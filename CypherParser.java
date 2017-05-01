// Generated from src/Cypher.g4 by ANTLR 4.6
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CypherParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, StringLiteral=27, DecimalInteger=28, Digit=29, NonZeroDigit=30, 
		NonZeroOctDigit=31, OctDigit=32, ZeroDigit=33, RegularDecimalReal=34, 
		COUNT=35, NULL=36, OR=37, MATCH=38, RETURN=39, WHERE=40, AND=41, NOT=42, 
		TRUE=43, FALSE=44, UnescapedSymbolicName=45, IdentifierStart=46, IdentifierPart=47, 
		SP=48, WHITESPACE=49, Comment=50;
	public static final int
		RULE_cypher = 0, RULE_statement = 1, RULE_singleQuery = 2, RULE_clause = 3, 
		RULE_match = 4, RULE_return1 = 5, RULE_returnBody = 6, RULE_returnItems = 7, 
		RULE_returnItem = 8, RULE_where = 9, RULE_pattern = 10, RULE_patternPart = 11, 
		RULE_anonymousPatternPart = 12, RULE_patternElement = 13, RULE_nodePattern = 14, 
		RULE_patternElementChain = 15, RULE_relationshipPattern = 16, RULE_relationshipDetail = 17, 
		RULE_properties = 18, RULE_relationshipTypes = 19, RULE_nodeLabels = 20, 
		RULE_nodeLabel = 21, RULE_rangeLiteral = 22, RULE_labelName = 23, RULE_relTypeName = 24, 
		RULE_expression = 25, RULE_expression10 = 26, RULE_expression9 = 27, RULE_expression8 = 28, 
		RULE_expression7 = 29, RULE_expression6 = 30, RULE_expression5 = 31, RULE_expression4 = 32, 
		RULE_expression3 = 33, RULE_expression2 = 34, RULE_atom = 35, RULE_literal = 36, 
		RULE_numberLiteral = 37, RULE_booleanLiteral = 38, RULE_listLiteral = 39, 
		RULE_mapLiteral = 40, RULE_partialComparisonExpression = 41, RULE_parenthesizedExpression = 42, 
		RULE_parameter = 43, RULE_relationshipsPattern = 44, RULE_propertyLookup = 45, 
		RULE_propertyKeyName = 46, RULE_variable = 47, RULE_integerLiteral = 48, 
		RULE_doubleLiteral = 49, RULE_symbolicName = 50, RULE_leftArrowHead = 51, 
		RULE_rightArrowHead = 52, RULE_dash = 53;
	public static final String[] ruleNames = {
		"cypher", "statement", "singleQuery", "clause", "match", "return1", "returnBody", 
		"returnItems", "returnItem", "where", "pattern", "patternPart", "anonymousPatternPart", 
		"patternElement", "nodePattern", "patternElementChain", "relationshipPattern", 
		"relationshipDetail", "properties", "relationshipTypes", "nodeLabels", 
		"nodeLabel", "rangeLiteral", "labelName", "relTypeName", "expression", 
		"expression10", "expression9", "expression8", "expression7", "expression6", 
		"expression5", "expression4", "expression3", "expression2", "atom", "literal", 
		"numberLiteral", "booleanLiteral", "listLiteral", "mapLiteral", "partialComparisonExpression", 
		"parenthesizedExpression", "parameter", "relationshipsPattern", "propertyLookup", 
		"propertyKeyName", "variable", "integerLiteral", "doubleLiteral", "symbolicName", 
		"leftArrowHead", "rightArrowHead", "dash"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'='", "'('", "')'", "'['", "']'", "':'", "'|'", "'*'", 
		"'..'", "'+'", "'-'", "'/'", "'%'", "'^'", "'{'", "'}'", "'<>'", "'!='", 
		"'<'", "'>'", "'<='", "'>='", "'$'", "'.'", null, null, null, null, null, 
		null, "'0'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "StringLiteral", "DecimalInteger", "Digit", "NonZeroDigit", 
		"NonZeroOctDigit", "OctDigit", "ZeroDigit", "RegularDecimalReal", "COUNT", 
		"NULL", "OR", "MATCH", "RETURN", "WHERE", "AND", "NOT", "TRUE", "FALSE", 
		"UnescapedSymbolicName", "IdentifierStart", "IdentifierPart", "SP", "WHITESPACE", 
		"Comment"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Cypher.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CypherParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CypherContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public CypherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cypher; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCypher(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCypher(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCypher(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CypherContext cypher() throws RecognitionException {
		CypherContext _localctx = new CypherContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_cypher);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(108);
				match(SP);
				}
			}

			setState(111);
			statement();
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(112);
					match(SP);
					}
				}

				setState(115);
				match(T__0);
				}
				break;
			}
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(118);
				match(SP);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public SingleQueryContext singleQuery() {
			return getRuleContext(SingleQueryContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			singleQuery();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SingleQueryContext extends ParserRuleContext {
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public SingleQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSingleQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSingleQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSingleQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleQueryContext singleQuery() throws RecognitionException {
		SingleQueryContext _localctx = new SingleQueryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_singleQuery);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			clause();
			setState(130);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(125);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(124);
						match(SP);
						}
					}

					setState(127);
					clause();
					}
					} 
				}
				setState(132);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClauseContext extends ParserRuleContext {
		public MatchContext match() {
			return getRuleContext(MatchContext.class,0);
		}
		public Return1Context return1() {
			return getRuleContext(Return1Context.class,0);
		}
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_clause);
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				match();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				return1();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchContext extends ParserRuleContext {
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public MatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMatch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchContext match() throws RecognitionException {
		MatchContext _localctx = new MatchContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_match);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(MATCH);
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(138);
				match(SP);
				}
			}

			setState(141);
			pattern();
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(142);
					match(SP);
					}
				}

				setState(145);
				where();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return1Context extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }
		public TerminalNode SP() { return getToken(CypherParser.SP, 0); }
		public ReturnBodyContext returnBody() {
			return getRuleContext(ReturnBodyContext.class,0);
		}
		public Return1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturn1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturn1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturn1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return1Context return1() throws RecognitionException {
		Return1Context _localctx = new Return1Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_return1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(RETURN);
			setState(149);
			match(SP);
			setState(150);
			returnBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnBodyContext extends ParserRuleContext {
		public ReturnItemsContext returnItems() {
			return getRuleContext(ReturnItemsContext.class,0);
		}
		public ReturnBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnBodyContext returnBody() throws RecognitionException {
		ReturnBodyContext _localctx = new ReturnBodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_returnBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			returnItems();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnItemsContext extends ParserRuleContext {
		public List<ReturnItemContext> returnItem() {
			return getRuleContexts(ReturnItemContext.class);
		}
		public ReturnItemContext returnItem(int i) {
			return getRuleContext(ReturnItemContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public ReturnItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnItems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnItemsContext returnItems() throws RecognitionException {
		ReturnItemsContext _localctx = new ReturnItemsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_returnItems);
		int _la;
		try {
			int _alt;
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(164);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(155);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(154);
							match(SP);
							}
						}

						setState(157);
						match(T__1);
						setState(159);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(158);
							match(SP);
							}
						}

						setState(161);
						returnItem();
						}
						} 
					}
					setState(166);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(167);
				returnItem();
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(169);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(168);
							match(SP);
							}
						}

						setState(171);
						match(T__1);
						setState(173);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(172);
							match(SP);
							}
						}

						setState(175);
						returnItem();
						}
						} 
					}
					setState(180);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnItemContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnItemContext returnItem() throws RecognitionException {
		ReturnItemContext _localctx = new ReturnItemContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_returnItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public TerminalNode SP() { return getToken(CypherParser.SP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWhere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWhere(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitWhere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(WHERE);
			setState(186);
			match(SP);
			setState(187);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternContext extends ParserRuleContext {
		public List<PatternPartContext> patternPart() {
			return getRuleContexts(PatternPartContext.class);
		}
		public PatternPartContext patternPart(int i) {
			return getRuleContext(PatternPartContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			patternPart();
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(191);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(190);
						match(SP);
						}
					}

					setState(193);
					match(T__1);
					setState(195);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(194);
						match(SP);
						}
					}

					setState(197);
					patternPart();
					}
					} 
				}
				setState(202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternPartContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AnonymousPatternPartContext anonymousPatternPart() {
			return getRuleContext(AnonymousPatternPartContext.class,0);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public PatternPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternPartContext patternPart() throws RecognitionException {
		PatternPartContext _localctx = new PatternPartContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_patternPart);
		int _la;
		try {
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OR:
			case MATCH:
			case RETURN:
			case WHERE:
			case AND:
			case NOT:
			case TRUE:
			case FALSE:
			case UnescapedSymbolicName:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(203);
				variable();
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(204);
					match(SP);
					}
				}

				setState(207);
				match(T__2);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(208);
					match(SP);
					}
				}

				setState(211);
				anonymousPatternPart();
				}
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				anonymousPatternPart();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnonymousPatternPartContext extends ParserRuleContext {
		public PatternElementContext patternElement() {
			return getRuleContext(PatternElementContext.class,0);
		}
		public AnonymousPatternPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymousPatternPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAnonymousPatternPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAnonymousPatternPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitAnonymousPatternPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnonymousPatternPartContext anonymousPatternPart() throws RecognitionException {
		AnonymousPatternPartContext _localctx = new AnonymousPatternPartContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_anonymousPatternPart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			patternElement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternElementContext extends ParserRuleContext {
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public List<PatternElementChainContext> patternElementChain() {
			return getRuleContexts(PatternElementChainContext.class);
		}
		public PatternElementChainContext patternElementChain(int i) {
			return getRuleContext(PatternElementChainContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public PatternElementContext patternElement() {
			return getRuleContext(PatternElementContext.class,0);
		}
		public PatternElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternElementContext patternElement() throws RecognitionException {
		PatternElementContext _localctx = new PatternElementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_patternElement);
		int _la;
		try {
			int _alt;
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(218);
				nodePattern();
				setState(225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(220);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(219);
							match(SP);
							}
						}

						setState(222);
						patternElementChain();
						}
						} 
					}
					setState(227);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(228);
				match(T__3);
				setState(229);
				patternElement();
				setState(230);
				match(T__4);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodePatternContext extends ParserRuleContext {
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public NodePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodePattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodePattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodePatternContext nodePattern() throws RecognitionException {
		NodePatternContext _localctx = new NodePatternContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_nodePattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(T__3);
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(235);
				match(SP);
				}
			}

			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << MATCH) | (1L << RETURN) | (1L << WHERE) | (1L << AND) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << UnescapedSymbolicName))) != 0)) {
				{
				setState(238);
				variable();
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(239);
					match(SP);
					}
				}

				}
			}

			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(244);
				nodeLabels();
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(245);
					match(SP);
					}
				}

				}
			}

			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16 || _la==T__24) {
				{
				setState(250);
				properties();
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(251);
					match(SP);
					}
				}

				}
			}

			setState(256);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternElementChainContext extends ParserRuleContext {
		public RelationshipPatternContext relationshipPattern() {
			return getRuleContext(RelationshipPatternContext.class,0);
		}
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public TerminalNode SP() { return getToken(CypherParser.SP, 0); }
		public PatternElementChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternElementChain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternElementChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternElementChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternElementChain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternElementChainContext patternElementChain() throws RecognitionException {
		PatternElementChainContext _localctx = new PatternElementChainContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_patternElementChain);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			relationshipPattern();
			setState(260);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(259);
				match(SP);
				}
			}

			setState(262);
			nodePattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipPatternContext extends ParserRuleContext {
		public LeftArrowHeadContext leftArrowHead() {
			return getRuleContext(LeftArrowHeadContext.class,0);
		}
		public List<DashContext> dash() {
			return getRuleContexts(DashContext.class);
		}
		public DashContext dash(int i) {
			return getRuleContext(DashContext.class,i);
		}
		public RightArrowHeadContext rightArrowHead() {
			return getRuleContext(RightArrowHeadContext.class,0);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public RelationshipDetailContext relationshipDetail() {
			return getRuleContext(RelationshipDetailContext.class,0);
		}
		public RelationshipPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipPatternContext relationshipPattern() throws RecognitionException {
		RelationshipPatternContext _localctx = new RelationshipPatternContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_relationshipPattern);
		int _la;
		try {
			setState(328);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(264);
				leftArrowHead();
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(265);
					match(SP);
					}
				}

				setState(268);
				dash();
				setState(270);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(269);
					match(SP);
					}
					break;
				}
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(272);
					relationshipDetail();
					}
				}

				setState(276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(275);
					match(SP);
					}
				}

				setState(278);
				dash();
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(279);
					match(SP);
					}
				}

				setState(282);
				rightArrowHead();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(284);
				leftArrowHead();
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(285);
					match(SP);
					}
				}

				setState(288);
				dash();
				setState(290);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(289);
					match(SP);
					}
					break;
				}
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(292);
					relationshipDetail();
					}
				}

				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(295);
					match(SP);
					}
				}

				setState(298);
				dash();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(300);
				dash();
				setState(302);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(301);
					match(SP);
					}
					break;
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(304);
					relationshipDetail();
					}
				}

				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(307);
					match(SP);
					}
				}

				setState(310);
				dash();
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(311);
					match(SP);
					}
				}

				setState(314);
				rightArrowHead();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(316);
				dash();
				setState(318);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(317);
					match(SP);
					}
					break;
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(320);
					relationshipDetail();
					}
				}

				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(323);
					match(SP);
					}
				}

				setState(326);
				dash();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipDetailContext extends ParserRuleContext {
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RelationshipTypesContext relationshipTypes() {
			return getRuleContext(RelationshipTypesContext.class,0);
		}
		public RangeLiteralContext rangeLiteral() {
			return getRuleContext(RangeLiteralContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public RelationshipDetailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipDetail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipDetail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipDetail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipDetail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipDetailContext relationshipDetail() throws RecognitionException {
		RelationshipDetailContext _localctx = new RelationshipDetailContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_relationshipDetail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(T__5);
			setState(332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(331);
				match(SP);
				}
			}

			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << MATCH) | (1L << RETURN) | (1L << WHERE) | (1L << AND) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << UnescapedSymbolicName))) != 0)) {
				{
				setState(334);
				variable();
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(335);
					match(SP);
					}
				}

				}
			}

			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(340);
				relationshipTypes();
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(341);
					match(SP);
					}
				}

				}
			}

			setState(347);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(346);
				rangeLiteral();
				}
			}

			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16 || _la==T__24) {
				{
				setState(349);
				properties();
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(350);
					match(SP);
					}
				}

				}
			}

			setState(355);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertiesContext extends ParserRuleContext {
		public MapLiteralContext mapLiteral() {
			return getRuleContext(MapLiteralContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_properties);
		try {
			setState(359);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				mapLiteral();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(358);
				parameter();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipTypesContext extends ParserRuleContext {
		public List<RelTypeNameContext> relTypeName() {
			return getRuleContexts(RelTypeNameContext.class);
		}
		public RelTypeNameContext relTypeName(int i) {
			return getRuleContext(RelTypeNameContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public RelationshipTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipTypesContext relationshipTypes() throws RecognitionException {
		RelationshipTypesContext _localctx = new RelationshipTypesContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_relationshipTypes);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(T__7);
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(362);
				match(SP);
				}
			}

			setState(365);
			relTypeName();
			setState(379);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(367);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(366);
						match(SP);
						}
					}

					setState(369);
					match(T__8);
					setState(371);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__7) {
						{
						setState(370);
						match(T__7);
						}
					}

					setState(374);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(373);
						match(SP);
						}
					}

					setState(376);
					relTypeName();
					}
					} 
				}
				setState(381);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeLabelsContext extends ParserRuleContext {
		public List<NodeLabelContext> nodeLabel() {
			return getRuleContexts(NodeLabelContext.class);
		}
		public NodeLabelContext nodeLabel(int i) {
			return getRuleContext(NodeLabelContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public NodeLabelsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeLabels; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLabels(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLabels(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodeLabels(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeLabelsContext nodeLabels() throws RecognitionException {
		NodeLabelsContext _localctx = new NodeLabelsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_nodeLabels);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			nodeLabel();
			setState(389);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(384);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(383);
						match(SP);
						}
					}

					setState(386);
					nodeLabel();
					}
					} 
				}
				setState(391);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeLabelContext extends ParserRuleContext {
		public LabelNameContext labelName() {
			return getRuleContext(LabelNameContext.class,0);
		}
		public TerminalNode SP() { return getToken(CypherParser.SP, 0); }
		public NodeLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodeLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeLabelContext nodeLabel() throws RecognitionException {
		NodeLabelContext _localctx = new NodeLabelContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_nodeLabel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			match(T__7);
			setState(394);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(393);
				match(SP);
				}
			}

			setState(396);
			labelName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeLiteralContext extends ParserRuleContext {
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public List<IntegerLiteralContext> integerLiteral() {
			return getRuleContexts(IntegerLiteralContext.class);
		}
		public IntegerLiteralContext integerLiteral(int i) {
			return getRuleContext(IntegerLiteralContext.class,i);
		}
		public RangeLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRangeLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRangeLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRangeLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeLiteralContext rangeLiteral() throws RecognitionException {
		RangeLiteralContext _localctx = new RangeLiteralContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_rangeLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(T__9);
			setState(400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(399);
				match(SP);
				}
			}

			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DecimalInteger) {
				{
				setState(402);
				integerLiteral();
				setState(404);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(403);
					match(SP);
					}
				}

				}
			}

			setState(418);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(408);
				match(T__10);
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(409);
					match(SP);
					}
				}

				setState(416);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DecimalInteger) {
					{
					setState(412);
					integerLiteral();
					setState(414);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(413);
						match(SP);
						}
					}

					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelNameContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public LabelNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLabelName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLabelName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLabelName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelNameContext labelName() throws RecognitionException {
		LabelNameContext _localctx = new LabelNameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_labelName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelTypeNameContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public RelTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelTypeNameContext relTypeName() throws RecognitionException {
		RelTypeNameContext _localctx = new RelTypeNameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_relTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression10Context expression10() {
			return getRuleContext(Expression10Context.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
			expression10();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression10Context extends ParserRuleContext {
		public List<Expression9Context> expression9() {
			return getRuleContexts(Expression9Context.class);
		}
		public Expression9Context expression9(int i) {
			return getRuleContext(Expression9Context.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public List<TerminalNode> AND() { return getTokens(CypherParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(CypherParser.AND, i);
		}
		public Expression10Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression10; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression10(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression10(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression10(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression10Context expression10() throws RecognitionException {
		Expression10Context _localctx = new Expression10Context(_ctx, getState());
		enterRule(_localctx, 52, RULE_expression10);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			expression9();
			setState(433);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(427);
					match(SP);
					setState(428);
					match(AND);
					setState(429);
					match(SP);
					setState(430);
					expression9();
					}
					} 
				}
				setState(435);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression9Context extends ParserRuleContext {
		public Expression8Context expression8() {
			return getRuleContext(Expression8Context.class,0);
		}
		public List<TerminalNode> NOT() { return getTokens(CypherParser.NOT); }
		public TerminalNode NOT(int i) {
			return getToken(CypherParser.NOT, i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public Expression9Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression9; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression9(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression9(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression9(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression9Context expression9() throws RecognitionException {
		Expression9Context _localctx = new Expression9Context(_ctx, getState());
		enterRule(_localctx, 54, RULE_expression9);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(436);
					match(NOT);
					setState(438);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(437);
						match(SP);
						}
					}

					}
					} 
				}
				setState(444);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			}
			setState(445);
			expression8();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression8Context extends ParserRuleContext {
		public Expression7Context expression7() {
			return getRuleContext(Expression7Context.class,0);
		}
		public List<PartialComparisonExpressionContext> partialComparisonExpression() {
			return getRuleContexts(PartialComparisonExpressionContext.class);
		}
		public PartialComparisonExpressionContext partialComparisonExpression(int i) {
			return getRuleContext(PartialComparisonExpressionContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public Expression8Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression8; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression8(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression8(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression8(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression8Context expression8() throws RecognitionException {
		Expression8Context _localctx = new Expression8Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_expression8);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			expression7();
			setState(454);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(449);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(448);
						match(SP);
						}
					}

					setState(451);
					partialComparisonExpression();
					}
					} 
				}
				setState(456);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression7Context extends ParserRuleContext {
		public List<Expression6Context> expression6() {
			return getRuleContexts(Expression6Context.class);
		}
		public Expression6Context expression6(int i) {
			return getRuleContext(Expression6Context.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public Expression7Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression7; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression7(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression7(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression7(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression7Context expression7() throws RecognitionException {
		Expression7Context _localctx = new Expression7Context(_ctx, getState());
		enterRule(_localctx, 58, RULE_expression7);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			expression6();
			setState(476);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(474);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
					case 1:
						{
						{
						setState(459);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(458);
							match(SP);
							}
						}

						setState(461);
						match(T__11);
						setState(463);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(462);
							match(SP);
							}
						}

						setState(465);
						expression6();
						}
						}
						break;
					case 2:
						{
						{
						setState(467);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(466);
							match(SP);
							}
						}

						setState(469);
						match(T__12);
						setState(471);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(470);
							match(SP);
							}
						}

						setState(473);
						expression6();
						}
						}
						break;
					}
					} 
				}
				setState(478);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression6Context extends ParserRuleContext {
		public List<Expression5Context> expression5() {
			return getRuleContexts(Expression5Context.class);
		}
		public Expression5Context expression5(int i) {
			return getRuleContext(Expression5Context.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public Expression6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression6; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression6(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression6(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression6(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression6Context expression6() throws RecognitionException {
		Expression6Context _localctx = new Expression6Context(_ctx, getState());
		enterRule(_localctx, 60, RULE_expression6);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			expression5();
			setState(506);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(504);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
					case 1:
						{
						{
						setState(481);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(480);
							match(SP);
							}
						}

						setState(483);
						match(T__9);
						setState(485);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(484);
							match(SP);
							}
						}

						setState(487);
						expression5();
						}
						}
						break;
					case 2:
						{
						{
						setState(489);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(488);
							match(SP);
							}
						}

						setState(491);
						match(T__13);
						setState(493);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(492);
							match(SP);
							}
						}

						setState(495);
						expression5();
						}
						}
						break;
					case 3:
						{
						{
						setState(497);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(496);
							match(SP);
							}
						}

						setState(499);
						match(T__14);
						setState(501);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==SP) {
							{
							setState(500);
							match(SP);
							}
						}

						setState(503);
						expression5();
						}
						}
						break;
					}
					} 
				}
				setState(508);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression5Context extends ParserRuleContext {
		public List<Expression4Context> expression4() {
			return getRuleContexts(Expression4Context.class);
		}
		public Expression4Context expression4(int i) {
			return getRuleContext(Expression4Context.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public Expression5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression5(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression5Context expression5() throws RecognitionException {
		Expression5Context _localctx = new Expression5Context(_ctx, getState());
		enterRule(_localctx, 62, RULE_expression5);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			expression4();
			setState(520);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(511);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(510);
						match(SP);
						}
					}

					setState(513);
					match(T__15);
					setState(515);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(514);
						match(SP);
						}
					}

					setState(517);
					expression4();
					}
					} 
				}
				setState(522);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression4Context extends ParserRuleContext {
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public Expression4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression4(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression4Context expression4() throws RecognitionException {
		Expression4Context _localctx = new Expression4Context(_ctx, getState());
		enterRule(_localctx, 64, RULE_expression4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11 || _la==T__12) {
				{
				{
				setState(523);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(525);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(524);
					match(SP);
					}
				}

				}
				}
				setState(531);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(532);
			expression3();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression3Context extends ParserRuleContext {
		public Expression2Context expression2() {
			return getRuleContext(Expression2Context.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode SP() { return getToken(CypherParser.SP, 0); }
		public Expression3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression3Context expression3() throws RecognitionException {
		Expression3Context _localctx = new Expression3Context(_ctx, getState());
		enterRule(_localctx, 66, RULE_expression3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			expression2();
			setState(554);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				{
				{
				setState(536);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(535);
					match(SP);
					}
				}

				setState(538);
				match(T__5);
				setState(539);
				expression();
				setState(540);
				match(T__6);
				}
				}
				break;
			case 2:
				{
				{
				setState(543);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(542);
					match(SP);
					}
				}

				setState(545);
				match(T__5);
				setState(547);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__5) | (1L << T__11) | (1L << T__12) | (1L << T__16) | (1L << T__24) | (1L << StringLiteral) | (1L << DecimalInteger) | (1L << RegularDecimalReal) | (1L << COUNT) | (1L << NULL) | (1L << OR) | (1L << MATCH) | (1L << RETURN) | (1L << WHERE) | (1L << AND) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << UnescapedSymbolicName))) != 0)) {
					{
					setState(546);
					expression();
					}
				}

				setState(549);
				match(T__10);
				setState(551);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__5) | (1L << T__11) | (1L << T__12) | (1L << T__16) | (1L << T__24) | (1L << StringLiteral) | (1L << DecimalInteger) | (1L << RegularDecimalReal) | (1L << COUNT) | (1L << NULL) | (1L << OR) | (1L << MATCH) | (1L << RETURN) | (1L << WHERE) | (1L << AND) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << UnescapedSymbolicName))) != 0)) {
					{
					setState(550);
					expression();
					}
				}

				setState(553);
				match(T__6);
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression2Context extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<PropertyLookupContext> propertyLookup() {
			return getRuleContexts(PropertyLookupContext.class);
		}
		public PropertyLookupContext propertyLookup(int i) {
			return getRuleContext(PropertyLookupContext.class,i);
		}
		public List<NodeLabelsContext> nodeLabels() {
			return getRuleContexts(NodeLabelsContext.class);
		}
		public NodeLabelsContext nodeLabels(int i) {
			return getRuleContext(NodeLabelsContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public Expression2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression2Context expression2() throws RecognitionException {
		Expression2Context _localctx = new Expression2Context(_ctx, getState());
		enterRule(_localctx, 68, RULE_expression2);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(556);
			atom();
			setState(566);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(558);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(557);
						match(SP);
						}
					}

					setState(562);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__25:
						{
						setState(560);
						propertyLookup();
						}
						break;
					case T__7:
						{
						setState(561);
						nodeLabels();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(568);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public RelationshipsPatternContext relationshipsPattern() {
			return getRuleContext(RelationshipsPatternContext.class,0);
		}
		public ParenthesizedExpressionContext parenthesizedExpression() {
			return getRuleContext(ParenthesizedExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_atom);
		int _la;
		try {
			setState(587);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(569);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(570);
				parameter();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(571);
				match(COUNT);
				setState(573);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(572);
					match(SP);
					}
				}

				setState(575);
				match(T__3);
				setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(576);
					match(SP);
					}
				}

				setState(579);
				match(T__9);
				setState(581);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(580);
					match(SP);
					}
				}

				setState(583);
				match(T__4);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(584);
				relationshipsPattern();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(585);
				parenthesizedExpression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(586);
				variable();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public NumberLiteralContext numberLiteral() {
			return getRuleContext(NumberLiteralContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(CypherParser.StringLiteral, 0); }
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }
		public MapLiteralContext mapLiteral() {
			return getRuleContext(MapLiteralContext.class,0);
		}
		public ListLiteralContext listLiteral() {
			return getRuleContext(ListLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_literal);
		try {
			setState(595);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DecimalInteger:
			case RegularDecimalReal:
				enterOuterAlt(_localctx, 1);
				{
				setState(589);
				numberLiteral();
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(590);
				match(StringLiteral);
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(591);
				booleanLiteral();
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(592);
				match(NULL);
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 5);
				{
				setState(593);
				mapLiteral();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 6);
				{
				setState(594);
				listLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberLiteralContext extends ParserRuleContext {
		public DoubleLiteralContext doubleLiteral() {
			return getRuleContext(DoubleLiteralContext.class,0);
		}
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public NumberLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNumberLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNumberLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberLiteralContext numberLiteral() throws RecognitionException {
		NumberLiteralContext _localctx = new NumberLiteralContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_numberLiteral);
		try {
			setState(599);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RegularDecimalReal:
				enterOuterAlt(_localctx, 1);
				{
				setState(597);
				doubleLiteral();
				}
				break;
			case DecimalInteger:
				enterOuterAlt(_localctx, 2);
				{
				setState(598);
				integerLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanLiteralContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitBooleanLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(601);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListLiteralContext extends ParserRuleContext {
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ListLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterListLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitListLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitListLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListLiteralContext listLiteral() throws RecognitionException {
		ListLiteralContext _localctx = new ListLiteralContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_listLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(603);
			match(T__5);
			setState(605);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(604);
				match(SP);
				}
			}

			setState(624);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__5) | (1L << T__11) | (1L << T__12) | (1L << T__16) | (1L << T__24) | (1L << StringLiteral) | (1L << DecimalInteger) | (1L << RegularDecimalReal) | (1L << COUNT) | (1L << NULL) | (1L << OR) | (1L << MATCH) | (1L << RETURN) | (1L << WHERE) | (1L << AND) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << UnescapedSymbolicName))) != 0)) {
				{
				setState(607);
				expression();
				setState(609);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(608);
					match(SP);
					}
				}

				setState(621);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(611);
					match(T__1);
					setState(613);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(612);
						match(SP);
						}
					}

					setState(615);
					expression();
					setState(617);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(616);
						match(SP);
						}
					}

					}
					}
					setState(623);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(626);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapLiteralContext extends ParserRuleContext {
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public List<PropertyKeyNameContext> propertyKeyName() {
			return getRuleContexts(PropertyKeyNameContext.class);
		}
		public PropertyKeyNameContext propertyKeyName(int i) {
			return getRuleContext(PropertyKeyNameContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MapLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMapLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMapLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMapLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapLiteralContext mapLiteral() throws RecognitionException {
		MapLiteralContext _localctx = new MapLiteralContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_mapLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(628);
			match(T__16);
			setState(630);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(629);
				match(SP);
				}
			}

			setState(665);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << MATCH) | (1L << RETURN) | (1L << WHERE) | (1L << AND) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << UnescapedSymbolicName))) != 0)) {
				{
				setState(632);
				propertyKeyName();
				setState(634);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(633);
					match(SP);
					}
				}

				setState(636);
				match(T__7);
				setState(638);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(637);
					match(SP);
					}
				}

				setState(640);
				expression();
				setState(642);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(641);
					match(SP);
					}
				}

				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(644);
					match(T__1);
					setState(646);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(645);
						match(SP);
						}
					}

					setState(648);
					propertyKeyName();
					setState(650);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(649);
						match(SP);
						}
					}

					setState(652);
					match(T__7);
					setState(654);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(653);
						match(SP);
						}
					}

					setState(656);
					expression();
					setState(658);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(657);
						match(SP);
						}
					}

					}
					}
					setState(664);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(667);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartialComparisonExpressionContext extends ParserRuleContext {
		public Expression7Context expression7() {
			return getRuleContext(Expression7Context.class,0);
		}
		public TerminalNode SP() { return getToken(CypherParser.SP, 0); }
		public PartialComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partialComparisonExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPartialComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPartialComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPartialComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartialComparisonExpressionContext partialComparisonExpression() throws RecognitionException {
		PartialComparisonExpressionContext _localctx = new PartialComparisonExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_partialComparisonExpression);
		int _la;
		try {
			setState(704);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(669);
				match(T__2);
				setState(671);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(670);
					match(SP);
					}
				}

				setState(673);
				expression7();
				}
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(674);
				match(T__18);
				setState(676);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(675);
					match(SP);
					}
				}

				setState(678);
				expression7();
				}
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(679);
				match(T__19);
				setState(681);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(680);
					match(SP);
					}
				}

				setState(683);
				expression7();
				}
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(684);
				match(T__20);
				setState(686);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(685);
					match(SP);
					}
				}

				setState(688);
				expression7();
				}
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(689);
				match(T__21);
				setState(691);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(690);
					match(SP);
					}
				}

				setState(693);
				expression7();
				}
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(694);
				match(T__22);
				setState(696);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(695);
					match(SP);
					}
				}

				setState(698);
				expression7();
				}
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(699);
				match(T__23);
				setState(701);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SP) {
					{
					setState(700);
					match(SP);
					}
				}

				setState(703);
				expression7();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenthesizedExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public ParenthesizedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesizedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesizedExpressionContext parenthesizedExpression() throws RecognitionException {
		ParenthesizedExpressionContext _localctx = new ParenthesizedExpressionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_parenthesizedExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			match(T__3);
			setState(708);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(707);
				match(SP);
				}
			}

			setState(710);
			expression();
			setState(712);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(711);
				match(SP);
				}
			}

			setState(714);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public TerminalNode DecimalInteger() { return getToken(CypherParser.DecimalInteger, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(716);
			match(T__24);
			setState(719);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OR:
			case MATCH:
			case RETURN:
			case WHERE:
			case AND:
			case NOT:
			case TRUE:
			case FALSE:
			case UnescapedSymbolicName:
				{
				setState(717);
				symbolicName();
				}
				break;
			case DecimalInteger:
				{
				setState(718);
				match(DecimalInteger);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipsPatternContext extends ParserRuleContext {
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public List<PatternElementChainContext> patternElementChain() {
			return getRuleContexts(PatternElementChainContext.class);
		}
		public PatternElementChainContext patternElementChain(int i) {
			return getRuleContext(PatternElementChainContext.class,i);
		}
		public List<TerminalNode> SP() { return getTokens(CypherParser.SP); }
		public TerminalNode SP(int i) {
			return getToken(CypherParser.SP, i);
		}
		public RelationshipsPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipsPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipsPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipsPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipsPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipsPatternContext relationshipsPattern() throws RecognitionException {
		RelationshipsPatternContext _localctx = new RelationshipsPatternContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_relationshipsPattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(721);
			nodePattern();
			setState(726); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(723);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SP) {
						{
						setState(722);
						match(SP);
						}
					}

					setState(725);
					patternElementChain();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(728); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,141,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyLookupContext extends ParserRuleContext {
		public PropertyKeyNameContext propertyKeyName() {
			return getRuleContext(PropertyKeyNameContext.class,0);
		}
		public TerminalNode SP() { return getToken(CypherParser.SP, 0); }
		public PropertyLookupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyLookup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyLookup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyLookup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPropertyLookup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyLookupContext propertyLookup() throws RecognitionException {
		PropertyLookupContext _localctx = new PropertyLookupContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_propertyLookup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(730);
			match(T__25);
			setState(732);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SP) {
				{
				setState(731);
				match(SP);
				}
			}

			{
			setState(734);
			propertyKeyName();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyKeyNameContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public PropertyKeyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyKeyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyKeyName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyKeyName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPropertyKeyName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyKeyNameContext propertyKeyName() throws RecognitionException {
		PropertyKeyNameContext _localctx = new PropertyKeyNameContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_propertyKeyName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(738);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode DecimalInteger() { return getToken(CypherParser.DecimalInteger, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_integerLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(740);
			match(DecimalInteger);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleLiteralContext extends ParserRuleContext {
		public TerminalNode RegularDecimalReal() { return getToken(CypherParser.RegularDecimalReal, 0); }
		public DoubleLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDoubleLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDoubleLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDoubleLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleLiteralContext doubleLiteral() throws RecognitionException {
		DoubleLiteralContext _localctx = new DoubleLiteralContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_doubleLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(742);
			match(RegularDecimalReal);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolicNameContext extends ParserRuleContext {
		public TerminalNode UnescapedSymbolicName() { return getToken(CypherParser.UnescapedSymbolicName, 0); }
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public TerminalNode OR() { return getToken(CypherParser.OR, 0); }
		public TerminalNode AND() { return getToken(CypherParser.AND, 0); }
		public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public SymbolicNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolicName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSymbolicName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSymbolicName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSymbolicName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolicNameContext symbolicName() throws RecognitionException {
		SymbolicNameContext _localctx = new SymbolicNameContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_symbolicName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(744);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << MATCH) | (1L << RETURN) | (1L << WHERE) | (1L << AND) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << UnescapedSymbolicName))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftArrowHeadContext extends ParserRuleContext {
		public LeftArrowHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftArrowHead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLeftArrowHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLeftArrowHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLeftArrowHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftArrowHeadContext leftArrowHead() throws RecognitionException {
		LeftArrowHeadContext _localctx = new LeftArrowHeadContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_leftArrowHead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(746);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightArrowHeadContext extends ParserRuleContext {
		public RightArrowHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightArrowHead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRightArrowHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRightArrowHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRightArrowHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightArrowHeadContext rightArrowHead() throws RecognitionException {
		RightArrowHeadContext _localctx = new RightArrowHeadContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_rightArrowHead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(748);
			match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DashContext extends ParserRuleContext {
		public DashContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dash; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDash(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDash(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DashContext dash() throws RecognitionException {
		DashContext _localctx = new DashContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_dash);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(750);
			match(T__12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\64\u02f3\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\5\2p\n\2\3\2\3\2\5\2t\n\2\3\2\5"+
		"\2w\n\2\3\2\5\2z\n\2\3\3\3\3\3\4\3\4\5\4\u0080\n\4\3\4\7\4\u0083\n\4\f"+
		"\4\16\4\u0086\13\4\3\5\3\5\5\5\u008a\n\5\3\6\3\6\5\6\u008e\n\6\3\6\3\6"+
		"\5\6\u0092\n\6\3\6\5\6\u0095\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\5\t\u009e"+
		"\n\t\3\t\3\t\5\t\u00a2\n\t\3\t\7\t\u00a5\n\t\f\t\16\t\u00a8\13\t\3\t\3"+
		"\t\5\t\u00ac\n\t\3\t\3\t\5\t\u00b0\n\t\3\t\7\t\u00b3\n\t\f\t\16\t\u00b6"+
		"\13\t\5\t\u00b8\n\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\5\f\u00c2\n\f"+
		"\3\f\3\f\5\f\u00c6\n\f\3\f\7\f\u00c9\n\f\f\f\16\f\u00cc\13\f\3\r\3\r\5"+
		"\r\u00d0\n\r\3\r\3\r\5\r\u00d4\n\r\3\r\3\r\3\r\5\r\u00d9\n\r\3\16\3\16"+
		"\3\17\3\17\5\17\u00df\n\17\3\17\7\17\u00e2\n\17\f\17\16\17\u00e5\13\17"+
		"\3\17\3\17\3\17\3\17\5\17\u00eb\n\17\3\20\3\20\5\20\u00ef\n\20\3\20\3"+
		"\20\5\20\u00f3\n\20\5\20\u00f5\n\20\3\20\3\20\5\20\u00f9\n\20\5\20\u00fb"+
		"\n\20\3\20\3\20\5\20\u00ff\n\20\5\20\u0101\n\20\3\20\3\20\3\21\3\21\5"+
		"\21\u0107\n\21\3\21\3\21\3\22\3\22\5\22\u010d\n\22\3\22\3\22\5\22\u0111"+
		"\n\22\3\22\5\22\u0114\n\22\3\22\5\22\u0117\n\22\3\22\3\22\5\22\u011b\n"+
		"\22\3\22\3\22\3\22\3\22\5\22\u0121\n\22\3\22\3\22\5\22\u0125\n\22\3\22"+
		"\5\22\u0128\n\22\3\22\5\22\u012b\n\22\3\22\3\22\3\22\3\22\5\22\u0131\n"+
		"\22\3\22\5\22\u0134\n\22\3\22\5\22\u0137\n\22\3\22\3\22\5\22\u013b\n\22"+
		"\3\22\3\22\3\22\3\22\5\22\u0141\n\22\3\22\5\22\u0144\n\22\3\22\5\22\u0147"+
		"\n\22\3\22\3\22\5\22\u014b\n\22\3\23\3\23\5\23\u014f\n\23\3\23\3\23\5"+
		"\23\u0153\n\23\5\23\u0155\n\23\3\23\3\23\5\23\u0159\n\23\5\23\u015b\n"+
		"\23\3\23\5\23\u015e\n\23\3\23\3\23\5\23\u0162\n\23\5\23\u0164\n\23\3\23"+
		"\3\23\3\24\3\24\5\24\u016a\n\24\3\25\3\25\5\25\u016e\n\25\3\25\3\25\5"+
		"\25\u0172\n\25\3\25\3\25\5\25\u0176\n\25\3\25\5\25\u0179\n\25\3\25\7\25"+
		"\u017c\n\25\f\25\16\25\u017f\13\25\3\26\3\26\5\26\u0183\n\26\3\26\7\26"+
		"\u0186\n\26\f\26\16\26\u0189\13\26\3\27\3\27\5\27\u018d\n\27\3\27\3\27"+
		"\3\30\3\30\5\30\u0193\n\30\3\30\3\30\5\30\u0197\n\30\5\30\u0199\n\30\3"+
		"\30\3\30\5\30\u019d\n\30\3\30\3\30\5\30\u01a1\n\30\5\30\u01a3\n\30\5\30"+
		"\u01a5\n\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\7\34"+
		"\u01b2\n\34\f\34\16\34\u01b5\13\34\3\35\3\35\5\35\u01b9\n\35\7\35\u01bb"+
		"\n\35\f\35\16\35\u01be\13\35\3\35\3\35\3\36\3\36\5\36\u01c4\n\36\3\36"+
		"\7\36\u01c7\n\36\f\36\16\36\u01ca\13\36\3\37\3\37\5\37\u01ce\n\37\3\37"+
		"\3\37\5\37\u01d2\n\37\3\37\3\37\5\37\u01d6\n\37\3\37\3\37\5\37\u01da\n"+
		"\37\3\37\7\37\u01dd\n\37\f\37\16\37\u01e0\13\37\3 \3 \5 \u01e4\n \3 \3"+
		" \5 \u01e8\n \3 \3 \5 \u01ec\n \3 \3 \5 \u01f0\n \3 \3 \5 \u01f4\n \3"+
		" \3 \5 \u01f8\n \3 \7 \u01fb\n \f \16 \u01fe\13 \3!\3!\5!\u0202\n!\3!"+
		"\3!\5!\u0206\n!\3!\7!\u0209\n!\f!\16!\u020c\13!\3\"\3\"\5\"\u0210\n\""+
		"\7\"\u0212\n\"\f\"\16\"\u0215\13\"\3\"\3\"\3#\3#\5#\u021b\n#\3#\3#\3#"+
		"\3#\3#\5#\u0222\n#\3#\3#\5#\u0226\n#\3#\3#\5#\u022a\n#\3#\5#\u022d\n#"+
		"\3$\3$\5$\u0231\n$\3$\3$\5$\u0235\n$\7$\u0237\n$\f$\16$\u023a\13$\3%\3"+
		"%\3%\3%\5%\u0240\n%\3%\3%\5%\u0244\n%\3%\3%\5%\u0248\n%\3%\3%\3%\3%\5"+
		"%\u024e\n%\3&\3&\3&\3&\3&\3&\5&\u0256\n&\3\'\3\'\5\'\u025a\n\'\3(\3(\3"+
		")\3)\5)\u0260\n)\3)\3)\5)\u0264\n)\3)\3)\5)\u0268\n)\3)\3)\5)\u026c\n"+
		")\7)\u026e\n)\f)\16)\u0271\13)\5)\u0273\n)\3)\3)\3*\3*\5*\u0279\n*\3*"+
		"\3*\5*\u027d\n*\3*\3*\5*\u0281\n*\3*\3*\5*\u0285\n*\3*\3*\5*\u0289\n*"+
		"\3*\3*\5*\u028d\n*\3*\3*\5*\u0291\n*\3*\3*\5*\u0295\n*\7*\u0297\n*\f*"+
		"\16*\u029a\13*\5*\u029c\n*\3*\3*\3+\3+\5+\u02a2\n+\3+\3+\3+\5+\u02a7\n"+
		"+\3+\3+\3+\5+\u02ac\n+\3+\3+\3+\5+\u02b1\n+\3+\3+\3+\5+\u02b6\n+\3+\3"+
		"+\3+\5+\u02bb\n+\3+\3+\3+\5+\u02c0\n+\3+\5+\u02c3\n+\3,\3,\5,\u02c7\n"+
		",\3,\3,\5,\u02cb\n,\3,\3,\3-\3-\3-\5-\u02d2\n-\3.\3.\5.\u02d6\n.\3.\6"+
		".\u02d9\n.\r.\16.\u02da\3/\3/\5/\u02df\n/\3/\3/\3\60\3\60\3\61\3\61\3"+
		"\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\3\67\2\28\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL"+
		"NPRTVXZ\\^`bdfhjl\2\5\3\2\16\17\3\2-.\3\2\'/\u035b\2o\3\2\2\2\4{\3\2\2"+
		"\2\6}\3\2\2\2\b\u0089\3\2\2\2\n\u008b\3\2\2\2\f\u0096\3\2\2\2\16\u009a"+
		"\3\2\2\2\20\u00b7\3\2\2\2\22\u00b9\3\2\2\2\24\u00bb\3\2\2\2\26\u00bf\3"+
		"\2\2\2\30\u00d8\3\2\2\2\32\u00da\3\2\2\2\34\u00ea\3\2\2\2\36\u00ec\3\2"+
		"\2\2 \u0104\3\2\2\2\"\u014a\3\2\2\2$\u014c\3\2\2\2&\u0169\3\2\2\2(\u016b"+
		"\3\2\2\2*\u0180\3\2\2\2,\u018a\3\2\2\2.\u0190\3\2\2\2\60\u01a6\3\2\2\2"+
		"\62\u01a8\3\2\2\2\64\u01aa\3\2\2\2\66\u01ac\3\2\2\28\u01bc\3\2\2\2:\u01c1"+
		"\3\2\2\2<\u01cb\3\2\2\2>\u01e1\3\2\2\2@\u01ff\3\2\2\2B\u0213\3\2\2\2D"+
		"\u0218\3\2\2\2F\u022e\3\2\2\2H\u024d\3\2\2\2J\u0255\3\2\2\2L\u0259\3\2"+
		"\2\2N\u025b\3\2\2\2P\u025d\3\2\2\2R\u0276\3\2\2\2T\u02c2\3\2\2\2V\u02c4"+
		"\3\2\2\2X\u02ce\3\2\2\2Z\u02d3\3\2\2\2\\\u02dc\3\2\2\2^\u02e2\3\2\2\2"+
		"`\u02e4\3\2\2\2b\u02e6\3\2\2\2d\u02e8\3\2\2\2f\u02ea\3\2\2\2h\u02ec\3"+
		"\2\2\2j\u02ee\3\2\2\2l\u02f0\3\2\2\2np\7\62\2\2on\3\2\2\2op\3\2\2\2pq"+
		"\3\2\2\2qv\5\4\3\2rt\7\62\2\2sr\3\2\2\2st\3\2\2\2tu\3\2\2\2uw\7\3\2\2"+
		"vs\3\2\2\2vw\3\2\2\2wy\3\2\2\2xz\7\62\2\2yx\3\2\2\2yz\3\2\2\2z\3\3\2\2"+
		"\2{|\5\6\4\2|\5\3\2\2\2}\u0084\5\b\5\2~\u0080\7\62\2\2\177~\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\5\b\5\2\u0082\177\3\2\2"+
		"\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\7"+
		"\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u008a\5\n\6\2\u0088\u008a\5\f\7\2\u0089"+
		"\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a\t\3\2\2\2\u008b\u008d\7(\2\2"+
		"\u008c\u008e\7\62\2\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\u0094\5\26\f\2\u0090\u0092\7\62\2\2\u0091\u0090\3\2\2\2"+
		"\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\5\24\13\2\u0094\u0091"+
		"\3\2\2\2\u0094\u0095\3\2\2\2\u0095\13\3\2\2\2\u0096\u0097\7)\2\2\u0097"+
		"\u0098\7\62\2\2\u0098\u0099\5\16\b\2\u0099\r\3\2\2\2\u009a\u009b\5\20"+
		"\t\2\u009b\17\3\2\2\2\u009c\u009e\7\62\2\2\u009d\u009c\3\2\2\2\u009d\u009e"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\7\4\2\2\u00a0\u00a2\7\62\2\2"+
		"\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5"+
		"\5\22\n\2\u00a4\u009d\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2"+
		"\u00a6\u00a7\3\2\2\2\u00a7\u00b8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00b4"+
		"\5\22\n\2\u00aa\u00ac\7\62\2\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2"+
		"\u00ac\u00ad\3\2\2\2\u00ad\u00af\7\4\2\2\u00ae\u00b0\7\62\2\2\u00af\u00ae"+
		"\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\5\22\n\2"+
		"\u00b2\u00ab\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00a6\3\2\2\2\u00b7"+
		"\u00a9\3\2\2\2\u00b8\21\3\2\2\2\u00b9\u00ba\5\64\33\2\u00ba\23\3\2\2\2"+
		"\u00bb\u00bc\7*\2\2\u00bc\u00bd\7\62\2\2\u00bd\u00be\5\64\33\2\u00be\25"+
		"\3\2\2\2\u00bf\u00ca\5\30\r\2\u00c0\u00c2\7\62\2\2\u00c1\u00c0\3\2\2\2"+
		"\u00c1\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c5\7\4\2\2\u00c4\u00c6"+
		"\7\62\2\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2"+
		"\u00c7\u00c9\5\30\r\2\u00c8\u00c1\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\27\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd"+
		"\u00cf\5`\61\2\u00ce\u00d0\7\62\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3"+
		"\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\7\5\2\2\u00d2\u00d4\7\62\2\2\u00d3"+
		"\u00d2\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\5\32"+
		"\16\2\u00d6\u00d9\3\2\2\2\u00d7\u00d9\5\32\16\2\u00d8\u00cd\3\2\2\2\u00d8"+
		"\u00d7\3\2\2\2\u00d9\31\3\2\2\2\u00da\u00db\5\34\17\2\u00db\33\3\2\2\2"+
		"\u00dc\u00e3\5\36\20\2\u00dd\u00df\7\62\2\2\u00de\u00dd\3\2\2\2\u00de"+
		"\u00df\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\5 \21\2\u00e1\u00de\3\2"+
		"\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		"\u00eb\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7\6\2\2\u00e7\u00e8\5\34"+
		"\17\2\u00e8\u00e9\7\7\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00dc\3\2\2\2\u00ea"+
		"\u00e6\3\2\2\2\u00eb\35\3\2\2\2\u00ec\u00ee\7\6\2\2\u00ed\u00ef\7\62\2"+
		"\2\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f4\3\2\2\2\u00f0\u00f2"+
		"\5`\61\2\u00f1\u00f3\7\62\2\2\u00f2\u00f1\3\2\2\2\u00f2\u00f3\3\2\2\2"+
		"\u00f3\u00f5\3\2\2\2\u00f4\u00f0\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00fa"+
		"\3\2\2\2\u00f6\u00f8\5*\26\2\u00f7\u00f9\7\62\2\2\u00f8\u00f7\3\2\2\2"+
		"\u00f8\u00f9\3\2\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00f6\3\2\2\2\u00fa\u00fb"+
		"\3\2\2\2\u00fb\u0100\3\2\2\2\u00fc\u00fe\5&\24\2\u00fd\u00ff\7\62\2\2"+
		"\u00fe\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3\2\2\2\u0100\u00fc"+
		"\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\7\7\2\2\u0103"+
		"\37\3\2\2\2\u0104\u0106\5\"\22\2\u0105\u0107\7\62\2\2\u0106\u0105\3\2"+
		"\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109\5\36\20\2\u0109"+
		"!\3\2\2\2\u010a\u010c\5h\65\2\u010b\u010d\7\62\2\2\u010c\u010b\3\2\2\2"+
		"\u010c\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u0110\5l\67\2\u010f\u0111"+
		"\7\62\2\2\u0110\u010f\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2"+
		"\u0112\u0114\5$\23\2\u0113\u0112\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116"+
		"\3\2\2\2\u0115\u0117\7\62\2\2\u0116\u0115\3\2\2\2\u0116\u0117\3\2\2\2"+
		"\u0117\u0118\3\2\2\2\u0118\u011a\5l\67\2\u0119\u011b\7\62\2\2\u011a\u0119"+
		"\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\5j\66\2\u011d"+
		"\u014b\3\2\2\2\u011e\u0120\5h\65\2\u011f\u0121\7\62\2\2\u0120\u011f\3"+
		"\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\5l\67\2\u0123"+
		"\u0125\7\62\2\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0127\3"+
		"\2\2\2\u0126\u0128\5$\23\2\u0127\u0126\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\u012a\3\2\2\2\u0129\u012b\7\62\2\2\u012a\u0129\3\2\2\2\u012a\u012b\3"+
		"\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\5l\67\2\u012d\u014b\3\2\2\2\u012e"+
		"\u0130\5l\67\2\u012f\u0131\7\62\2\2\u0130\u012f\3\2\2\2\u0130\u0131\3"+
		"\2\2\2\u0131\u0133\3\2\2\2\u0132\u0134\5$\23\2\u0133\u0132\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0136\3\2\2\2\u0135\u0137\7\62\2\2\u0136\u0135\3"+
		"\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u013a\5l\67\2\u0139"+
		"\u013b\7\62\2\2\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\3"+
		"\2\2\2\u013c\u013d\5j\66\2\u013d\u014b\3\2\2\2\u013e\u0140\5l\67\2\u013f"+
		"\u0141\7\62\2\2\u0140\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0143\3"+
		"\2\2\2\u0142\u0144\5$\23\2\u0143\u0142\3\2\2\2\u0143\u0144\3\2\2\2\u0144"+
		"\u0146\3\2\2\2\u0145\u0147\7\62\2\2\u0146\u0145\3\2\2\2\u0146\u0147\3"+
		"\2\2\2\u0147\u0148\3\2\2\2\u0148\u0149\5l\67\2\u0149\u014b\3\2\2\2\u014a"+
		"\u010a\3\2\2\2\u014a\u011e\3\2\2\2\u014a\u012e\3\2\2\2\u014a\u013e\3\2"+
		"\2\2\u014b#\3\2\2\2\u014c\u014e\7\b\2\2\u014d\u014f\7\62\2\2\u014e\u014d"+
		"\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0154\3\2\2\2\u0150\u0152\5`\61\2\u0151"+
		"\u0153\7\62\2\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0155\3"+
		"\2\2\2\u0154\u0150\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u015a\3\2\2\2\u0156"+
		"\u0158\5(\25\2\u0157\u0159\7\62\2\2\u0158\u0157\3\2\2\2\u0158\u0159\3"+
		"\2\2\2\u0159\u015b\3\2\2\2\u015a\u0156\3\2\2\2\u015a\u015b\3\2\2\2\u015b"+
		"\u015d\3\2\2\2\u015c\u015e\5.\30\2\u015d\u015c\3\2\2\2\u015d\u015e\3\2"+
		"\2\2\u015e\u0163\3\2\2\2\u015f\u0161\5&\24\2\u0160\u0162\7\62\2\2\u0161"+
		"\u0160\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0164\3\2\2\2\u0163\u015f\3\2"+
		"\2\2\u0163\u0164\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166\7\t\2\2\u0166"+
		"%\3\2\2\2\u0167\u016a\5R*\2\u0168\u016a\5X-\2\u0169\u0167\3\2\2\2\u0169"+
		"\u0168\3\2\2\2\u016a\'\3\2\2\2\u016b\u016d\7\n\2\2\u016c\u016e\7\62\2"+
		"\2\u016d\u016c\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u017d"+
		"\5\62\32\2\u0170\u0172\7\62\2\2\u0171\u0170\3\2\2\2\u0171\u0172\3\2\2"+
		"\2\u0172\u0173\3\2\2\2\u0173\u0175\7\13\2\2\u0174\u0176\7\n\2\2\u0175"+
		"\u0174\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0178\3\2\2\2\u0177\u0179\7\62"+
		"\2\2\u0178\u0177\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017a\3\2\2\2\u017a"+
		"\u017c\5\62\32\2\u017b\u0171\3\2\2\2\u017c\u017f\3\2\2\2\u017d\u017b\3"+
		"\2\2\2\u017d\u017e\3\2\2\2\u017e)\3\2\2\2\u017f\u017d\3\2\2\2\u0180\u0187"+
		"\5,\27\2\u0181\u0183\7\62\2\2\u0182\u0181\3\2\2\2\u0182\u0183\3\2\2\2"+
		"\u0183\u0184\3\2\2\2\u0184\u0186\5,\27\2\u0185\u0182\3\2\2\2\u0186\u0189"+
		"\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188+\3\2\2\2\u0189"+
		"\u0187\3\2\2\2\u018a\u018c\7\n\2\2\u018b\u018d\7\62\2\2\u018c\u018b\3"+
		"\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\5\60\31\2\u018f"+
		"-\3\2\2\2\u0190\u0192\7\f\2\2\u0191\u0193\7\62\2\2\u0192\u0191\3\2\2\2"+
		"\u0192\u0193\3\2\2\2\u0193\u0198\3\2\2\2\u0194\u0196\5b\62\2\u0195\u0197"+
		"\7\62\2\2\u0196\u0195\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0199\3\2\2\2"+
		"\u0198\u0194\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u01a4\3\2\2\2\u019a\u019c"+
		"\7\r\2\2\u019b\u019d\7\62\2\2\u019c\u019b\3\2\2\2\u019c\u019d\3\2\2\2"+
		"\u019d\u01a2\3\2\2\2\u019e\u01a0\5b\62\2\u019f\u01a1\7\62\2\2\u01a0\u019f"+
		"\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a3\3\2\2\2\u01a2\u019e\3\2\2\2\u01a2"+
		"\u01a3\3\2\2\2\u01a3\u01a5\3\2\2\2\u01a4\u019a\3\2\2\2\u01a4\u01a5\3\2"+
		"\2\2\u01a5/\3\2\2\2\u01a6\u01a7\5f\64\2\u01a7\61\3\2\2\2\u01a8\u01a9\5"+
		"f\64\2\u01a9\63\3\2\2\2\u01aa\u01ab\5\66\34\2\u01ab\65\3\2\2\2\u01ac\u01b3"+
		"\58\35\2\u01ad\u01ae\7\62\2\2\u01ae\u01af\7+\2\2\u01af\u01b0\7\62\2\2"+
		"\u01b0\u01b2\58\35\2\u01b1\u01ad\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1"+
		"\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4\67\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b6"+
		"\u01b8\7,\2\2\u01b7\u01b9\7\62\2\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9\3\2"+
		"\2\2\u01b9\u01bb\3\2\2\2\u01ba\u01b6\3\2\2\2\u01bb\u01be\3\2\2\2\u01bc"+
		"\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01bf\3\2\2\2\u01be\u01bc\3\2"+
		"\2\2\u01bf\u01c0\5:\36\2\u01c09\3\2\2\2\u01c1\u01c8\5<\37\2\u01c2\u01c4"+
		"\7\62\2\2\u01c3\u01c2\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5\3\2\2\2"+
		"\u01c5\u01c7\5T+\2\u01c6\u01c3\3\2\2\2\u01c7\u01ca\3\2\2\2\u01c8\u01c6"+
		"\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9;\3\2\2\2\u01ca\u01c8\3\2\2\2\u01cb"+
		"\u01de\5> \2\u01cc\u01ce\7\62\2\2\u01cd\u01cc\3\2\2\2\u01cd\u01ce\3\2"+
		"\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d1\7\16\2\2\u01d0\u01d2\7\62\2\2\u01d1"+
		"\u01d0\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3\u01dd\5>"+
		" \2\u01d4\u01d6\7\62\2\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6"+
		"\u01d7\3\2\2\2\u01d7\u01d9\7\17\2\2\u01d8\u01da\7\62\2\2\u01d9\u01d8\3"+
		"\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u01dd\5> \2\u01dc"+
		"\u01cd\3\2\2\2\u01dc\u01d5\3\2\2\2\u01dd\u01e0\3\2\2\2\u01de\u01dc\3\2"+
		"\2\2\u01de\u01df\3\2\2\2\u01df=\3\2\2\2\u01e0\u01de\3\2\2\2\u01e1\u01fc"+
		"\5@!\2\u01e2\u01e4\7\62\2\2\u01e3\u01e2\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4"+
		"\u01e5\3\2\2\2\u01e5\u01e7\7\f\2\2\u01e6\u01e8\7\62\2\2\u01e7\u01e6\3"+
		"\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01fb\5@!\2\u01ea"+
		"\u01ec\7\62\2\2\u01eb\u01ea\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed\3"+
		"\2\2\2\u01ed\u01ef\7\20\2\2\u01ee\u01f0\7\62\2\2\u01ef\u01ee\3\2\2\2\u01ef"+
		"\u01f0\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01fb\5@!\2\u01f2\u01f4\7\62"+
		"\2\2\u01f3\u01f2\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5"+
		"\u01f7\7\21\2\2\u01f6\u01f8\7\62\2\2\u01f7\u01f6\3\2\2\2\u01f7\u01f8\3"+
		"\2\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fb\5@!\2\u01fa\u01e3\3\2\2\2\u01fa"+
		"\u01eb\3\2\2\2\u01fa\u01f3\3\2\2\2\u01fb\u01fe\3\2\2\2\u01fc\u01fa\3\2"+
		"\2\2\u01fc\u01fd\3\2\2\2\u01fd?\3\2\2\2\u01fe\u01fc\3\2\2\2\u01ff\u020a"+
		"\5B\"\2\u0200\u0202\7\62\2\2\u0201\u0200\3\2\2\2\u0201\u0202\3\2\2\2\u0202"+
		"\u0203\3\2\2\2\u0203\u0205\7\22\2\2\u0204\u0206\7\62\2\2\u0205\u0204\3"+
		"\2\2\2\u0205\u0206\3\2\2\2\u0206\u0207\3\2\2\2\u0207\u0209\5B\"\2\u0208"+
		"\u0201\3\2\2\2\u0209\u020c\3\2\2\2\u020a\u0208\3\2\2\2\u020a\u020b\3\2"+
		"\2\2\u020bA\3\2\2\2\u020c\u020a\3\2\2\2\u020d\u020f\t\2\2\2\u020e\u0210"+
		"\7\62\2\2\u020f\u020e\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u0212\3\2\2\2"+
		"\u0211\u020d\3\2\2\2\u0212\u0215\3\2\2\2\u0213\u0211\3\2\2\2\u0213\u0214"+
		"\3\2\2\2\u0214\u0216\3\2\2\2\u0215\u0213\3\2\2\2\u0216\u0217\5D#\2\u0217"+
		"C\3\2\2\2\u0218\u022c\5F$\2\u0219\u021b\7\62\2\2\u021a\u0219\3\2\2\2\u021a"+
		"\u021b\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021d\7\b\2\2\u021d\u021e\5\64"+
		"\33\2\u021e\u021f\7\t\2\2\u021f\u022d\3\2\2\2\u0220\u0222\7\62\2\2\u0221"+
		"\u0220\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0225\7\b"+
		"\2\2\u0224\u0226\5\64\33\2\u0225\u0224\3\2\2\2\u0225\u0226\3\2\2\2\u0226"+
		"\u0227\3\2\2\2\u0227\u0229\7\r\2\2\u0228\u022a\5\64\33\2\u0229\u0228\3"+
		"\2\2\2\u0229\u022a\3\2\2\2\u022a\u022b\3\2\2\2\u022b\u022d\7\t\2\2\u022c"+
		"\u021a\3\2\2\2\u022c\u0221\3\2\2\2\u022dE\3\2\2\2\u022e\u0238\5H%\2\u022f"+
		"\u0231\7\62\2\2\u0230\u022f\3\2\2\2\u0230\u0231\3\2\2\2\u0231\u0234\3"+
		"\2\2\2\u0232\u0235\5\\/\2\u0233\u0235\5*\26\2\u0234\u0232\3\2\2\2\u0234"+
		"\u0233\3\2\2\2\u0235\u0237\3\2\2\2\u0236\u0230\3\2\2\2\u0237\u023a\3\2"+
		"\2\2\u0238\u0236\3\2\2\2\u0238\u0239\3\2\2\2\u0239G\3\2\2\2\u023a\u0238"+
		"\3\2\2\2\u023b\u024e\5J&\2\u023c\u024e\5X-\2\u023d\u023f\7%\2\2\u023e"+
		"\u0240\7\62\2\2\u023f\u023e\3\2\2\2\u023f\u0240\3\2\2\2\u0240\u0241\3"+
		"\2\2\2\u0241\u0243\7\6\2\2\u0242\u0244\7\62\2\2\u0243\u0242\3\2\2\2\u0243"+
		"\u0244\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0247\7\f\2\2\u0246\u0248\7\62"+
		"\2\2\u0247\u0246\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u0249\3\2\2\2\u0249"+
		"\u024e\7\7\2\2\u024a\u024e\5Z.\2\u024b\u024e\5V,\2\u024c\u024e\5`\61\2"+
		"\u024d\u023b\3\2\2\2\u024d\u023c\3\2\2\2\u024d\u023d\3\2\2\2\u024d\u024a"+
		"\3\2\2\2\u024d\u024b\3\2\2\2\u024d\u024c\3\2\2\2\u024eI\3\2\2\2\u024f"+
		"\u0256\5L\'\2\u0250\u0256\7\35\2\2\u0251\u0256\5N(\2\u0252\u0256\7&\2"+
		"\2\u0253\u0256\5R*\2\u0254\u0256\5P)\2\u0255\u024f\3\2\2\2\u0255\u0250"+
		"\3\2\2\2\u0255\u0251\3\2\2\2\u0255\u0252\3\2\2\2\u0255\u0253\3\2\2\2\u0255"+
		"\u0254\3\2\2\2\u0256K\3\2\2\2\u0257\u025a\5d\63\2\u0258\u025a\5b\62\2"+
		"\u0259\u0257\3\2\2\2\u0259\u0258\3\2\2\2\u025aM\3\2\2\2\u025b\u025c\t"+
		"\3\2\2\u025cO\3\2\2\2\u025d\u025f\7\b\2\2\u025e\u0260\7\62\2\2\u025f\u025e"+
		"\3\2\2\2\u025f\u0260\3\2\2\2\u0260\u0272\3\2\2\2\u0261\u0263\5\64\33\2"+
		"\u0262\u0264\7\62\2\2\u0263\u0262\3\2\2\2\u0263\u0264\3\2\2\2\u0264\u026f"+
		"\3\2\2\2\u0265\u0267\7\4\2\2\u0266\u0268\7\62\2\2\u0267\u0266\3\2\2\2"+
		"\u0267\u0268\3\2\2\2\u0268\u0269\3\2\2\2\u0269\u026b\5\64\33\2\u026a\u026c"+
		"\7\62\2\2\u026b\u026a\3\2\2\2\u026b\u026c\3\2\2\2\u026c\u026e\3\2\2\2"+
		"\u026d\u0265\3\2\2\2\u026e\u0271\3\2\2\2\u026f\u026d\3\2\2\2\u026f\u0270"+
		"\3\2\2\2\u0270\u0273\3\2\2\2\u0271\u026f\3\2\2\2\u0272\u0261\3\2\2\2\u0272"+
		"\u0273\3\2\2\2\u0273\u0274\3\2\2\2\u0274\u0275\7\t\2\2\u0275Q\3\2\2\2"+
		"\u0276\u0278\7\23\2\2\u0277\u0279\7\62\2\2\u0278\u0277\3\2\2\2\u0278\u0279"+
		"\3\2\2\2\u0279\u029b\3\2\2\2\u027a\u027c\5^\60\2\u027b\u027d\7\62\2\2"+
		"\u027c\u027b\3\2\2\2\u027c\u027d\3\2\2\2\u027d\u027e\3\2\2\2\u027e\u0280"+
		"\7\n\2\2\u027f\u0281\7\62\2\2\u0280\u027f\3\2\2\2\u0280\u0281\3\2\2\2"+
		"\u0281\u0282\3\2\2\2\u0282\u0284\5\64\33\2\u0283\u0285\7\62\2\2\u0284"+
		"\u0283\3\2\2\2\u0284\u0285\3\2\2\2\u0285\u0298\3\2\2\2\u0286\u0288\7\4"+
		"\2\2\u0287\u0289\7\62\2\2\u0288\u0287\3\2\2\2\u0288\u0289\3\2\2\2\u0289"+
		"\u028a\3\2\2\2\u028a\u028c\5^\60\2\u028b\u028d\7\62\2\2\u028c\u028b\3"+
		"\2\2\2\u028c\u028d\3\2\2\2\u028d\u028e\3\2\2\2\u028e\u0290\7\n\2\2\u028f"+
		"\u0291\7\62\2\2\u0290\u028f\3\2\2\2\u0290\u0291\3\2\2\2\u0291\u0292\3"+
		"\2\2\2\u0292\u0294\5\64\33\2\u0293\u0295\7\62\2\2\u0294\u0293\3\2\2\2"+
		"\u0294\u0295\3\2\2\2\u0295\u0297\3\2\2\2\u0296\u0286\3\2\2\2\u0297\u029a"+
		"\3\2\2\2\u0298\u0296\3\2\2\2\u0298\u0299\3\2\2\2\u0299\u029c\3\2\2\2\u029a"+
		"\u0298\3\2\2\2\u029b\u027a\3\2\2\2\u029b\u029c\3\2\2\2\u029c\u029d\3\2"+
		"\2\2\u029d\u029e\7\24\2\2\u029eS\3\2\2\2\u029f\u02a1\7\5\2\2\u02a0\u02a2"+
		"\7\62\2\2\u02a1\u02a0\3\2\2\2\u02a1\u02a2\3\2\2\2\u02a2\u02a3\3\2\2\2"+
		"\u02a3\u02c3\5<\37\2\u02a4\u02a6\7\25\2\2\u02a5\u02a7\7\62\2\2\u02a6\u02a5"+
		"\3\2\2\2\u02a6\u02a7\3\2\2\2\u02a7\u02a8\3\2\2\2\u02a8\u02c3\5<\37\2\u02a9"+
		"\u02ab\7\26\2\2\u02aa\u02ac\7\62\2\2\u02ab\u02aa\3\2\2\2\u02ab\u02ac\3"+
		"\2\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02c3\5<\37\2\u02ae\u02b0\7\27\2\2\u02af"+
		"\u02b1\7\62\2\2\u02b0\u02af\3\2\2\2\u02b0\u02b1\3\2\2\2\u02b1\u02b2\3"+
		"\2\2\2\u02b2\u02c3\5<\37\2\u02b3\u02b5\7\30\2\2\u02b4\u02b6\7\62\2\2\u02b5"+
		"\u02b4\3\2\2\2\u02b5\u02b6\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7\u02c3\5<"+
		"\37\2\u02b8\u02ba\7\31\2\2\u02b9\u02bb\7\62\2\2\u02ba\u02b9\3\2\2\2\u02ba"+
		"\u02bb\3\2\2\2\u02bb\u02bc\3\2\2\2\u02bc\u02c3\5<\37\2\u02bd\u02bf\7\32"+
		"\2\2\u02be\u02c0\7\62\2\2\u02bf\u02be\3\2\2\2\u02bf\u02c0\3\2\2\2\u02c0"+
		"\u02c1\3\2\2\2\u02c1\u02c3\5<\37\2\u02c2\u029f\3\2\2\2\u02c2\u02a4\3\2"+
		"\2\2\u02c2\u02a9\3\2\2\2\u02c2\u02ae\3\2\2\2\u02c2\u02b3\3\2\2\2\u02c2"+
		"\u02b8\3\2\2\2\u02c2\u02bd\3\2\2\2\u02c3U\3\2\2\2\u02c4\u02c6\7\6\2\2"+
		"\u02c5\u02c7\7\62\2\2\u02c6\u02c5\3\2\2\2\u02c6\u02c7\3\2\2\2\u02c7\u02c8"+
		"\3\2\2\2\u02c8\u02ca\5\64\33\2\u02c9\u02cb\7\62\2\2\u02ca\u02c9\3\2\2"+
		"\2\u02ca\u02cb\3\2\2\2\u02cb\u02cc\3\2\2\2\u02cc\u02cd\7\7\2\2\u02cdW"+
		"\3\2\2\2\u02ce\u02d1\7\33\2\2\u02cf\u02d2\5f\64\2\u02d0\u02d2\7\36\2\2"+
		"\u02d1\u02cf\3\2\2\2\u02d1\u02d0\3\2\2\2\u02d2Y\3\2\2\2\u02d3\u02d8\5"+
		"\36\20\2\u02d4\u02d6\7\62\2\2\u02d5\u02d4\3\2\2\2\u02d5\u02d6\3\2\2\2"+
		"\u02d6\u02d7\3\2\2\2\u02d7\u02d9\5 \21\2\u02d8\u02d5\3\2\2\2\u02d9\u02da"+
		"\3\2\2\2\u02da\u02d8\3\2\2\2\u02da\u02db\3\2\2\2\u02db[\3\2\2\2\u02dc"+
		"\u02de\7\34\2\2\u02dd\u02df\7\62\2\2\u02de\u02dd\3\2\2\2\u02de\u02df\3"+
		"\2\2\2\u02df\u02e0\3\2\2\2\u02e0\u02e1\5^\60\2\u02e1]\3\2\2\2\u02e2\u02e3"+
		"\5f\64\2\u02e3_\3\2\2\2\u02e4\u02e5\5f\64\2\u02e5a\3\2\2\2\u02e6\u02e7"+
		"\7\36\2\2\u02e7c\3\2\2\2\u02e8\u02e9\7$\2\2\u02e9e\3\2\2\2\u02ea\u02eb"+
		"\t\4\2\2\u02ebg\3\2\2\2\u02ec\u02ed\7\27\2\2\u02edi\3\2\2\2\u02ee\u02ef"+
		"\7\30\2\2\u02efk\3\2\2\2\u02f0\u02f1\7\17\2\2\u02f1m\3\2\2\2\u0091osv"+
		"y\177\u0084\u0089\u008d\u0091\u0094\u009d\u00a1\u00a6\u00ab\u00af\u00b4"+
		"\u00b7\u00c1\u00c5\u00ca\u00cf\u00d3\u00d8\u00de\u00e3\u00ea\u00ee\u00f2"+
		"\u00f4\u00f8\u00fa\u00fe\u0100\u0106\u010c\u0110\u0113\u0116\u011a\u0120"+
		"\u0124\u0127\u012a\u0130\u0133\u0136\u013a\u0140\u0143\u0146\u014a\u014e"+
		"\u0152\u0154\u0158\u015a\u015d\u0161\u0163\u0169\u016d\u0171\u0175\u0178"+
		"\u017d\u0182\u0187\u018c\u0192\u0196\u0198\u019c\u01a0\u01a2\u01a4\u01b3"+
		"\u01b8\u01bc\u01c3\u01c8\u01cd\u01d1\u01d5\u01d9\u01dc\u01de\u01e3\u01e7"+
		"\u01eb\u01ef\u01f3\u01f7\u01fa\u01fc\u0201\u0205\u020a\u020f\u0213\u021a"+
		"\u0221\u0225\u0229\u022c\u0230\u0234\u0238\u023f\u0243\u0247\u024d\u0255"+
		"\u0259\u025f\u0263\u0267\u026b\u026f\u0272\u0278\u027c\u0280\u0284\u0288"+
		"\u028c\u0290\u0294\u0298\u029b\u02a1\u02a6\u02ab\u02b0\u02b5\u02ba\u02bf"+
		"\u02c2\u02c6\u02ca\u02d1\u02d5\u02da\u02de";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}