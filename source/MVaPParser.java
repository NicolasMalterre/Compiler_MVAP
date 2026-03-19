// Generated from MVaP.g4 by ANTLR 4.13.1

    import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MVaPParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, ENTIER=2, FLOAT=3, ADD=4, SUB=5, MUL=6, DIV=7, MOD=8, INF=9, INFEQ=10, 
		SUP=11, SUPEQ=12, EQUAL=13, NEQ=14, FADD=15, FSUB=16, FMUL=17, FDIV=18, 
		FINF=19, FINFEQ=20, FSUP=21, FSUPEQ=22, FEQUAL=23, FNEQ=24, ITOF=25, FTOI=26, 
		RETURN=27, POP=28, DUP=29, READ=30, READF=31, WRITE=32, WRITEF=33, PUSHSP=34, 
		PUSHFP=35, PUSHI=36, PUSHF=37, PUSHG=38, STOREG=39, PUSHL=40, STOREL=41, 
		PUSHR=42, STORER=43, FREE=44, ALLOC=45, CALL=46, JUMP=47, JUMPF=48, JUMPR=49, 
		HALT=50, LABEL=51, IDENTIFIANT=52, NEWLINE=53;
	public static final int
		RULE_program = 0, RULE_instr = 1, RULE_commande1 = 2, RULE_instr1 = 3, 
		RULE_commande2 = 4, RULE_instr2 = 5, RULE_instr2f = 6, RULE_commandeSaut = 7, 
		RULE_saut = 8, RULE_label = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "instr", "commande1", "instr1", "commande2", "instr2", "instr2f", 
			"commandeSaut", "saut", "label"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'ADD'", "'SUB'", "'MUL'", "'DIV'", "'MOD'", 
			"'INF'", "'INFEQ'", "'SUP'", "'SUPEQ'", "'EQUAL'", "'NEQ'", "'FADD'", 
			"'FSUB'", "'FMUL'", "'FDIV'", "'FINF'", "'FINFEQ'", "'FSUP'", "'FSUPEQ'", 
			"'FEQUAL'", "'FNEQ'", "'ITOF'", "'FTOI'", "'RETURN'", "'POP'", "'DUP'", 
			"'READ'", "'READF'", "'WRITE'", "'WRITEF'", "'PUSHSP'", "'PUSHFP'", "'PUSHI'", 
			"'PUSHF'", "'PUSHG'", "'STOREG'", "'PUSHL'", "'STOREL'", "'PUSHR'", "'STORER'", 
			"'FREE'", "'ALLOC'", "'CALL'", "'JUMP '", "'JUMPF'", "'JUMPR'", "'HALT'", 
			"'LABEL'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "ENTIER", "FLOAT", "ADD", "SUB", "MUL", "DIV", "MOD", "INF", 
			"INFEQ", "SUP", "SUPEQ", "EQUAL", "NEQ", "FADD", "FSUB", "FMUL", "FDIV", 
			"FINF", "FINFEQ", "FSUP", "FSUPEQ", "FEQUAL", "FNEQ", "ITOF", "FTOI", 
			"RETURN", "POP", "DUP", "READ", "READF", "WRITE", "WRITEF", "PUSHSP", 
			"PUSHFP", "PUSHI", "PUSHF", "PUSHG", "STOREG", "PUSHL", "STOREL", "PUSHR", 
			"STORER", "FREE", "ALLOC", "CALL", "JUMP", "JUMPF", "JUMPR", "HALT", 
			"LABEL", "IDENTIFIANT", "NEWLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "MVaP.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    /** La map pour mémoriser les addresses des étiquettes */
	    private HashMap<String, Integer> labels = new HashMap<String, Integer>();
	    /** adresse instruction */
	    private int instrAddress = 0;
	    /** Récupère le dictionnaire des étiquettes */
	    public HashMap<String, Integer> getLabels() { return labels; }
	    public int getProgramSize() { return instrAddress; }

	public MVaPParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<InstrContext> instr() {
			return getRuleContexts(InstrContext.class);
		}
		public InstrContext instr(int i) {
			return getRuleContext(InstrContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				instr();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 13510798882111472L) != 0) );
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

	@SuppressWarnings("CheckReturnValue")
	public static class InstrContext extends ParserRuleContext {
		public Instr1Context instr1() {
			return getRuleContext(Instr1Context.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(MVaPParser.NEWLINE, 0); }
		public Instr2Context instr2() {
			return getRuleContext(Instr2Context.class,0);
		}
		public Instr2fContext instr2f() {
			return getRuleContext(Instr2fContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public SautContext saut() {
			return getRuleContext(SautContext.class,0);
		}
		public InstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitInstr(this);
		}
	}

	public final InstrContext instr() throws RecognitionException {
		InstrContext _localctx = new InstrContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instr);
		try {
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case SUB:
			case MUL:
			case DIV:
			case MOD:
			case INF:
			case INFEQ:
			case SUP:
			case SUPEQ:
			case EQUAL:
			case NEQ:
			case FADD:
			case FSUB:
			case FMUL:
			case FDIV:
			case FINF:
			case FINFEQ:
			case FSUP:
			case FSUPEQ:
			case FEQUAL:
			case FNEQ:
			case ITOF:
			case FTOI:
			case RETURN:
			case POP:
			case DUP:
			case READ:
			case READF:
			case WRITE:
			case WRITEF:
			case PUSHSP:
			case PUSHFP:
			case HALT:
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				instr1();
				setState(26);
				match(NEWLINE);
				}
				break;
			case PUSHI:
			case PUSHG:
			case STOREG:
			case PUSHL:
			case STOREL:
			case PUSHR:
			case STORER:
			case FREE:
			case ALLOC:
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				instr2();
				setState(29);
				match(NEWLINE);
				}
				break;
			case PUSHF:
				enterOuterAlt(_localctx, 3);
				{
				setState(31);
				instr2f();
				setState(32);
				match(NEWLINE);
				}
				break;
			case LABEL:
				enterOuterAlt(_localctx, 4);
				{
				setState(34);
				label();
				setState(35);
				match(NEWLINE);
				}
				break;
			case CALL:
			case JUMP:
			case JUMPF:
			case JUMPR:
				enterOuterAlt(_localctx, 5);
				{
				setState(37);
				saut();
				setState(38);
				match(NEWLINE);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 6);
				{
				setState(40);
				match(NEWLINE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Commande1Context extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(MVaPParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MVaPParser.SUB, 0); }
		public TerminalNode MUL() { return getToken(MVaPParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MVaPParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MVaPParser.MOD, 0); }
		public TerminalNode INF() { return getToken(MVaPParser.INF, 0); }
		public TerminalNode INFEQ() { return getToken(MVaPParser.INFEQ, 0); }
		public TerminalNode SUP() { return getToken(MVaPParser.SUP, 0); }
		public TerminalNode SUPEQ() { return getToken(MVaPParser.SUPEQ, 0); }
		public TerminalNode EQUAL() { return getToken(MVaPParser.EQUAL, 0); }
		public TerminalNode NEQ() { return getToken(MVaPParser.NEQ, 0); }
		public TerminalNode FADD() { return getToken(MVaPParser.FADD, 0); }
		public TerminalNode FSUB() { return getToken(MVaPParser.FSUB, 0); }
		public TerminalNode FMUL() { return getToken(MVaPParser.FMUL, 0); }
		public TerminalNode FDIV() { return getToken(MVaPParser.FDIV, 0); }
		public TerminalNode FINF() { return getToken(MVaPParser.FINF, 0); }
		public TerminalNode FINFEQ() { return getToken(MVaPParser.FINFEQ, 0); }
		public TerminalNode FSUP() { return getToken(MVaPParser.FSUP, 0); }
		public TerminalNode FSUPEQ() { return getToken(MVaPParser.FSUPEQ, 0); }
		public TerminalNode FEQUAL() { return getToken(MVaPParser.FEQUAL, 0); }
		public TerminalNode FNEQ() { return getToken(MVaPParser.FNEQ, 0); }
		public TerminalNode ITOF() { return getToken(MVaPParser.ITOF, 0); }
		public TerminalNode FTOI() { return getToken(MVaPParser.FTOI, 0); }
		public TerminalNode RETURN() { return getToken(MVaPParser.RETURN, 0); }
		public TerminalNode POP() { return getToken(MVaPParser.POP, 0); }
		public TerminalNode READ() { return getToken(MVaPParser.READ, 0); }
		public TerminalNode READF() { return getToken(MVaPParser.READF, 0); }
		public TerminalNode WRITE() { return getToken(MVaPParser.WRITE, 0); }
		public TerminalNode WRITEF() { return getToken(MVaPParser.WRITEF, 0); }
		public TerminalNode PUSHSP() { return getToken(MVaPParser.PUSHSP, 0); }
		public TerminalNode PUSHFP() { return getToken(MVaPParser.PUSHFP, 0); }
		public TerminalNode DUP() { return getToken(MVaPParser.DUP, 0); }
		public TerminalNode HALT() { return getToken(MVaPParser.HALT, 0); }
		public Commande1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commande1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterCommande1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitCommande1(this);
		}
	}

	public final Commande1Context commande1() throws RecognitionException {
		Commande1Context _localctx = new Commande1Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_commande1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1125968626319344L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class Instr1Context extends ParserRuleContext {
		public Commande1Context c;
		public Commande1Context commande1() {
			return getRuleContext(Commande1Context.class,0);
		}
		public Instr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterInstr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitInstr1(this);
		}
	}

	public final Instr1Context instr1() throws RecognitionException {
		Instr1Context _localctx = new Instr1Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_instr1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			((Instr1Context)_localctx).c = commande1();
			 instrAddress++; 
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

	@SuppressWarnings("CheckReturnValue")
	public static class Commande2Context extends ParserRuleContext {
		public TerminalNode PUSHI() { return getToken(MVaPParser.PUSHI, 0); }
		public TerminalNode PUSHG() { return getToken(MVaPParser.PUSHG, 0); }
		public TerminalNode STOREG() { return getToken(MVaPParser.STOREG, 0); }
		public TerminalNode PUSHL() { return getToken(MVaPParser.PUSHL, 0); }
		public TerminalNode STOREL() { return getToken(MVaPParser.STOREL, 0); }
		public TerminalNode PUSHR() { return getToken(MVaPParser.PUSHR, 0); }
		public TerminalNode STORER() { return getToken(MVaPParser.STORER, 0); }
		public TerminalNode FREE() { return getToken(MVaPParser.FREE, 0); }
		public TerminalNode ALLOC() { return getToken(MVaPParser.ALLOC, 0); }
		public Commande2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commande2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterCommande2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitCommande2(this);
		}
	}

	public final Commande2Context commande2() throws RecognitionException {
		Commande2Context _localctx = new Commande2Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_commande2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 70162585747456L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class Instr2Context extends ParserRuleContext {
		public Commande2Context commande2() {
			return getRuleContext(Commande2Context.class,0);
		}
		public TerminalNode ENTIER() { return getToken(MVaPParser.ENTIER, 0); }
		public Instr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterInstr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitInstr2(this);
		}
	}

	public final Instr2Context instr2() throws RecognitionException {
		Instr2Context _localctx = new Instr2Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_instr2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			commande2();
			setState(51);
			match(ENTIER);
			 instrAddress+=2; /* 2 entiers pour stocker l'instruction */ 
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

	@SuppressWarnings("CheckReturnValue")
	public static class Instr2fContext extends ParserRuleContext {
		public TerminalNode PUSHF() { return getToken(MVaPParser.PUSHF, 0); }
		public TerminalNode FLOAT() { return getToken(MVaPParser.FLOAT, 0); }
		public Instr2fContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr2f; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterInstr2f(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitInstr2f(this);
		}
	}

	public final Instr2fContext instr2f() throws RecognitionException {
		Instr2fContext _localctx = new Instr2fContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_instr2f);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(PUSHF);
			setState(55);
			match(FLOAT);
			 instrAddress+=3; /* 1 entier pour le code op et 2 pour le flottant */ 
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

	@SuppressWarnings("CheckReturnValue")
	public static class CommandeSautContext extends ParserRuleContext {
		public TerminalNode JUMP() { return getToken(MVaPParser.JUMP, 0); }
		public TerminalNode JUMPF() { return getToken(MVaPParser.JUMPF, 0); }
		public TerminalNode JUMPR() { return getToken(MVaPParser.JUMPR, 0); }
		public TerminalNode CALL() { return getToken(MVaPParser.CALL, 0); }
		public CommandeSautContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commandeSaut; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterCommandeSaut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitCommandeSaut(this);
		}
	}

	public final CommandeSautContext commandeSaut() throws RecognitionException {
		CommandeSautContext _localctx = new CommandeSautContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_commandeSaut);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1055531162664960L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class SautContext extends ParserRuleContext {
		public CommandeSautContext commandeSaut() {
			return getRuleContext(CommandeSautContext.class,0);
		}
		public TerminalNode IDENTIFIANT() { return getToken(MVaPParser.IDENTIFIANT, 0); }
		public SautContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_saut; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterSaut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitSaut(this);
		}
	}

	public final SautContext saut() throws RecognitionException {
		SautContext _localctx = new SautContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_saut);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			commandeSaut();
			setState(61);
			match(IDENTIFIANT);
			 instrAddress+=2; /* 2 entiers pour stocker l'instruction */ 
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

	@SuppressWarnings("CheckReturnValue")
	public static class LabelContext extends ParserRuleContext {
		public Token IDENTIFIANT;
		public TerminalNode LABEL() { return getToken(MVaPParser.LABEL, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(MVaPParser.IDENTIFIANT, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPListener ) ((MVaPListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(LABEL);
			setState(65);
			((LabelContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			 labels.put((((LabelContext)_localctx).IDENTIFIANT!=null?((LabelContext)_localctx).IDENTIFIANT.getText():null), instrAddress); 
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
		"\u0004\u00015E\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007"+
		"\b\u0002\t\u0007\t\u0001\u0000\u0004\u0000\u0016\b\u0000\u000b\u0000\f"+
		"\u0000\u0017\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"*\b\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0000\u0000\n\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0000"+
		"\u0003\u0002\u0000\u0004#22\u0002\u0000$$&-\u0001\u0000.1@\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0002)\u0001\u0000\u0000\u0000\u0004+\u0001\u0000"+
		"\u0000\u0000\u0006-\u0001\u0000\u0000\u0000\b0\u0001\u0000\u0000\u0000"+
		"\n2\u0001\u0000\u0000\u0000\f6\u0001\u0000\u0000\u0000\u000e:\u0001\u0000"+
		"\u0000\u0000\u0010<\u0001\u0000\u0000\u0000\u0012@\u0001\u0000\u0000\u0000"+
		"\u0014\u0016\u0003\u0002\u0001\u0000\u0015\u0014\u0001\u0000\u0000\u0000"+
		"\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0015\u0001\u0000\u0000\u0000"+
		"\u0017\u0018\u0001\u0000\u0000\u0000\u0018\u0001\u0001\u0000\u0000\u0000"+
		"\u0019\u001a\u0003\u0006\u0003\u0000\u001a\u001b\u00055\u0000\u0000\u001b"+
		"*\u0001\u0000\u0000\u0000\u001c\u001d\u0003\n\u0005\u0000\u001d\u001e"+
		"\u00055\u0000\u0000\u001e*\u0001\u0000\u0000\u0000\u001f \u0003\f\u0006"+
		"\u0000 !\u00055\u0000\u0000!*\u0001\u0000\u0000\u0000\"#\u0003\u0012\t"+
		"\u0000#$\u00055\u0000\u0000$*\u0001\u0000\u0000\u0000%&\u0003\u0010\b"+
		"\u0000&\'\u00055\u0000\u0000\'*\u0001\u0000\u0000\u0000(*\u00055\u0000"+
		"\u0000)\u0019\u0001\u0000\u0000\u0000)\u001c\u0001\u0000\u0000\u0000)"+
		"\u001f\u0001\u0000\u0000\u0000)\"\u0001\u0000\u0000\u0000)%\u0001\u0000"+
		"\u0000\u0000)(\u0001\u0000\u0000\u0000*\u0003\u0001\u0000\u0000\u0000"+
		"+,\u0007\u0000\u0000\u0000,\u0005\u0001\u0000\u0000\u0000-.\u0003\u0004"+
		"\u0002\u0000./\u0006\u0003\uffff\uffff\u0000/\u0007\u0001\u0000\u0000"+
		"\u000001\u0007\u0001\u0000\u00001\t\u0001\u0000\u0000\u000023\u0003\b"+
		"\u0004\u000034\u0005\u0002\u0000\u000045\u0006\u0005\uffff\uffff\u0000"+
		"5\u000b\u0001\u0000\u0000\u000067\u0005%\u0000\u000078\u0005\u0003\u0000"+
		"\u000089\u0006\u0006\uffff\uffff\u00009\r\u0001\u0000\u0000\u0000:;\u0007"+
		"\u0002\u0000\u0000;\u000f\u0001\u0000\u0000\u0000<=\u0003\u000e\u0007"+
		"\u0000=>\u00054\u0000\u0000>?\u0006\b\uffff\uffff\u0000?\u0011\u0001\u0000"+
		"\u0000\u0000@A\u00053\u0000\u0000AB\u00054\u0000\u0000BC\u0006\t\uffff"+
		"\uffff\u0000C\u0013\u0001\u0000\u0000\u0000\u0002\u0017)";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}