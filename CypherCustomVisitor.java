import Entity.*;
import Query.Engine.QueryIndexer;
import Query.Engine.QueryPlanner;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

/**
 * Created by liuche on 5/21/17.
 *
 */
public class CypherCustomVisitor extends CypherBaseVisitor<Value> {
    //This is the map used for first selection step.
    private Map<String, Value> mem = new HashMap<>();
    private QueryIndexer indexer;
    private Map<String, QueryConstraints> varToConstraint = new HashMap<>();
    private List<Equality> equalityList = new ArrayList<>();
    private List<Path> pathList = new ArrayList<>();
    private List<Pair<String, String>> retList = new ArrayList<>();
    private Integer anonNode = 0, anonRelation = 0;

    public void setIndexer(QueryIndexer queryIndexer){
        this.indexer = queryIndexer;
    }

    void reset(){
        varToConstraint = new HashMap<>();
        anonNode = 0; anonRelation = 0;
    }

    void executeQuery() {
        // TODO: Fetch the constraint and carry out query execution.
        QueryPlanner planner = new QueryPlanner(varToConstraint,
                                                equalityList,
                                                pathList,
                                                retList,
                                                mem,
                                                indexer
                );

        planner.plan();

    }

    @Override
    public Value visitCypher(CypherParser.CypherContext ctx) {
        return this.visit(ctx.singleQuery());
    }

    @Override
    public Value visitSingleQuery(CypherParser.SingleQueryContext ctx) {
        for (int i = 0, size = ctx.clause().size(); i < size; i++) {
            this.visit(ctx.clause(i));
        }

        executeQuery();
        return null;
    }

    @Override
    public Value visitClause(CypherParser.ClauseContext ctx) {
        if (ctx.match() != null) {
            return this.visit(ctx.match());
        } else {
            return this.visit(ctx.return1());
        }
    }

    @Override
    public Value visitMatch(CypherParser.MatchContext ctx) {
        // Get pattern constraints
        Value res = this.visit(ctx.pattern());
        assert res.type.equals("List");
        List<Path> paths= (List<Path>) res.val;

        // Combine constraints in Pattern with constraints in WHERE
        if (ctx.expression() != null) {
            for(int i = 0 , size = ctx.expression().exp_not().size() ; i < size ; i++){
                Value ret = this.visit(ctx.expression().exp_not(i));
                if(ret != null && ret.type.equals("nodeLabels")){
                    String varName = (String) (((Pair) ret.val).getV0());
                    Value list = (Value) (((Pair) ret.val).getV1());
                    String equality = ctx.expression().exp_not(i).getChildCount() == 1 ? "==" : "!=";
                    varToConstraint.get(varName).add(new Constraint("nodeLabels", equality, list));
                }
            }
        }

        // Add additional constraint on relationships, that
        // relationships in the same match phase should be
        // different from each other.
        List<String> relations = new ArrayList<>();
        for(Path path : paths){
            for(String relation : path.relations){
                relations.add(relation);
            }
        }
        for(int i = 0, size = relations.size() ; i < size ; i++){
            for(int j = 0 ; j < i ; j++){
                equalityList.add(new Equality(relations.get(i), relations.get(j), "!=", "relation2relation"));
            }
        }

        return null;
    }

    @Override
    public Value visitReturn1(CypherParser.Return1Context ctx) {
        List<Pair<String, String>> returnVals = new ArrayList<>();
        for(int i = 0, size = ctx.expression().size(); i < size ; i++){
            Value retVal = this.visit(ctx.expression(i));
            assert retVal.type.equals("Variable") || retVal.type.equals("PropertyLookup") || retVal.isConstant;
            if(retVal.isConstant){
                returnVals.add(new Pair<>("Constant", retVal.val.toString()));
            }else if(retVal.type.equals("Variable")){
                String varName = (String)(retVal.val);
                returnVals.add(new Pair<>(varName, ""));
            }else{
                String varName = (String)(((Pair)(retVal.val)).getV0());
                String propertyName = (String)(((Pair)(retVal.val)).getV1());
                returnVals.add(new Pair<>(varName, propertyName));
            }
        }
        retList = returnVals;
        return new Value(returnVals, false, "Return");
    }

    @Override
    public Value visitPattern(CypherParser.PatternContext ctx) {
        List<Path> paths = new ArrayList<>();
        for (int i = 0, size = ctx.patternPart().size(); i < size; i++) {
            Value ret = this.visit(ctx.patternPart(i));
            Path path = (Path)ret.val;
            //TODO: Not finished yet.
            pathList.add(path);
            paths.add(path);
        }
        return new Value(paths, false, "List");
    }

    @Override
    public Value visitPatternPart(CypherParser.PatternPartContext ctx) {

        Value patternVal = this.visit(ctx.patternElement());
        if (ctx.variable() != null) {
            String var = ctx.variable().getText();
            assert !mem.containsKey(var);
            mem.put(var, patternVal);
        }
        return patternVal;
    }

    private String getDirection(CypherParser.RelationshipPatternContext ctx) {
        String direction = "";
        for (int i = 0, size = ctx.getChildCount(); i < size; i++) {
            switch (ctx.getChild(i).getText()) {
                case "<":
                    direction += "<";
                    break;
                case "-":
                    direction += "-";
                    break;
                case ">":
                    direction += ">";
                    break;
                default:
                    break;
            }
        }
        return direction;
    }

    @Override
    public Value visitPatternElement(CypherParser.PatternElementContext ctx) {
        if (ctx.patternElement() != null) {
            return this.visit(ctx.patternElement());
        }
        Value lastNode = this.visit(ctx.nodePattern());
        assert lastNode.type.equals("String");

        Path path = new Path();
        path.nodes.add((String) lastNode.val);

        for (int i = 0, size = ctx.patternElementChain().size(); i < size; i++) {
            Value val = this.visit(ctx.patternElementChain(i));
            assert val.type.equals("Pair");
            Pair<String, String> nodeRelation = (Pair<String, String>) val.val;
            String thisNodeName = nodeRelation.getV0();
            String relation = nodeRelation.getV1();
            String direction = getDirection(ctx.patternElementChain(i).relationshipPattern());
            //TODO: Add pattern direction chain
            path.add(thisNodeName, relation, direction);
        }
        return new Value(path, false, "Path");

    }

    @Override
    public Value visitPatternElementChain(CypherParser.PatternElementChainContext ctx) {
        String nodeVar, relationVar;
        Value res = this.visit(ctx.nodePattern());
        assert res.type.equals("String");
        nodeVar = (String) res.val;

        res = this.visit(ctx.relationshipPattern());
        assert res.type.equals("String");
        relationVar = (String) res.val;
        return new Value(new Pair<>(nodeVar, relationVar), true, "Pair");
    }

    @Override
    public Value visitNodePattern(CypherParser.NodePatternContext ctx) {

        String key;
        if (ctx.variable() != null) {
            key = ctx.variable().getText();
        } else {
            key = "anonNode" + anonNode.toString();
            anonNode++;
        }

        if (!varToConstraint.containsKey(key)) {
            varToConstraint.put(key, new QueryConstraints());
        }

        if (ctx.nodeLabels() != null) {
            Value res = this.visit(ctx.nodeLabels());
            assert res.type.equals("List");
            varToConstraint.get(key).add(new Constraint("nodeLabels", "==", res));
        }
        if (ctx.properties() != null) {
            Value res = this.visit(ctx.properties());
            assert res.type.contains("Map");
            Map<String, Value> propMap = (Map<String, Value>) res.val;
            for(String k: propMap.keySet()){
                Value v = propMap.get(k);
                varToConstraint.get(key).add(new Constraint(k, "==", v));
            }

        }

        return new Value(key, true, "String");
    }

    @Override
    public Value visitRelationshipPattern(CypherParser.RelationshipPatternContext ctx) {
        if(ctx.relationshipDetail() == null){
            String key = "anonRelation" + anonRelation.toString();
            anonRelation++;
            varToConstraint.put(key, new QueryConstraints());
            return new Value(key, true, "String");
        }
        return this.visit(ctx.relationshipDetail());
    }

    @Override
    public Value visitRelationshipDetail(CypherParser.RelationshipDetailContext ctx) {

        QueryConstraints constraints = new QueryConstraints();
        String key;
        if (ctx.variable() != null) {
            key = ctx.variable().getText();
        } else {
            key = "anonRelation" + anonRelation.toString();
            anonRelation++;
        }

        if (!varToConstraint.containsKey(key)) {
            varToConstraint.put(key, constraints);
        }

        if (ctx.relationshipTypes() != null) {
            Value res = this.visit(ctx.relationshipTypes());
            assert res.type.equals("List");
            constraints.add(new Constraint("rel_type", "==", res));

        }
        if (ctx.rangeLiteral() != null) {
            Value res = this.visit(ctx.rangeLiteral());
            assert res.type.equals("Pair");
            constraints.add(new Constraint("range", "in", res));
        }
        if (ctx.properties() != null) {
            Value result = this.visit(ctx.properties());
            assert result.type.contains("Map");
            Map<String, Value> propMap = (Map<String, Value>) result.val;
            for(String k: propMap.keySet()){
                constraints.add(new Constraint(k, "==", propMap.get(k)));
            }
        }

        return new Value(key, true, "String");
    }

    @Override
    public Value visitProperties(CypherParser.PropertiesContext ctx) {

        return this.visit(ctx.mapLiteral());
    }

    @Override
    public Value visitRelationshipTypes(CypherParser.RelationshipTypesContext ctx) {
        List<String> types = new ArrayList<>();
        for (int i = 0, size = ctx.relTypeName().size(); i < size; i++) {
            types.add(ctx.relTypeName(i).getText());
        }
        return new Value(types, true, "List");
    }

    @Override
    public Value visitNodeLabels(CypherParser.NodeLabelsContext ctx) {

        List<String> labels = new ArrayList<>();
        for (int i = 0, size = ctx.labelName().size(); i < size; i++) {
            labels.add(ctx.labelName(i).getText());
        }
        return new Value(labels, true, "List");
    }

    @Override
    public Value visitRangeLiteral(CypherParser.RangeLiteralContext ctx) {

        Pair<Integer, Integer> range = new Pair<>(0, Integer.MAX_VALUE);
        if (!ctx.getText().equals("*")) {
            if (!ctx.getText().contains("..")) {
                Integer val = new Integer(ctx.integerLiteral(0).getText());
                range = new Pair<>(val, val);
            } else {

                int dotPos = -1;
                for (int i = 0, size = ctx.getChildCount(); i < size; i++) {
                    if (ctx.getChild(i).getText().equals("..")) {
                        dotPos = i;
                        break;
                    }
                }
                assert dotPos > 0;
                String left = ctx.getChild(dotPos - 1).getText();
                String right = dotPos == ctx.getChildCount() - 1 ? "" : ctx.getChild(dotPos + 1).getText();
                if (!left.equals("*")) {
                    range.setV0(new Integer(left));
                }

                if (!right.equals("")) {
                    range.setV1(new Integer(right));
                }
            }
        }

        return new Value(range, true, "Pair");
    }

    @Override
    public Value visitLabelName(CypherParser.LabelNameContext ctx) {
        return new Value(ctx.getText(), true, "String");
    }

    @Override
    public Value visitRelTypeName(CypherParser.RelTypeNameContext ctx) {
        return new Value(ctx.getText(), true, "String");
    }

    @Override
    public Value visitExpression(CypherParser.ExpressionContext ctx) {
        if (ctx.exp_not().size() == 1) {
            return this.visit(ctx.exp_not(0));
        } else {
            for(int i = 0 , size = ctx.exp_not().size() ; i < size ; i++){
                this.visit(ctx.exp_not(i));
            }
            return null;
        }
    }

    private boolean isNot = false;
    private String swapComparision(String comp){
        switch (comp){
            case "=":  case "==":
                return "!=";
            case "<>": case "!=":
                return "==";
            case ">" :
                return "<=";
            case "<":
                return ">=";
            case ">=":
                return "<";
            case "<=":
                return ">";
            default:
                return "";
        }
    }
    @Override
    public Value visitExp_not(CypherParser.Exp_notContext ctx) {
        if (ctx.getChildCount() != 1) {
            isNot = true;
        }
        Value res = this.visit(ctx.exp_arithmatic());

        isNot = false;
        return res;
    }

    @Override
    public Value visitExp_arithmatic(CypherParser.Exp_arithmaticContext ctx) {
        // Notice:
        // TODO: Handling this for WHERE clause,
        // TODO: p.name = q.name; p.name = 2, p:aa:bb = true, p:aa
        //
        if (ctx.getChildCount() == 1) {
            return this.visit(ctx.exp_binary());
        } else {
            Value expression0 = this.visit(ctx.exp_binary());
            String comparison = ctx.partialComparisonExpression().getChild(0).getText();
            comparison = comparison.equals("=") ? "==" : comparison;
            comparison = isNot ? swapComparision(comparison) : comparison;
            Value expression1 = this.visit(ctx.partialComparisonExpression().getChild(1));

            assert !expression0.isConstant || !expression1.isConstant;
            Value temp = expression0;

            // Swap so that the first expression is definitely a variable
            if (!expression1.isConstant && expression0.isConstant) {
                expression0 = expression1;
                expression1 = temp;
            }


            if (expression1.isConstant) {
                String varName0 = (String) (((Pair) expression0.val).getV0());
                String constraintType = expression0.type;
                switch (constraintType) {
                    case "PropertyLookup":
                        // p.name = "aa"
                        assert expression1.isConstant;
                        String propertyName = (String) (((Pair) expression0.val).getV1());
                        varToConstraint.get(varName0).add(propertyName, comparison, expression1);
                        break;
                    case "nodeLabels":
                        // p:Crew:Matrix == true
                        assert expression1.type.contains("Boolean");
                        Boolean boolResult = (comparison.equals("==") ==
                                (Boolean) expression1.val);
                        String equality = boolResult ? "==" : "!=";
                        varToConstraint.get(varName0).add("nodeLabels", equality, expression1);
                        break;
                    default:
                        assert false;
                        break;
                }
            }else{
                // Two variables together
                //Currently only accept comparison between nodes and node properties
                assert expression0.type.equals(expression1.type) &&
                        (expression0.type.equals("Variable") ||
                                expression0.type.equals("PropertyLookup"));

                if(expression0.type.equals("Variable")){
                    String varName0 = (String) expression0.val;
                    String varName1 = (String) expression1.val;
                    Equality equality = new Equality(varName0, varName1, comparison);
                    equalityList.add(equality);
                }else{
                    String varName0 = (String) (((Pair) (expression0.val)).getV0());
                    String varName1 = (String) (((Pair) (expression1.val)).getV0());

                    String propertyName0 = (String)((Pair) expression0.val).getV1();
                    String propertyName1 = (String)((Pair) expression1.val).getV1();
                    Equality equality = new Equality(varName0, varName1, comparison, propertyName0, propertyName1);
                    equalityList.add(equality);
                }
            }
        }
        return null;
    }

    @Override
    public Value visitExp_binary(CypherParser.Exp_binaryContext ctx) {
        Value res = this.visit(ctx.exp_muldiv(0));
        assert ctx.exp_muldiv().size() == 1 ||
                ctx.exp_muldiv().size() > 1 && res.isConstant;

        if (ctx.getChildCount() == 1) {
            return res;

        } else if (res.type.equals("Integer")) {
            Integer intVal = (Integer) res.val;
            for (int i = 1, size = ctx.exp_muldiv().size(); i < size; i++) {
                Value other = this.visit(ctx.exp_muldiv(i));
                String operation = ctx.getChild(i * 2 - 1).getText();
                assert other.isConstant;
                switch (operation) {
                    case "+":
                        intVal = intVal + (Integer) other.val;
                        break;
                    default:
                        intVal = intVal - (Integer) other.val;
                        break;
                }
            }
            return new Value(intVal, true, "Integer");
        } else {
            Double doubleVal = (Double) res.val;
            for (int i = 1, size = ctx.exp_muldiv().size(); i < size; i++) {
                Value other = this.visit(ctx.exp_muldiv(i));
                String operation = ctx.getChild(i * 2 - 1).getText();
                assert other.isConstant;
                switch (operation) {
                    case "+":
                        doubleVal = doubleVal + (Double) other.val;
                        break;
                    default:
                        doubleVal = doubleVal - (Double) other.val;
                        break;
                }
            }
            return new Value(doubleVal, true, "Double");
        }
    }

    @Override
    public Value visitExp_muldiv(CypherParser.Exp_muldivContext ctx) {
        Value res = this.visit(ctx.exp_xor(0));
        assert ctx.exp_xor().size() == 1 ||
                ctx.exp_xor().size() > 1 && res.isConstant;

        if (ctx.getChildCount() == 1) {
            return res;
        } else if (res.type.equals("Integer")) {
            Integer intVal = (Integer) res.val;
            for (int i = 1, size = ctx.exp_xor().size(); i < size; i++) {
                Value other = this.visit(ctx.exp_xor(i));
                String operation = ctx.getChild(i * 2 - 1).getText();
                assert other.isConstant;
                switch (operation) {
                    case "*":
                        intVal = intVal * (Integer) other.val;
                        break;
                    case "/":
                        intVal = intVal / (Integer) other.val;
                        break;
                    default:
                        intVal = intVal % (Integer) other.val;
                        break;
                }
            }
            return new Value(intVal, true, "Integer");
        } else {
            Double doubleVal = (Double) res.val;
            for (int i = 1, size = ctx.exp_xor().size(); i < size; i++) {
                Value other = this.visit(ctx.exp_xor(i));
                String operation = ctx.getChild(i * 2 - 1).getText();
                assert other.isConstant;
                switch (operation) {
                    case "*":
                        doubleVal = doubleVal * (Double) other.val;
                        break;
                    case "/":
                        doubleVal = doubleVal / (Double) other.val;
                        break;
                    default:
                        doubleVal = doubleVal % (Double) other.val;
                        break;
                }
            }
            return new Value(doubleVal, true, "Double");
        }

    }

    @Override
    public Value visitExp_xor(CypherParser.Exp_xorContext ctx) {
        Value res = this.visit(ctx.exp_unary(0));
        assert ctx.exp_unary().size() == 1 ||
                ctx.exp_unary().size() > 1 && res.type.equals("Integer");

        if (ctx.getChildCount() == 1) {
            return res;
        } else {
            Integer intVal = (Integer) res.val;
            for (int i = 1, size = ctx.exp_unary().size(); i < size; i++) {
                Value other = this.visit(ctx.exp_unary(i));
                assert other.type.equals("Integer") && other.isConstant;
                intVal = intVal ^ (Integer) other.val;
            }
            return new Value(intVal, true, "Integer");
        }


    }

    @Override
    public Value visitExp_unary(CypherParser.Exp_unaryContext ctx) {
        // Notice: ANTLR grammar is changed here
        // Only accept one '-' in unary expression

        Value val = this.visit(ctx.exp_basic());
        assert (ctx.getChildCount() == 1) ||
                ctx.getChildCount() > 1 && val.isConstant && (val.val instanceof Number);

        if (ctx.getChildCount() == 1) {
            return this.visit(ctx.exp_basic());
        }

        boolean isNeg = false;
        for (int i = 0, size = ctx.getChildCount() - 1; i < size; i++) {
            if (ctx.getChild(i).getText().equals("-")) {
                isNeg = !isNeg;
            }
        }

        if (val.type.equals("Integer")) {
            val.val = (Integer) val.val * (isNeg ? -1 : 1);
        } else {
            val.val = (Double) val.val * (isNeg ? -1 : 1);
        }

        return val;
    }

    @Override
    public Value visitExp_basic(CypherParser.Exp_basicContext ctx) {
        if (ctx.getChildCount() == 1) {
            return this.visit(ctx.getChild(0));
        }

        Value prevVal = this.visit(ctx.getChild(0));
        if (!prevVal.isConstant) {
            return prevVal;
        }
        if (ctx.getText().contains("[")) {
            // TODO: Add support for array visit in predicates later
            assert prevVal.isConstant &&
                    prevVal.type.equals("List");

            int dotdotPos = 0;
            for (int i = 0, size = ctx.getChildCount(); i < size; i++) {
                dotdotPos = i;
                if (ctx.getChild(i).getText().equals("..")) {
                    break;
                }
            }

            // No dotdot,
            if (dotdotPos == ctx.getChildCount()) {
                assert dotdotPos >= 2;
                Value pos = this.visit(ctx.expression(0));
                assert pos.type.equals("Integer") && pos.isConstant;
                int posInt = (Integer) (pos.val);
                assert posInt < ((List) prevVal).size() &&
                        ((List) prevVal.val).get(posInt) instanceof Value;
                return (Value) ((List) prevVal.val).get(posInt);
            } else {


                int left, right;
                if (ctx.getChild(dotdotPos - 1).getText().equals("[")) {
                    left = 0;
                } else {
                    Value leftVal = this.visit(ctx.getChild(dotdotPos - 1));
                    assert leftVal.type.equals("Integer");
                    left = (Integer) leftVal.val;
                }

                if (ctx.getChild(dotdotPos + 1).getText().equals("]")) {
                    right = ((List) prevVal).size() - 1;
                } else {
                    Value rightVal = this.visit(ctx.getChild(dotdotPos + 1));
                    assert rightVal.type.equals("Integer");
                    right = (Integer) rightVal.val;
                }
                List<Value> res = new ArrayList<>();
                for (int i = left; i <= right; i++) {
                    assert ((Value) ((List) prevVal).get(i)).isConstant;
                    res.add((Value) ((List) prevVal).get(i));
                }
                return new Value(res, true, "List");
            }


        } else {
            return prevVal;
        }
    }

    @Override
    public Value visitExpression2(CypherParser.Expression2Context ctx) {
        Value atomValue = this.visit(ctx.atom());
        if (ctx.getChildCount() > 1) {
            String varName = (String) atomValue.val;
            assert varToConstraint.containsKey(varName);
            if (ctx.propertyLookup() != null) {
                assert ctx.nodeLabels() == null;
                String val = ctx.propertyLookup().propertyKeyName().getText();
                return new Value(new Pair<>(varName, val), false, "PropertyLookup");
            } else {
                assert ctx.propertyLookup() == null;
                // :Label is a final property.
                // Add this constraint directly, and don't propagate it.
                Value val = this.visit(ctx.nodeLabels());
                return new Value(new Pair<>(varName, val), false, "nodeLabels");
            }
        }
        return atomValue;
    }

    @Override
    public Value visitAtom(CypherParser.AtomContext ctx) {
        if (ctx.COUNT() != null) {
            return new Value("COUNT*", true, "Count*");
        }
        return this.visit(ctx.getChild(0));
    }

    @Override
    public Value visitLiteral(CypherParser.LiteralContext ctx) {
        if (ctx.StringLiteral() != null) {
            return new Value(ctx.StringLiteral().getText(), true, "String");
        } else if (ctx.NULL() != null) {
            return new Value("null", true, "null");
        } else {
            return this.visit(ctx.getChild(0));
        }
    }

    @Override
    public Value visitNumberLiteral(CypherParser.NumberLiteralContext ctx) {
        return this.visit(ctx.getChild(0));
    }

    @Override
    public Value visitBooleanLiteral(CypherParser.BooleanLiteralContext ctx) {
        Boolean boolVal = ctx.getText().toLowerCase().contains("true");
        return new Value(boolVal, true, "Boolean");
    }

    @Override
    public Value visitListLiteral(CypherParser.ListLiteralContext ctx) {
        List<Value> res = new ArrayList<>();
        boolean isConst = true;
        for (int i = 0, size = ctx.expression().size(); i < size; i++) {
            Value expVal = this.visit(ctx.expression(i));
            isConst = isConst && expVal.isConstant;
            res.add(expVal);
        }
        return new Value(res, isConst, "List");
    }

    @Override
    public Value visitMapLiteral(CypherParser.MapLiteralContext ctx) {
        Map<String, Value> res = new HashMap<>();
        boolean isConst = true;
        for (int i = 0, size = ctx.expression().size(); i < size; i++) {
            String key = ctx.propertyKeyName(i).getText();
            Value val = this.visit(ctx.expression(i));
            isConst = isConst && val.isConstant;
            res.put(key, val);
        }
        return new Value(res, isConst, "Map");
    }

    @Override
    public Value visitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx) {
        return null;
    }

    @Override
    public Value visitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx) {
        return this.visit(ctx.expression());
    }

    @Override
    public Value visitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx) {
        assert false;
        Value lastNode = this.visit(ctx.nodePattern());
        assert lastNode.type.equals("String");

        Path path = new Path();
        path.nodes.add((String) lastNode.val);

        for (int i = 0, size = ctx.patternElementChain().size(); i < size; i++) {
            Value val = this.visit(ctx.patternElementChain(i));
            assert val.type.equals("Pair");
            Pair<String, String> nodeRelation = (Pair<String, String>) val.val;
            String direction = getDirection(ctx.patternElementChain(i).relationshipPattern());
            path.add(nodeRelation.getV0(), nodeRelation.getV1(), direction);
        }
        return new Value(path, false, "Path");
    }

    @Override
    public Value visitPropertyLookup(CypherParser.PropertyLookupContext ctx) {
        return null;
    }

    @Override
    public Value visitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx) {
        // No one is visiting it.
        // This is done.
        assert false;
        return null;
    }

    @Override
    public Value visitVariable(CypherParser.VariableContext ctx) {
        return new Value(ctx.getText(), false, "Variable");
    }

    @Override
    public Value visitIntegerLiteral(CypherParser.IntegerLiteralContext ctx) {
        Integer val = new Integer(ctx.getText());
        return new Value(val, true, "Integer");
    }

    @Override
    public Value visitDoubleLiteral(CypherParser.DoubleLiteralContext ctx) {
        Double val = new Double(ctx.getText());
        return new Value(val, true, "Double");
    }

    @Override
    public Value visitSymbolicName(CypherParser.SymbolicNameContext ctx) {
        // No one is visiting it.
        // This is done.
        assert false;
        return null;
    }

    @Override
    public Value visitLeftArrowHead(CypherParser.LeftArrowHeadContext ctx) {
        return null;
    }

    @Override
    public Value visitRightArrowHead(CypherParser.RightArrowHeadContext ctx) {
        return null;
    }

    @Override
    public Value visitDash(CypherParser.DashContext ctx) {
        return super.visit(ctx);
    }

    @Override
    public Value visit(ParseTree parseTree) {
        return super.visit(parseTree);
    }

    @Override
    public Value visitChildren(RuleNode ruleNode) {
        return super.visitChildren(ruleNode);

    }

    @Override
    public Value visitTerminal(TerminalNode terminalNode) {
        return super.visitTerminal(terminalNode);
    }

    @Override
    public Value visitErrorNode(ErrorNode errorNode) {
        return super.visitErrorNode(errorNode);
    }
}
