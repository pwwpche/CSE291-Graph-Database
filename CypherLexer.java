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
		T__24=25, T__25=26, StringLiteral=27, DecimalInteger=28, Digit=29, NonZeroDigit=30, 
		NonZeroOctDigit=31, OctDigit=32, ZeroDigit=33, RegularDecimalReal=34, 
		COUNT=35, NULL=36, OR=37, MATCH=38, RETURN=39, WHERE=40, AND=41, NOT=42, 
		TRUE=43, FALSE=44, UnescapedSymbolicName=45, IdentifierStart=46, IdentifierPart=47, 
		SP=48, WHITESPACE=49, Comment=50;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "StringLiteral", "DecimalInteger", "Digit", "NonZeroDigit", "NonZeroOctDigit", 
		"OctDigit", "ZeroDigit", "RegularDecimalReal", "COUNT", "NULL", "OR", 
		"MATCH", "RETURN", "WHERE", "AND", "NOT", "TRUE", "FALSE", "UnescapedSymbolicName", 
		"IdentifierStart", "IdentifierPart", "SP", "WHITESPACE", "Comment", "FF", 
		"RS", "ID_Continue", "Comment_1", "Comment_3", "Comment_2", "GS", "FS", 
		"CR", "SPACE", "TAB", "StringLiteral_0", "LF", "VT", "US", "ID_Start"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\64\u0182\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\7\34\u00c3\n\34\f\34\16\34\u00c6\13\34"+
		"\3\34\3\34\3\35\3\35\3\35\7\35\u00cd\n\35\f\35\16\35\u00d0\13\35\5\35"+
		"\u00d2\n\35\3\36\3\36\5\36\u00d6\n\36\3\37\3\37\5\37\u00da\n\37\3 \3 "+
		"\3!\3!\5!\u00e0\n!\3\"\3\"\3#\7#\u00e5\n#\f#\16#\u00e8\13#\3#\3#\6#\u00ec"+
		"\n#\r#\16#\u00ed\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'"+
		"\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3+\3+"+
		"\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3.\7.\u0126\n.\f.\16.\u0129"+
		"\13.\3/\3/\5/\u012d\n/\3\60\3\60\3\61\6\61\u0132\n\61\r\61\16\61\u0133"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\5\62\u0142"+
		"\n\62\3\63\3\63\3\63\3\63\3\63\3\63\7\63\u014a\n\63\f\63\16\63\u014d\13"+
		"\63\3\63\3\63\3\63\3\63\3\63\3\63\7\63\u0155\n\63\f\63\16\63\u0158\13"+
		"\63\3\63\5\63\u015b\n\63\3\63\3\63\5\63\u015f\n\63\5\63\u0161\n\63\3\64"+
		"\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3"+
		"=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\2\2D\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"+
		"U,W-Y.[/]\60_\61a\62c\63e\64g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2"+
		"\u0081\2\u0083\2\u0085\2\3\2#\4\2EEee\4\2QQqq\4\2WWww\4\2PPpp\4\2VVvv"+
		"\4\2NNnn\4\2TTtt\4\2OOoo\4\2CCcc\4\2JJjj\4\2GGgg\4\2YYyy\4\2FFff\4\2H"+
		"Hhh\4\2UUuu\b\2aa\u2041\u2042\u2056\u2056\ufe35\ufe36\ufe4f\ufe51\uff41"+
		"\uff41\n\2\u00a2\u00a2\u1682\u1682\u1810\u1810\u2002\u200c\u202a\u202b"+
		"\u2031\u2031\u2061\u2061\u3002\u3002\3\2\16\16\3\2  \6\2\62;C\\aac|\4"+
		"\2\2+-\1\5\2\2\13\r\16\20\1\4\2\2\60\62\1\3\2\37\37\3\2\36\36\3\2\17\17"+
		"\3\2\"\"\3\2\13\13\5\2\2#%]_\1\3\2\f\f\3\2\r\r\3\2!!\4\2C\\c|\u018d\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\2e\3\2\2\2\3\u0087\3\2\2\2\5\u0089\3\2\2\2\7\u008b\3\2"+
		"\2\2\t\u008d\3\2\2\2\13\u008f\3\2\2\2\r\u0091\3\2\2\2\17\u0093\3\2\2\2"+
		"\21\u0095\3\2\2\2\23\u0097\3\2\2\2\25\u0099\3\2\2\2\27\u009b\3\2\2\2\31"+
		"\u009e\3\2\2\2\33\u00a0\3\2\2\2\35\u00a2\3\2\2\2\37\u00a4\3\2\2\2!\u00a6"+
		"\3\2\2\2#\u00a8\3\2\2\2%\u00aa\3\2\2\2\'\u00ac\3\2\2\2)\u00af\3\2\2\2"+
		"+\u00b2\3\2\2\2-\u00b4\3\2\2\2/\u00b6\3\2\2\2\61\u00b9\3\2\2\2\63\u00bc"+
		"\3\2\2\2\65\u00be\3\2\2\2\67\u00c0\3\2\2\29\u00d1\3\2\2\2;\u00d5\3\2\2"+
		"\2=\u00d9\3\2\2\2?\u00db\3\2\2\2A\u00df\3\2\2\2C\u00e1\3\2\2\2E\u00e6"+
		"\3\2\2\2G\u00ef\3\2\2\2I\u00f5\3\2\2\2K\u00fa\3\2\2\2M\u00fd\3\2\2\2O"+
		"\u0103\3\2\2\2Q\u010a\3\2\2\2S\u0110\3\2\2\2U\u0114\3\2\2\2W\u0118\3\2"+
		"\2\2Y\u011d\3\2\2\2[\u0123\3\2\2\2]\u012c\3\2\2\2_\u012e\3\2\2\2a\u0131"+
		"\3\2\2\2c\u0141\3\2\2\2e\u0160\3\2\2\2g\u0162\3\2\2\2i\u0164\3\2\2\2k"+
		"\u0166\3\2\2\2m\u0168\3\2\2\2o\u016a\3\2\2\2q\u016c\3\2\2\2s\u016e\3\2"+
		"\2\2u\u0170\3\2\2\2w\u0172\3\2\2\2y\u0174\3\2\2\2{\u0176\3\2\2\2}\u0178"+
		"\3\2\2\2\177\u017a\3\2\2\2\u0081\u017c\3\2\2\2\u0083\u017e\3\2\2\2\u0085"+
		"\u0180\3\2\2\2\u0087\u0088\7=\2\2\u0088\4\3\2\2\2\u0089\u008a\7.\2\2\u008a"+
		"\6\3\2\2\2\u008b\u008c\7?\2\2\u008c\b\3\2\2\2\u008d\u008e\7*\2\2\u008e"+
		"\n\3\2\2\2\u008f\u0090\7+\2\2\u0090\f\3\2\2\2\u0091\u0092\7]\2\2\u0092"+
		"\16\3\2\2\2\u0093\u0094\7_\2\2\u0094\20\3\2\2\2\u0095\u0096\7<\2\2\u0096"+
		"\22\3\2\2\2\u0097\u0098\7~\2\2\u0098\24\3\2\2\2\u0099\u009a\7,\2\2\u009a"+
		"\26\3\2\2\2\u009b\u009c\7\60\2\2\u009c\u009d\7\60\2\2\u009d\30\3\2\2\2"+
		"\u009e\u009f\7-\2\2\u009f\32\3\2\2\2\u00a0\u00a1\7/\2\2\u00a1\34\3\2\2"+
		"\2\u00a2\u00a3\7\61\2\2\u00a3\36\3\2\2\2\u00a4\u00a5\7\'\2\2\u00a5 \3"+
		"\2\2\2\u00a6\u00a7\7`\2\2\u00a7\"\3\2\2\2\u00a8\u00a9\7}\2\2\u00a9$\3"+
		"\2\2\2\u00aa\u00ab\7\177\2\2\u00ab&\3\2\2\2\u00ac\u00ad\7>\2\2\u00ad\u00ae"+
		"\7@\2\2\u00ae(\3\2\2\2\u00af\u00b0\7#\2\2\u00b0\u00b1\7?\2\2\u00b1*\3"+
		"\2\2\2\u00b2\u00b3\7>\2\2\u00b3,\3\2\2\2\u00b4\u00b5\7@\2\2\u00b5.\3\2"+
		"\2\2\u00b6\u00b7\7>\2\2\u00b7\u00b8\7?\2\2\u00b8\60\3\2\2\2\u00b9\u00ba"+
		"\7@\2\2\u00ba\u00bb\7?\2\2\u00bb\62\3\2\2\2\u00bc\u00bd\7&\2\2\u00bd\64"+
		"\3\2\2\2\u00be\u00bf\7\60\2\2\u00bf\66\3\2\2\2\u00c0\u00c4\7$\2\2\u00c1"+
		"\u00c3\5}?\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2"+
		"\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8"+
		"\7$\2\2\u00c88\3\2\2\2\u00c9\u00d2\5C\"\2\u00ca\u00ce\5=\37\2\u00cb\u00cd"+
		"\5;\36\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00c9\3\2"+
		"\2\2\u00d1\u00ca\3\2\2\2\u00d2:\3\2\2\2\u00d3\u00d6\5C\"\2\u00d4\u00d6"+
		"\5=\37\2\u00d5\u00d3\3\2\2\2\u00d5\u00d4\3\2\2\2\u00d6<\3\2\2\2\u00d7"+
		"\u00da\5? \2\u00d8\u00da\4:;\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2"+
		"\u00da>\3\2\2\2\u00db\u00dc\4\639\2\u00dc@\3\2\2\2\u00dd\u00e0\5C\"\2"+
		"\u00de\u00e0\5? \2\u00df\u00dd\3\2\2\2\u00df\u00de\3\2\2\2\u00e0B\3\2"+
		"\2\2\u00e1\u00e2\7\62\2\2\u00e2D\3\2\2\2\u00e3\u00e5\5;\36\2\u00e4\u00e3"+
		"\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00eb\7\60\2\2\u00ea\u00ec\5"+
		";\36\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00eeF\3\2\2\2\u00ef\u00f0\t\2\2\2\u00f0\u00f1\t\3\2\2"+
		"\u00f1\u00f2\t\4\2\2\u00f2\u00f3\t\5\2\2\u00f3\u00f4\t\6\2\2\u00f4H\3"+
		"\2\2\2\u00f5\u00f6\t\5\2\2\u00f6\u00f7\t\4\2\2\u00f7\u00f8\t\7\2\2\u00f8"+
		"\u00f9\t\7\2\2\u00f9J\3\2\2\2\u00fa\u00fb\t\3\2\2\u00fb\u00fc\t\b\2\2"+
		"\u00fcL\3\2\2\2\u00fd\u00fe\t\t\2\2\u00fe\u00ff\t\n\2\2\u00ff\u0100\t"+
		"\6\2\2\u0100\u0101\t\2\2\2\u0101\u0102\t\13\2\2\u0102N\3\2\2\2\u0103\u0104"+
		"\t\b\2\2\u0104\u0105\t\f\2\2\u0105\u0106\t\6\2\2\u0106\u0107\t\4\2\2\u0107"+
		"\u0108\t\b\2\2\u0108\u0109\t\5\2\2\u0109P\3\2\2\2\u010a\u010b\t\r\2\2"+
		"\u010b\u010c\t\13\2\2\u010c\u010d\t\f\2\2\u010d\u010e\t\b\2\2\u010e\u010f"+
		"\t\f\2\2\u010fR\3\2\2\2\u0110\u0111\t\n\2\2\u0111\u0112\t\5\2\2\u0112"+
		"\u0113\t\16\2\2\u0113T\3\2\2\2\u0114\u0115\t\5\2\2\u0115\u0116\t\3\2\2"+
		"\u0116\u0117\t\6\2\2\u0117V\3\2\2\2\u0118\u0119\t\6\2\2\u0119\u011a\t"+
		"\b\2\2\u011a\u011b\t\4\2\2\u011b\u011c\t\f\2\2\u011cX\3\2\2\2\u011d\u011e"+
		"\t\17\2\2\u011e\u011f\t\n\2\2\u011f\u0120\t\7\2\2\u0120\u0121\t\20\2\2"+
		"\u0121\u0122\t\f\2\2\u0122Z\3\2\2\2\u0123\u0127\5]/\2\u0124\u0126\5_\60"+
		"\2\u0125\u0124\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128"+
		"\3\2\2\2\u0128\\\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u012d\5\u0085C\2\u012b"+
		"\u012d\t\21\2\2\u012c\u012a\3\2\2\2\u012c\u012b\3\2\2\2\u012d^\3\2\2\2"+
		"\u012e\u012f\5k\66\2\u012f`\3\2\2\2\u0130\u0132\5c\62\2\u0131\u0130\3"+
		"\2\2\2\u0132\u0133\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
		"b\3\2\2\2\u0135\u0142\5y=\2\u0136\u0142\5{>\2\u0137\u0142\5\177@\2\u0138"+
		"\u0142\5\u0081A\2\u0139\u0142\5g\64\2\u013a\u0142\5w<\2\u013b\u0142\5"+
		"u;\2\u013c\u0142\5s:\2\u013d\u0142\5i\65\2\u013e\u0142\5\u0083B\2\u013f"+
		"\u0142\t\22\2\2\u0140\u0142\5e\63\2\u0141\u0135\3\2\2\2\u0141\u0136\3"+
		"\2\2\2\u0141\u0137\3\2\2\2\u0141\u0138\3\2\2\2\u0141\u0139\3\2\2\2\u0141"+
		"\u013a\3\2\2\2\u0141\u013b\3\2\2\2\u0141\u013c\3\2\2\2\u0141\u013d\3\2"+
		"\2\2\u0141\u013e\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0140\3\2\2\2\u0142"+
		"d\3\2\2\2\u0143\u0144\7\61\2\2\u0144\u0145\7,\2\2\u0145\u014b\3\2\2\2"+
		"\u0146\u014a\5m\67\2\u0147\u0148\7,\2\2\u0148\u014a\5q9\2\u0149\u0146"+
		"\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b"+
		"\u014c\3\2\2\2\u014c\u014e\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u014f\7,"+
		"\2\2\u014f\u0161\7\61\2\2\u0150\u0151\7\61\2\2\u0151\u0152\7\61\2\2\u0152"+
		"\u0156\3\2\2\2\u0153\u0155\5o8\2\u0154\u0153\3\2\2\2\u0155\u0158\3\2\2"+
		"\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156"+
		"\3\2\2\2\u0159\u015b\5w<\2\u015a\u0159\3\2\2\2\u015a\u015b\3\2\2\2\u015b"+
		"\u015e\3\2\2\2\u015c\u015f\5\177@\2\u015d\u015f\7\2\2\3\u015e\u015c\3"+
		"\2\2\2\u015e\u015d\3\2\2\2\u015f\u0161\3\2\2\2\u0160\u0143\3\2\2\2\u0160"+
		"\u0150\3\2\2\2\u0161f\3\2\2\2\u0162\u0163\t\23\2\2\u0163h\3\2\2\2\u0164"+
		"\u0165\t\24\2\2\u0165j\3\2\2\2\u0166\u0167\t\25\2\2\u0167l\3\2\2\2\u0168"+
		"\u0169\t\26\2\2\u0169n\3\2\2\2\u016a\u016b\t\27\2\2\u016bp\3\2\2\2\u016c"+
		"\u016d\t\30\2\2\u016dr\3\2\2\2\u016e\u016f\t\31\2\2\u016ft\3\2\2\2\u0170"+
		"\u0171\t\32\2\2\u0171v\3\2\2\2\u0172\u0173\t\33\2\2\u0173x\3\2\2\2\u0174"+
		"\u0175\t\34\2\2\u0175z\3\2\2\2\u0176\u0177\t\35\2\2\u0177|\3\2\2\2\u0178"+
		"\u0179\t\36\2\2\u0179~\3\2\2\2\u017a\u017b\t\37\2\2\u017b\u0080\3\2\2"+
		"\2\u017c\u017d\t \2\2\u017d\u0082\3\2\2\2\u017e\u017f\t!\2\2\u017f\u0084"+
		"\3\2\2\2\u0180\u0181\t\"\2\2\u0181\u0086\3\2\2\2\25\2\u00c4\u00ce\u00d1"+
		"\u00d5\u00d9\u00df\u00e6\u00ed\u0127\u012c\u0133\u0141\u0149\u014b\u0156"+
		"\u015a\u015e\u0160\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}