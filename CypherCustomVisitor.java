import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Created by liuche on 4/25/17.
 */
public class CypherCustomVisitor implements CypherVisitor<Value> {
    @Override
    public Value visitCypher(CypherParser.CypherContext ctx) {
        return null;
    }

    @Override
    public Value visitStatement(CypherParser.StatementContext ctx) {
        return null;
    }

    @Override
    public Value visitSingleQuery(CypherParser.SingleQueryContext ctx) {
        return null;
    }

    @Override
    public Value visitClause(CypherParser.ClauseContext ctx) {
        return null;
    }

    @Override
    public Value visitMatch(CypherParser.MatchContext ctx) {
        return null;
    }

    @Override
    public Value visitReturn1(CypherParser.Return1Context ctx) {
        return null;
    }

    @Override
    public Value visitReturnBody(CypherParser.ReturnBodyContext ctx) {
        return null;
    }

    @Override
    public Value visitReturnItems(CypherParser.ReturnItemsContext ctx) {
        return null;
    }

    @Override
    public Value visitReturnItem(CypherParser.ReturnItemContext ctx) {
        return null;
    }

    @Override
    public Value visitWhere(CypherParser.WhereContext ctx) {
        return null;
    }

    @Override
    public Value visitPattern(CypherParser.PatternContext ctx) {
        return null;
    }

    @Override
    public Value visitPatternPart(CypherParser.PatternPartContext ctx) {
        return null;
    }

    @Override
    public Value visitAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx) {
        return null;
    }

    @Override
    public Value visitPatternElement(CypherParser.PatternElementContext ctx) {
        return null;
    }

    @Override
    public Value visitNodePattern(CypherParser.NodePatternContext ctx) {
        return null;
    }

    @Override
    public Value visitPatternElementChain(CypherParser.PatternElementChainContext ctx) {
        return null;
    }

    @Override
    public Value visitRelationshipPattern(CypherParser.RelationshipPatternContext ctx) {
        return null;
    }

    @Override
    public Value visitRelationshipDetail(CypherParser.RelationshipDetailContext ctx) {
        return null;
    }

    @Override
    public Value visitProperties(CypherParser.PropertiesContext ctx) {
        return null;
    }

    @Override
    public Value visitRelationshipTypes(CypherParser.RelationshipTypesContext ctx) {
        return null;
    }

    @Override
    public Value visitNodeLabels(CypherParser.NodeLabelsContext ctx) {
        return null;
    }

    @Override
    public Value visitNodeLabel(CypherParser.NodeLabelContext ctx) {
        return null;
    }

    @Override
    public Value visitRangeLiteral(CypherParser.RangeLiteralContext ctx) {
        return null;
    }

    @Override
    public Value visitLabelName(CypherParser.LabelNameContext ctx) {
        return null;
    }

    @Override
    public Value visitRelTypeName(CypherParser.RelTypeNameContext ctx) {
        return null;
    }

    @Override
    public Value visitExpression(CypherParser.ExpressionContext ctx) {
        return null;
    }

    @Override
    public Value visitExpression10(CypherParser.Expression10Context ctx) {
        return null;
    }

    @Override
    public Value visitExpression9(CypherParser.Expression9Context ctx) {
        return null;
    }

    @Override
    public Value visitExpression8(CypherParser.Expression8Context ctx) {
        return null;
    }

    @Override
    public Value visitExpression7(CypherParser.Expression7Context ctx) {
        return null;
    }

    @Override
    public Value visitExpression6(CypherParser.Expression6Context ctx) {
        return null;
    }

    @Override
    public Value visitExpression5(CypherParser.Expression5Context ctx) {
        return null;
    }

    @Override
    public Value visitExpression4(CypherParser.Expression4Context ctx) {
        return null;
    }

    @Override
    public Value visitExpression3(CypherParser.Expression3Context ctx) {
        return null;
    }

    @Override
    public Value visitExpression2(CypherParser.Expression2Context ctx) {
        return null;
    }

    @Override
    public Value visitAtom(CypherParser.AtomContext ctx) {
        return null;
    }

    @Override
    public Value visitLiteral(CypherParser.LiteralContext ctx) {
        return null;
    }

    @Override
    public Value visitNumberLiteral(CypherParser.NumberLiteralContext ctx) {
        return null;
    }

    @Override
    public Value visitBooleanLiteral(CypherParser.BooleanLiteralContext ctx) {
        return null;
    }

    @Override
    public Value visitListLiteral(CypherParser.ListLiteralContext ctx) {
        return null;
    }

    @Override
    public Value visitMapLiteral(CypherParser.MapLiteralContext ctx) {
        return null;
    }

    @Override
    public Value visitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx) {
        return null;
    }

    @Override
    public Value visitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx) {
        return null;
    }

    @Override
    public Value visitParameter(CypherParser.ParameterContext ctx) {
        return null;
    }

    @Override
    public Value visitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx) {
        return null;
    }

    @Override
    public Value visitPropertyLookup(CypherParser.PropertyLookupContext ctx) {
        return null;
    }

    @Override
    public Value visitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx) {
        return null;
    }

    @Override
    public Value visitVariable(CypherParser.VariableContext ctx) {
        return null;
    }

    @Override
    public Value visitIntegerLiteral(CypherParser.IntegerLiteralContext ctx) {
        return null;
    }

    @Override
    public Value visitDoubleLiteral(CypherParser.DoubleLiteralContext ctx) {
        return null;
    }

    @Override
    public Value visitSymbolicName(CypherParser.SymbolicNameContext ctx) {
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
        return null;
    }

    @Override
    public Value visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Value visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Value visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Value visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
