// Generated from src/Cypher.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CypherParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CypherVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCypher(CypherParser.CypherContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause(CypherParser.ClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatch(CypherParser.MatchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#return1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn1(CypherParser.Return1Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(CypherParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exp_not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_not(CypherParser.Exp_notContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exp_arithmatic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_arithmatic(CypherParser.Exp_arithmaticContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exp_binary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_binary(CypherParser.Exp_binaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exp_muldiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_muldiv(CypherParser.Exp_muldivContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exp_xor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_xor(CypherParser.Exp_xorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exp_unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_unary(CypherParser.Exp_unaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exp_basic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_basic(CypherParser.Exp_basicContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(CypherParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(CypherParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLiteral(CypherParser.ListLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(CypherParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(CypherParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDash(CypherParser.DashContext ctx);
}