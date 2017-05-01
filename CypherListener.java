// Generated from src/Cypher.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CypherParser}.
 */
public interface CypherListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void enterCypher(CypherParser.CypherContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void exitCypher(CypherParser.CypherContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CypherParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CypherParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void enterSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void exitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(CypherParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(CypherParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void enterMatch(CypherParser.MatchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void exitMatch(CypherParser.MatchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#return1}.
	 * @param ctx the parse tree
	 */
	void enterReturn1(CypherParser.Return1Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#return1}.
	 * @param ctx the parse tree
	 */
	void exitReturn1(CypherParser.Return1Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void enterReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void exitReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void enterReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void exitReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void enterReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void exitReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(CypherParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(CypherParser.WhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(CypherParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(CypherParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void enterPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void exitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#anonymousPatternPart}.
	 * @param ctx the parse tree
	 */
	void enterAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#anonymousPatternPart}.
	 * @param ctx the parse tree
	 */
	void exitAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void enterPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void exitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void enterNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void exitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void enterPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void exitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void enterLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void exitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void enterRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void exitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression10}.
	 * @param ctx the parse tree
	 */
	void enterExpression10(CypherParser.Expression10Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression10}.
	 * @param ctx the parse tree
	 */
	void exitExpression10(CypherParser.Expression10Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression9}.
	 * @param ctx the parse tree
	 */
	void enterExpression9(CypherParser.Expression9Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression9}.
	 * @param ctx the parse tree
	 */
	void exitExpression9(CypherParser.Expression9Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression8}.
	 * @param ctx the parse tree
	 */
	void enterExpression8(CypherParser.Expression8Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression8}.
	 * @param ctx the parse tree
	 */
	void exitExpression8(CypherParser.Expression8Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression7}.
	 * @param ctx the parse tree
	 */
	void enterExpression7(CypherParser.Expression7Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression7}.
	 * @param ctx the parse tree
	 */
	void exitExpression7(CypherParser.Expression7Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression6}.
	 * @param ctx the parse tree
	 */
	void enterExpression6(CypherParser.Expression6Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression6}.
	 * @param ctx the parse tree
	 */
	void exitExpression6(CypherParser.Expression6Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression5}.
	 * @param ctx the parse tree
	 */
	void enterExpression5(CypherParser.Expression5Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression5}.
	 * @param ctx the parse tree
	 */
	void exitExpression5(CypherParser.Expression5Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression4}.
	 * @param ctx the parse tree
	 */
	void enterExpression4(CypherParser.Expression4Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression4}.
	 * @param ctx the parse tree
	 */
	void exitExpression4(CypherParser.Expression4Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression3}.
	 * @param ctx the parse tree
	 */
	void enterExpression3(CypherParser.Expression3Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression3}.
	 * @param ctx the parse tree
	 */
	void exitExpression3(CypherParser.Expression3Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 */
	void enterExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 */
	void exitExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CypherParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CypherParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(CypherParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(CypherParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 */
	void enterListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 */
	void exitListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void enterMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void exitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(CypherParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(CypherParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void enterPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void exitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(CypherParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(CypherParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(CypherParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(CypherParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void enterSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void exitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 */
	void enterLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 */
	void exitLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 */
	void enterRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 */
	void exitRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 */
	void enterDash(CypherParser.DashContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 */
	void exitDash(CypherParser.DashContext ctx);
}