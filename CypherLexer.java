// Generated from src/Cypher.g4 by ANTLR 4.6
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CypherLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, StringLiteral=26, DecimalInteger=27, Digit=28, NonZeroDigit=29, 
		NonZeroOctDigit=30, OctDigit=31, ZeroDigit=32, RegularDecimalReal=33, 
		COUNT=34, NULL=35, MATCH=36, RETURN=37, WHERE=38, AND=39, NOT=40, TRUE=41, 
		FALSE=42, UnescapedSymbolicName=43, IdentifierStart=44, IdentifierPart=45, 
		SP=46, WHITESPACE=47, Comment=48;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"StringLiteral", "DecimalInteger", "Digit", "NonZeroDigit", "NonZeroOctDigit", 
		"OctDigit", "ZeroDigit", "RegularDecimalReal", "COUNT", "NULL", "MATCH", 
		"RETURN", "WHERE", "AND", "NOT", "TRUE", "FALSE", "UnescapedSymbolicName", 
		"IdentifierStart", "IdentifierPart", "SP", "WHITESPACE", "Comment", "FF", 
		"RS", "ID_Continue", "Comment_1", "Comment_3", "Comment_2", "GS", "FS", 
		"CR", "SPACE", "TAB", "StringLiteral_0", "LF", "VT", "US", "ID_Start"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'='", "'('", "')'", "'['", "']'", "':'", "'|'", "'*'", 
		"'..'", "'+'", "'-'", "'/'", "'%'", "'^'", "'{'", "'}'", "'<>'", "'!='", 
		"'<'", "'>'", "'<='", "'>='", "'.'", null, null, null, null, null, null, 
		"'0'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "StringLiteral", "DecimalInteger", "Digit", "NonZeroDigit", 
		"NonZeroOctDigit", "OctDigit", "ZeroDigit", "RegularDecimalReal", "COUNT", 
		"NULL", "MATCH", "RETURN", "WHERE", "AND", "NOT", "TRUE", "FALSE", "UnescapedSymbolicName", 
		"IdentifierStart", "IdentifierPart", "SP", "WHITESPACE", "Comment"
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


	public CypherLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Cypher.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\62\u017b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\7\33\u00bd\n\33\f\33\16\33\u00c0\13\33\3\33\3\33\3\34\3\34\3"+
		"\34\7\34\u00c7\n\34\f\34\16\34\u00ca\13\34\5\34\u00cc\n\34\3\35\3\35\5"+
		"\35\u00d0\n\35\3\36\3\36\5\36\u00d4\n\36\3\37\3\37\3 \3 \5 \u00da\n \3"+
		"!\3!\3\"\7\"\u00df\n\"\f\"\16\"\u00e2\13\"\3\"\3\"\6\"\u00e6\n\"\r\"\16"+
		"\"\u00e7\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3"+
		"&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3"+
		"*\3*\3+\3+\3+\3+\3+\3+\3,\3,\7,\u011d\n,\f,\16,\u0120\13,\3-\3-\5-\u0124"+
		"\n-\3.\3.\3/\6/\u0129\n/\r/\16/\u012a\3/\3/\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\3\60\5\60\u013b\n\60\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\7\61\u0143\n\61\f\61\16\61\u0146\13\61\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\7\61\u014e\n\61\f\61\16\61\u0151\13\61\3\61\5\61\u0154\n\61"+
		"\3\61\3\61\5\61\u0158\n\61\5\61\u015a\n\61\3\62\3\62\3\63\3\63\3\64\3"+
		"\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3="+
		"\3>\3>\3?\3?\3@\3@\3A\3A\2\2B\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61"+
		"a\62c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2\u0081\2\3\2#\4\2"+
		"EEee\4\2QQqq\4\2WWww\4\2PPpp\4\2VVvv\4\2NNnn\4\2OOoo\4\2CCcc\4\2JJjj\4"+
		"\2TTtt\4\2GGgg\4\2YYyy\4\2FFff\4\2HHhh\4\2UUuu\b\2aa\u2041\u2042\u2056"+
		"\u2056\ufe35\ufe36\ufe4f\ufe51\uff41\uff41\n\2\u00a2\u00a2\u1682\u1682"+
		"\u1810\u1810\u2002\u200c\u202a\u202b\u2031\u2031\u2061\u2061\u3002\u3002"+
		"\3\2\16\16\3\2  \6\2\62;C\\aac|\4\2\2+-\1\5\2\2\13\r\16\20\1\4\2\2\60"+
		"\62\1\3\2\37\37\3\2\36\36\3\2\17\17\3\2\"\"\3\2\13\13\5\2\2#%]_\1\3\2"+
		"\f\f\3\2\r\r\3\2!!\4\2C\\c|\u0186\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2"+
		"O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3"+
		"\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\3\u0083\3\2\2\2\5\u0085\3\2\2"+
		"\2\7\u0087\3\2\2\2\t\u0089\3\2\2\2\13\u008b\3\2\2\2\r\u008d\3\2\2\2\17"+
		"\u008f\3\2\2\2\21\u0091\3\2\2\2\23\u0093\3\2\2\2\25\u0095\3\2\2\2\27\u0097"+
		"\3\2\2\2\31\u009a\3\2\2\2\33\u009c\3\2\2\2\35\u009e\3\2\2\2\37\u00a0\3"+
		"\2\2\2!\u00a2\3\2\2\2#\u00a4\3\2\2\2%\u00a6\3\2\2\2\'\u00a8\3\2\2\2)\u00ab"+
		"\3\2\2\2+\u00ae\3\2\2\2-\u00b0\3\2\2\2/\u00b2\3\2\2\2\61\u00b5\3\2\2\2"+
		"\63\u00b8\3\2\2\2\65\u00ba\3\2\2\2\67\u00cb\3\2\2\29\u00cf\3\2\2\2;\u00d3"+
		"\3\2\2\2=\u00d5\3\2\2\2?\u00d9\3\2\2\2A\u00db\3\2\2\2C\u00e0\3\2\2\2E"+
		"\u00e9\3\2\2\2G\u00ef\3\2\2\2I\u00f4\3\2\2\2K\u00fa\3\2\2\2M\u0101\3\2"+
		"\2\2O\u0107\3\2\2\2Q\u010b\3\2\2\2S\u010f\3\2\2\2U\u0114\3\2\2\2W\u011a"+
		"\3\2\2\2Y\u0123\3\2\2\2[\u0125\3\2\2\2]\u0128\3\2\2\2_\u013a\3\2\2\2a"+
		"\u0159\3\2\2\2c\u015b\3\2\2\2e\u015d\3\2\2\2g\u015f\3\2\2\2i\u0161\3\2"+
		"\2\2k\u0163\3\2\2\2m\u0165\3\2\2\2o\u0167\3\2\2\2q\u0169\3\2\2\2s\u016b"+
		"\3\2\2\2u\u016d\3\2\2\2w\u016f\3\2\2\2y\u0171\3\2\2\2{\u0173\3\2\2\2}"+
		"\u0175\3\2\2\2\177\u0177\3\2\2\2\u0081\u0179\3\2\2\2\u0083\u0084\7=\2"+
		"\2\u0084\4\3\2\2\2\u0085\u0086\7.\2\2\u0086\6\3\2\2\2\u0087\u0088\7?\2"+
		"\2\u0088\b\3\2\2\2\u0089\u008a\7*\2\2\u008a\n\3\2\2\2\u008b\u008c\7+\2"+
		"\2\u008c\f\3\2\2\2\u008d\u008e\7]\2\2\u008e\16\3\2\2\2\u008f\u0090\7_"+
		"\2\2\u0090\20\3\2\2\2\u0091\u0092\7<\2\2\u0092\22\3\2\2\2\u0093\u0094"+
		"\7~\2\2\u0094\24\3\2\2\2\u0095\u0096\7,\2\2\u0096\26\3\2\2\2\u0097\u0098"+
		"\7\60\2\2\u0098\u0099\7\60\2\2\u0099\30\3\2\2\2\u009a\u009b\7-\2\2\u009b"+
		"\32\3\2\2\2\u009c\u009d\7/\2\2\u009d\34\3\2\2\2\u009e\u009f\7\61\2\2\u009f"+
		"\36\3\2\2\2\u00a0\u00a1\7\'\2\2\u00a1 \3\2\2\2\u00a2\u00a3\7`\2\2\u00a3"+
		"\"\3\2\2\2\u00a4\u00a5\7}\2\2\u00a5$\3\2\2\2\u00a6\u00a7\7\177\2\2\u00a7"+
		"&\3\2\2\2\u00a8\u00a9\7>\2\2\u00a9\u00aa\7@\2\2\u00aa(\3\2\2\2\u00ab\u00ac"+
		"\7#\2\2\u00ac\u00ad\7?\2\2\u00ad*\3\2\2\2\u00ae\u00af\7>\2\2\u00af,\3"+
		"\2\2\2\u00b0\u00b1\7@\2\2\u00b1.\3\2\2\2\u00b2\u00b3\7>\2\2\u00b3\u00b4"+
		"\7?\2\2\u00b4\60\3\2\2\2\u00b5\u00b6\7@\2\2\u00b6\u00b7\7?\2\2\u00b7\62"+
		"\3\2\2\2\u00b8\u00b9\7\60\2\2\u00b9\64\3\2\2\2\u00ba\u00be\7$\2\2\u00bb"+
		"\u00bd\5y=\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2"+
		"\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2"+
		"\7$\2\2\u00c2\66\3\2\2\2\u00c3\u00cc\5A!\2\u00c4\u00c8\5;\36\2\u00c5\u00c7"+
		"\59\35\2\u00c6\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00c3\3\2"+
		"\2\2\u00cb\u00c4\3\2\2\2\u00cc8\3\2\2\2\u00cd\u00d0\5A!\2\u00ce\u00d0"+
		"\5;\36\2\u00cf\u00cd\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0:\3\2\2\2\u00d1"+
		"\u00d4\5=\37\2\u00d2\u00d4\4:;\2\u00d3\u00d1\3\2\2\2\u00d3\u00d2\3\2\2"+
		"\2\u00d4<\3\2\2\2\u00d5\u00d6\4\639\2\u00d6>\3\2\2\2\u00d7\u00da\5A!\2"+
		"\u00d8\u00da\5=\37\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da@\3"+
		"\2\2\2\u00db\u00dc\7\62\2\2\u00dcB\3\2\2\2\u00dd\u00df\59\35\2\u00de\u00dd"+
		"\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e5\7\60\2\2\u00e4\u00e6\5"+
		"9\35\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8D\3\2\2\2\u00e9\u00ea\t\2\2\2\u00ea\u00eb\t\3\2\2"+
		"\u00eb\u00ec\t\4\2\2\u00ec\u00ed\t\5\2\2\u00ed\u00ee\t\6\2\2\u00eeF\3"+
		"\2\2\2\u00ef\u00f0\t\5\2\2\u00f0\u00f1\t\4\2\2\u00f1\u00f2\t\7\2\2\u00f2"+
		"\u00f3\t\7\2\2\u00f3H\3\2\2\2\u00f4\u00f5\t\b\2\2\u00f5\u00f6\t\t\2\2"+
		"\u00f6\u00f7\t\6\2\2\u00f7\u00f8\t\2\2\2\u00f8\u00f9\t\n\2\2\u00f9J\3"+
		"\2\2\2\u00fa\u00fb\t\13\2\2\u00fb\u00fc\t\f\2\2\u00fc\u00fd\t\6\2\2\u00fd"+
		"\u00fe\t\4\2\2\u00fe\u00ff\t\13\2\2\u00ff\u0100\t\5\2\2\u0100L\3\2\2\2"+
		"\u0101\u0102\t\r\2\2\u0102\u0103\t\n\2\2\u0103\u0104\t\f\2\2\u0104\u0105"+
		"\t\13\2\2\u0105\u0106\t\f\2\2\u0106N\3\2\2\2\u0107\u0108\t\t\2\2\u0108"+
		"\u0109\t\5\2\2\u0109\u010a\t\16\2\2\u010aP\3\2\2\2\u010b\u010c\t\5\2\2"+
		"\u010c\u010d\t\3\2\2\u010d\u010e\t\6\2\2\u010eR\3\2\2\2\u010f\u0110\t"+
		"\6\2\2\u0110\u0111\t\13\2\2\u0111\u0112\t\4\2\2\u0112\u0113\t\f\2\2\u0113"+
		"T\3\2\2\2\u0114\u0115\t\17\2\2\u0115\u0116\t\t\2\2\u0116\u0117\t\7\2\2"+
		"\u0117\u0118\t\20\2\2\u0118\u0119\t\f\2\2\u0119V\3\2\2\2\u011a\u011e\5"+
		"Y-\2\u011b\u011d\5[.\2\u011c\u011b\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c"+
		"\3\2\2\2\u011e\u011f\3\2\2\2\u011fX\3\2\2\2\u0120\u011e\3\2\2\2\u0121"+
		"\u0124\5\u0081A\2\u0122\u0124\t\21\2\2\u0123\u0121\3\2\2\2\u0123\u0122"+
		"\3\2\2\2\u0124Z\3\2\2\2\u0125\u0126\5g\64\2\u0126\\\3\2\2\2\u0127\u0129"+
		"\5_\60\2\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\b/\2\2\u012d^\3\2\2\2\u012e"+
		"\u013b\5u;\2\u012f\u013b\5w<\2\u0130\u013b\5{>\2\u0131\u013b\5}?\2\u0132"+
		"\u013b\5c\62\2\u0133\u013b\5s:\2\u0134\u013b\5q9\2\u0135\u013b\5o8\2\u0136"+
		"\u013b\5e\63\2\u0137\u013b\5\177@\2\u0138\u013b\t\22\2\2\u0139\u013b\5"+
		"a\61\2\u013a\u012e\3\2\2\2\u013a\u012f\3\2\2\2\u013a\u0130\3\2\2\2\u013a"+
		"\u0131\3\2\2\2\u013a\u0132\3\2\2\2\u013a\u0133\3\2\2\2\u013a\u0134\3\2"+
		"\2\2\u013a\u0135\3\2\2\2\u013a\u0136\3\2\2\2\u013a\u0137\3\2\2\2\u013a"+
		"\u0138\3\2\2\2\u013a\u0139\3\2\2\2\u013b`\3\2\2\2\u013c\u013d\7\61\2\2"+
		"\u013d\u013e\7,\2\2\u013e\u0144\3\2\2\2\u013f\u0143\5i\65\2\u0140\u0141"+
		"\7,\2\2\u0141\u0143\5m\67\2\u0142\u013f\3\2\2\2\u0142\u0140\3\2\2\2\u0143"+
		"\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0147\3\2"+
		"\2\2\u0146\u0144\3\2\2\2\u0147\u0148\7,\2\2\u0148\u015a\7\61\2\2\u0149"+
		"\u014a\7\61\2\2\u014a\u014b\7\61\2\2\u014b\u014f\3\2\2\2\u014c\u014e\5"+
		"k\66\2\u014d\u014c\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0152\u0154\5s"+
		":\2\u0153\u0152\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0157\3\2\2\2\u0155"+
		"\u0158\5{>\2\u0156\u0158\7\2\2\3\u0157\u0155\3\2\2\2\u0157\u0156\3\2\2"+
		"\2\u0158\u015a\3\2\2\2\u0159\u013c\3\2\2\2\u0159\u0149\3\2\2\2\u015ab"+
		"\3\2\2\2\u015b\u015c\t\23\2\2\u015cd\3\2\2\2\u015d\u015e\t\24\2\2\u015e"+
		"f\3\2\2\2\u015f\u0160\t\25\2\2\u0160h\3\2\2\2\u0161\u0162\t\26\2\2\u0162"+
		"j\3\2\2\2\u0163\u0164\t\27\2\2\u0164l\3\2\2\2\u0165\u0166\t\30\2\2\u0166"+
		"n\3\2\2\2\u0167\u0168\t\31\2\2\u0168p\3\2\2\2\u0169\u016a\t\32\2\2\u016a"+
		"r\3\2\2\2\u016b\u016c\t\33\2\2\u016ct\3\2\2\2\u016d\u016e\t\34\2\2\u016e"+
		"v\3\2\2\2\u016f\u0170\t\35\2\2\u0170x\3\2\2\2\u0171\u0172\t\36\2\2\u0172"+
		"z\3\2\2\2\u0173\u0174\t\37\2\2\u0174|\3\2\2\2\u0175\u0176\t \2\2\u0176"+
		"~\3\2\2\2\u0177\u0178\t!\2\2\u0178\u0080\3\2\2\2\u0179\u017a\t\"\2\2\u017a"+
		"\u0082\3\2\2\2\25\2\u00be\u00c8\u00cb\u00cf\u00d3\u00d9\u00e0\u00e7\u011e"+
		"\u0123\u012a\u013a\u0142\u0144\u014f\u0153\u0157\u0159\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}