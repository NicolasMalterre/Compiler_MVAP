// Generated from /home/nico/Documents/Licence/S2/Langage/TPCalc/Calculette.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CalculetteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, TYPEV=31, 
		RETURN=32, NEWLINE=33, WS=34, FLOTTANT=35, ENTIER=36, BOOL=37, OPERATEUR=38, 
		COMMENT=39, IDENTIFIANT=40, UNMATCH=41;
	public static final int
		RULE_start = 0, RULE_calcul = 1, RULE_instruction = 2, RULE_expression = 3, 
		RULE_decl = 4, RULE_assignation = 5, RULE_get = 6, RULE_put = 7, RULE_bloc = 8, 
		RULE_condition = 9, RULE_boucle = 10, RULE_breakBoucle = 11, RULE_continueBoucle = 12, 
		RULE_branchement = 13, RULE_params = 14, RULE_fonction = 15, RULE_args = 16, 
		RULE_finInstruction = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "calcul", "instruction", "expression", "decl", "assignation", 
			"get", "put", "bloc", "condition", "boucle", "breakBoucle", "continueBoucle", 
			"branchement", "params", "fonction", "args", "finInstruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'('", "')'", "'*'", "'/'", "'%'", "'['", "']'", 
			"'::'", "'='", "','", "'++'", "'--'", "'get('", "'put('", "'{'", "'}'", 
			"'and'", "'or'", "'not'", "'while'", "'for'", "';'", "'break'", "'continue'", 
			"'if'", "'then'", "'else'", "':='", null, "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "TYPEV", "RETURN", "NEWLINE", 
			"WS", "FLOTTANT", "ENTIER", "BOOL", "OPERATEUR", "COMMENT", "IDENTIFIANT", 
			"UNMATCH"
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
	public String getGrammarFileName() { return "Calculette.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	    private int _cur_label = 1;
	    /** générateur de nom d'étiquettes pour les boucles */
	    private String newLabel( ) { return "Label"+(_cur_label++); };

	    //evaluation operation arithmetique
	    private String evalexpr (String op, String type) {
	        String pre = type.equals("float") ? "F" : "";
	        if ( op.equals("*") ){
	            return pre + "MUL\n";
	        } else if ( op.equals("+") ){
	            return pre + "ADD\n";
	        } else if ( op.equals("/") ){
	            return pre + "DIV\n";
	        } else if ( op.equals("-") ){
	            return pre + "SUB\n";
	        } else if ( op.equals("%") ){
	            if (type.equals("int")){
	                return "MOD\n";
	            } else{
	                System.err.println("Opérateur arithmétique incorrect avec les floats: '"+op+"'");
	                throw new IllegalArgumentException("Opérateur arithmétique incorrect avec les floats : '"+op+"'");
	            }
	        
	        } else {
	           System.err.println("Opérateur arithmétique incorrect : '"+op+"'");
	           throw new IllegalArgumentException("Opérateur arithmétique incorrect : '"+op+"'");
	        }
	    }

	    //fonction pour cast automatique d'une expression/condition
	    private String[] promotion(String aCode, String aType, String bCode, String bType) {         
	        String resultCode = "";
	        String resultType = "";
	        if (aType.equals("int") && bType.equals("float")) {
	            resultCode = aCode + "ITOF\n" + bCode;
	            resultType = "float"; 
	        } else if (aType.equals("float") && bType.equals("int")) {           
	            resultCode = aCode + bCode + "ITOF\n"; 
	            resultType = "float";         
	        } 
	        else {           
	            resultCode = aCode + bCode;         
	            resultType = aType;         
	        }             
	        return new String[]{resultCode, resultType};    
	    }

	    private String push(String type, String val){
	        return type.equals("float") ? "PUSHF " + val + ".0 \n" : "PUSHI " + val + "\n" ;
	    }

	    //evaluation operation logique
	    private String evaloper(String op, String type){
	        String pre = type.equals("float") ? "F" : "";
	        if ( op.equals(">") ){
	            return pre + "SUP\n";
	        } else if ( op.equals(">=") ){
	            return pre + "SUPEQ\n";
	        } else if ( op.equals("<") ){
	            return pre + "INF\n";
	        } else if ( op.equals("<=") ){
	            return pre + "INFEQ\n";
	        } else if ( op.equals("==") ){
	            return pre + "EQUAL\n";
	        } else if ( op.equals("!=") ){
	            return pre + "NEQ\n";
	        } else {
	           System.err.println("Opérateur logique incorrect : '"+op+"'");
	           throw new IllegalArgumentException("Opérateur logique incorrect : '"+op+"'");
	        }
	    }

	    private String storeArray(VariableInfo vi, int off, int elemSize, String exprCode) {
	        if (elemSize == 1) {
	            return "DUP\n" + exprCode + "STORER " + off + "\n";
	        } else {
	            if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
	                return exprCode + "STOREL " + (vi.address + off + 1) + "\n" + "STOREL " + (vi.address + off) + "\n";
	            } else {
	                return exprCode + "STOREG " + (vi.address + off + 1) + "\n" + "STOREG " + (vi.address + off) + "\n";
	            }
	        }
	    }

	    private java.util.Stack<String> breakStack = new java.util.Stack<>();
	    private java.util.Stack<String> continueStack = new java.util.Stack<>();
	    
	    // HashMap pour stocker la taille des tableaux (nom -> taille)
	    private java.util.HashMap<String, Integer> arraySize = new java.util.HashMap<>();

	    private java.util.Stack<TablesSymboles> tableStack = new java.util.Stack<>();
	    private TablesSymboles tablesSymboles = new TablesSymboles();
	    {tableStack.push(tablesSymboles);}

	    private void enterTableStack(){
	        TablesSymboles newTable = new TablesSymboles(tablesSymboles);
	        newTable.enterFunction();
	        tableStack.push(newTable);
	        tablesSymboles = newTable;
	    }

	    private void exitTableStack(){
	        if (!tableStack.isEmpty()) {
	            tablesSymboles.exitFunction();
	            tableStack.pop();
	            if (!tableStack.isEmpty()) {
	                tablesSymboles = tableStack.peek();
	            }
	        }
	    }

	    

	public CalculetteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public CalculContext calcul() {
			return getRuleContext(CalculContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CalculetteParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			calcul();
			setState(37);
			match(EOF);
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
	public static class CalculContext extends ParserRuleContext {
		public String code;
		public DeclContext decl;
		public FonctionContext fonction;
		public InstructionContext instruction;
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<FonctionContext> fonction() {
			return getRuleContexts(FonctionContext.class);
		}
		public FonctionContext fonction(int i) {
			return getRuleContext(FonctionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public CalculContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcul; }
	}

	public final CalculContext calcul() throws RecognitionException {
		CalculContext _localctx = new CalculContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_calcul);
		 
		    ((CalculContext)_localctx).code =  new String(); 
		    int globalSize = 0;

		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(39);
					((CalculContext)_localctx).decl = decl();
					 
					            _localctx.code += ((CalculContext)_localctx).decl.code; 
					            globalSize += ((CalculContext)_localctx).decl.size;
					        
					}
					} 
				}
				setState(46);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			 _localctx.code += "  JUMP Start\n"; 
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(48);
					match(NEWLINE);
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(59);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(54);
					((CalculContext)_localctx).fonction = fonction();
					 _localctx.code += ((CalculContext)_localctx).fonction.code; 
					}
					} 
				}
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(65);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(62);
					match(NEWLINE);
					}
					} 
				}
				setState(67);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			 _localctx.code += "LABEL Start\n"; 
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1355328749582L) != 0)) {
				{
				{
				setState(69);
				((CalculContext)_localctx).instruction = instruction();
				 _localctx.code += ((CalculContext)_localctx).instruction.code; 
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 
			            
			            for(int i = 0; i < globalSize; i++) {
			                _localctx.code += "POP\n";
			            }
			            _localctx.code += "HALT\n"; 
			        
			}
			_ctx.stop = _input.LT(-1);
			 System.out.println(_localctx.code); 
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
	public static class InstructionContext extends ParserRuleContext {
		public String code;
		public ExpressionContext expression;
		public AssignationContext assignation;
		public GetContext get;
		public PutContext put;
		public ConditionContext condition;
		public DeclContext decl;
		public BlocContext bloc;
		public BreakBoucleContext breakBoucle;
		public ContinueBoucleContext continueBoucle;
		public BoucleContext boucle;
		public BranchementContext branchement;
		public FonctionContext fonction;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public GetContext get() {
			return getRuleContext(GetContext.class,0);
		}
		public PutContext put() {
			return getRuleContext(PutContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public BreakBoucleContext breakBoucle() {
			return getRuleContext(BreakBoucleContext.class,0);
		}
		public ContinueBoucleContext continueBoucle() {
			return getRuleContext(ContinueBoucleContext.class,0);
		}
		public BoucleContext boucle() {
			return getRuleContext(BoucleContext.class,0);
		}
		public BranchementContext branchement() {
			return getRuleContext(BranchementContext.class,0);
		}
		public FonctionContext fonction() {
			return getRuleContext(FonctionContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(CalculetteParser.RETURN, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				((InstructionContext)_localctx).expression = expression(0);
				setState(80);
				finInstruction();
				 
				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code ;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				((InstructionContext)_localctx).assignation = assignation();
				setState(84);
				finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).assignation.code ;
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				((InstructionContext)_localctx).get = get();
				setState(88);
				finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).get.code;
				        
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(91);
				((InstructionContext)_localctx).put = put();
				setState(92);
				finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).put.code;
				        
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(95);
				((InstructionContext)_localctx).condition = condition(0);
				setState(96);
				finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).condition.code;
				        
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(99);
				((InstructionContext)_localctx).decl = decl();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).decl.code;
				        
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(102);
				((InstructionContext)_localctx).bloc = bloc();
				setState(103);
				finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).bloc.code;
				        
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(106);
				((InstructionContext)_localctx).breakBoucle = breakBoucle();
				setState(107);
				finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).breakBoucle.code;
				        
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(110);
				((InstructionContext)_localctx).continueBoucle = continueBoucle();
				setState(111);
				finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).continueBoucle.code;
				        
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(114);
				((InstructionContext)_localctx).boucle = boucle();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).boucle.code;
				        
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(117);
				((InstructionContext)_localctx).branchement = branchement();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).branchement.code;
				        
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(120);
				((InstructionContext)_localctx).branchement = branchement();
				setState(121);
				finInstruction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).branchement.code;
				        
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(124);
				((InstructionContext)_localctx).fonction = fonction();

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).fonction.code;
				        
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(127);
				finInstruction();

				            ((InstructionContext)_localctx).code = "";
				        
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(130);
				match(RETURN);
				setState(131);
				((InstructionContext)_localctx).expression = expression(0);
				setState(132);
				finInstruction();

				            VariableInfo vi = tablesSymboles.getReturn();

				            if (((InstructionContext)_localctx).expression.type.equals("float")) {
				                ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code
				                    + "STOREL " + (vi.address + 1) + "\n"
				                    + "STOREL " + vi.address + "\n"
				                    + "RETURN\n";
				            } else if (((InstructionContext)_localctx).expression.type.equals("int") || ((InstructionContext)_localctx).expression.type.equals("bool")){
				                ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code
				                    + "STOREL " + vi.address + "\n"
				                    + "RETURN\n";
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public String code;
		public String type;
		public ExpressionContext a;
		public ExpressionContext e;
		public Token TYPEV;
		public Token ent;
		public Token fl;
		public Token bo;
		public Token IDENTIFIANT;
		public ArgsContext args;
		public Token ENTIER;
		public Token op;
		public ExpressionContext b;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode TYPEV() { return getToken(CalculetteParser.TYPEV, 0); }
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public TerminalNode FLOTTANT() { return getToken(CalculetteParser.FLOTTANT, 0); }
		public TerminalNode BOOL() { return getToken(CalculetteParser.BOOL, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(138);
				match(T__0);
				setState(139);
				((ExpressionContext)_localctx).e = expression(13);
				((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).e.type;
				}
				break;
			case 2:
				{
				setState(142);
				match(T__1);
				setState(143);
				((ExpressionContext)_localctx).e = expression(12);

				        ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).e.type; 
				        ((ExpressionContext)_localctx).code =  push(((ExpressionContext)_localctx).e.type,"-1") + ((ExpressionContext)_localctx).e.code + evalexpr("*",((ExpressionContext)_localctx).e.type);
				        
				}
				break;
			case 3:
				{
				setState(146);
				match(T__2);
				setState(147);
				((ExpressionContext)_localctx).TYPEV = match(TYPEV);
				setState(148);
				match(T__3);
				setState(149);
				((ExpressionContext)_localctx).e = expression(11);

				        if ((((ExpressionContext)_localctx).TYPEV!=null?((ExpressionContext)_localctx).TYPEV.getText():null).equals("int")) {
				            if (((ExpressionContext)_localctx).e.type.equals("float")) {
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code + "FTOI\n";
				            } else {
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code;
				            }
				            ((ExpressionContext)_localctx).type =  "int";
				        } else if ((((ExpressionContext)_localctx).TYPEV!=null?((ExpressionContext)_localctx).TYPEV.getText():null).equals("float")) {
				            if (((ExpressionContext)_localctx).e.type.equals("int") || ((ExpressionContext)_localctx).e.type.equals("bool")) {
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code + "ITOF\n";
				            } else {
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code;
				            }
				            ((ExpressionContext)_localctx).type =  "float";
				        } else if ((((ExpressionContext)_localctx).TYPEV!=null?((ExpressionContext)_localctx).TYPEV.getText():null).equals("bool")) {
				            if (((ExpressionContext)_localctx).e.type.equals("int") || ((ExpressionContext)_localctx).e.type.equals("bool")) {
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code + "PUSHI 0\nEQUAL\nPUSHI 0\nEQUAL\n";
				            } else if (((ExpressionContext)_localctx).e.type.equals("float")) {
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code + "FTOI\nPUSHI 0\nEQUAL\nPUSHI 0\nEQUAL\n";
				            } else {
				                ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code;
				            }
				            ((ExpressionContext)_localctx).type =  "bool";
				        } else {
				            ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code;
				            ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).e.type;
				        }
				    
				}
				break;
			case 4:
				{
				setState(152);
				match(T__2);
				setState(153);
				((ExpressionContext)_localctx).e = expression(0);
				setState(154);
				match(T__3);
				((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).e.code; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).e.type;
				}
				break;
			case 5:
				{
				setState(157);
				((ExpressionContext)_localctx).ent = match(ENTIER);
				((ExpressionContext)_localctx).code =  "PUSHI "+(((ExpressionContext)_localctx).ent!=null?((ExpressionContext)_localctx).ent.getText():null)+"\n"; ((ExpressionContext)_localctx).type =  "int";
				}
				break;
			case 6:
				{
				setState(159);
				((ExpressionContext)_localctx).fl = match(FLOTTANT);
				((ExpressionContext)_localctx).code =  "PUSHF "+(((ExpressionContext)_localctx).fl!=null?((ExpressionContext)_localctx).fl.getText():null)+"\n"; ((ExpressionContext)_localctx).type =  "float";
				}
				break;
			case 7:
				{
				setState(161);
				((ExpressionContext)_localctx).bo = match(BOOL);
				 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).bo.getText().equals("True") ? "PUSHI 1\n" : "PUSHI 0\n"; ((ExpressionContext)_localctx).type =  "bool"; 
				}
				break;
			case 8:
				{
				setState(163);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				        VariableInfo vi = tablesSymboles.getVar((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				        ((ExpressionContext)_localctx).type =  vi.type;

				        int currentDepth = tablesSymboles.getCurrentDepth();
				        int diffDepth = currentDepth - vi.depth;

				        if (diffDepth <= 1){
				            if (vi.type.equals("float")) {
				                if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                    ((ExpressionContext)_localctx).code =  "PUSHL " + vi.address + "\nPUSHL " + (vi.address + 1) + "\n";
				                } else {
				                    ((ExpressionContext)_localctx).code =  "PUSHG " + vi.address + "\nPUSHG " + (vi.address + 1) + "\n";
				                }
				            } else {
				                if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                    ((ExpressionContext)_localctx).code =  "PUSHL " + vi.address + "\n";
				                } else {
				                    ((ExpressionContext)_localctx).code =  "PUSHG " + vi.address + "\n";
				                }
				            }
				        } else {
				            String tmp = "PUSHL 0\n";
				            for (int i = 1; i < diffDepth; i++) {
				                tmp += "PUSHR 0\n";  // Suivre la chaîne
				            }
				            if (vi.type.equals("float")) {
				                ((ExpressionContext)_localctx).code =  tmp + "PUSHR " + vi.address + "\n" + tmp + "PUSHR " + (vi.address+1) + "\n";
				            } else {
				                ((ExpressionContext)_localctx).code =  tmp + "PUSHR " + vi.address + "\n";
				            }
				        }
				    
				}
				break;
			case 9:
				{
				setState(165);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(166);
				match(T__2);
				setState(167);
				match(T__3);

				            ((ExpressionContext)_localctx).type =  tablesSymboles.getFunction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				            ((ExpressionContext)_localctx).code =  push(_localctx.type,"0") + "PUSHFP\n" + "CALL " + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
				            _localctx.code += "POP\n"; 
				        
				}
				break;
			case 10:
				{
				setState(169);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(170);
				match(T__2);
				setState(171);
				((ExpressionContext)_localctx).args = args();
				setState(172);
				match(T__3);

				            ((ExpressionContext)_localctx).type =  tablesSymboles.getFunction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				            ((ExpressionContext)_localctx).code =  push(_localctx.type,"0") + ((ExpressionContext)_localctx).args.code + "PUSHFP\n" +"CALL " + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "\n";

				            for(int i = 0; i < ((ExpressionContext)_localctx).args.size+1; i++) {
				                _localctx.code += "POP\n";
				            }
				        
				}
				break;
			case 11:
				{
				setState(175);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(176);
				match(T__7);
				setState(177);
				((ExpressionContext)_localctx).ENTIER = match(ENTIER);
				setState(178);
				match(T__8);

				            VariableInfo vi = tablesSymboles.getVar((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				            String typeVi = vi.type.replaceAll("\\[\\]$", "");
				            String brackets = vi.type.substring(typeVi.length());

				            ((ExpressionContext)_localctx).type =  typeVi;

				            if (brackets.equals("[]") ) {
				                int pre = Integer.parseInt((((ExpressionContext)_localctx).ENTIER!=null?((ExpressionContext)_localctx).ENTIER.getText():null));
				                int elemSize = typeVi.equals("float") ? 2 : 1;
				                int off = pre * elemSize;
				                if (elemSize == 1) {
				                    ((ExpressionContext)_localctx).code =  "PUSHI " + vi.address + "\n" + "PUSHR " + off + "\n";
				                } else {
				                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                        ((ExpressionContext)_localctx).code =   "PUSHL " + (vi.address + off + 1) + "\n" + "PUSHL " + (vi.address + off) + "\n";
				                    } else {
				                        ((ExpressionContext)_localctx).code =  "PUSHG " + (vi.address + off + 1) + "\n" + "PUSHG " + (vi.address + off) + "\n";
				                    }
				                }
				            } else {
				                throw new IllegalArgumentException("Type incorrect: " + vi.type);
				            }
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(194);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(192);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(182);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(183);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 224L) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(184);
						((ExpressionContext)_localctx).b = expression(10);

						                  String[] prom = promotion(((ExpressionContext)_localctx).a.code,((ExpressionContext)_localctx).a.type,((ExpressionContext)_localctx).b.code,((ExpressionContext)_localctx).b.type) ; 
						                  ((ExpressionContext)_localctx).code =  prom[0] + evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null), prom[1]);
						                  ((ExpressionContext)_localctx).type =  prom[1];
						              
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(188);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__0 || _la==T__1) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(189);
						((ExpressionContext)_localctx).b = expression(9);

						                  String[] prom = promotion(((ExpressionContext)_localctx).a.code,((ExpressionContext)_localctx).a.type,((ExpressionContext)_localctx).b.code,((ExpressionContext)_localctx).b.type) ; 
						                  ((ExpressionContext)_localctx).code =  prom[0] + evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null), prom[1]);
						                  ((ExpressionContext)_localctx).type =  prom[1];
						              
						}
						break;
					}
					} 
				}
				setState(196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public String code;
		public int size;
		public Token IDENTIFIANT;
		public Token TYPEV;
		public Token ENTIER;
		public ExpressionContext expression;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public TerminalNode TYPEV() { return getToken(CalculetteParser.TYPEV, 0); }
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_decl);
		int _la;
		try {
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(197);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(198);
				match(T__9);
				setState(199);
				((DeclContext)_localctx).TYPEV = match(TYPEV);
				setState(200);
				finInstruction();

				        if ((((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null).equals("int")){
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				            ((DeclContext)_localctx).size =  1;
				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),"int");
				        } else if ((((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null).equals("float")){
				            ((DeclContext)_localctx).code =  "PUSHF 0.0\n";
				            ((DeclContext)_localctx).size =  2;
				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),"float");
				        } else if ((((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null).equals("bool")){
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				            ((DeclContext)_localctx).size =  1;
				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),"bool");
				        }
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(204);
				match(T__9);
				setState(205);
				((DeclContext)_localctx).TYPEV = match(TYPEV);
				setState(206);
				match(T__7);
				setState(207);
				((DeclContext)_localctx).ENTIER = match(ENTIER);
				setState(208);
				match(T__8);
				setState(209);
				finInstruction();

				            int nElem = Integer.parseInt((((DeclContext)_localctx).ENTIER!=null?((DeclContext)_localctx).ENTIER.getText():null));
				            int elemSize = (((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null).equals("float") ? 2 : 1;
				            int nAlloc = elemSize * (nElem + 1); 
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";

				    
				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),(((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null) + "[]");
				            arraySize.put((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), nElem);
				            VariableInfo vi = tablesSymboles.getVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null));

				            _localctx.code += "ALLOC " + nAlloc + "\n"+ "PUSHSP\n"+ "PUSHI " + nAlloc + "\n"+ "SUB\n" + "DUP\n" + "PUSHI " + (elemSize * nElem) + "\nADD\n" + "PUSHI -1\n" + "STORER 0\n";
				            
				            ((DeclContext)_localctx).size =  1 + nAlloc;
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(213);
				match(T__10);
				setState(214);
				((DeclContext)_localctx).expression = expression(0);
				setState(215);
				match(T__9);
				setState(216);
				((DeclContext)_localctx).TYPEV = match(TYPEV);
				setState(217);
				finInstruction();

				        if ((((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null).equals("int")){
				            ((DeclContext)_localctx).code =  ((DeclContext)_localctx).expression.code;
				            ((DeclContext)_localctx).size =  1;
				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),"int");
				        
				        } else if ((((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null).equals("float")){
				            ((DeclContext)_localctx).code =  ((DeclContext)_localctx).expression.code;
				            ((DeclContext)_localctx).size =  2;
				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),"float");
				        } else if ((((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null).equals("bool")){
				            ((DeclContext)_localctx).code =  ((DeclContext)_localctx).expression.code;
				            ((DeclContext)_localctx).size =  1;
				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),"bool");
				        }
				        
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(220);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(221);
				match(T__10);
				setState(222);
				match(T__7);

				            java.util.List<String> initValues = new java.util.ArrayList<String>();
				            ((DeclContext)_localctx).size =  0;
				        
				setState(224);
				((DeclContext)_localctx).expression = expression(0);

				            initValues.add(((DeclContext)_localctx).expression.code);
				            ((DeclContext)_localctx).size =  ((DeclContext)_localctx).expression.type.equals("float") ? 2 : 1;
				        
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(226);
					match(T__11);
					setState(227);
					((DeclContext)_localctx).expression = expression(0);

					            initValues.add(((DeclContext)_localctx).expression.code);
					            _localctx.size += ((DeclContext)_localctx).expression.type.equals("float") ? 2 : 1;
					        
					}
					}
					setState(234);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(235);
				match(T__8);
				setState(236);
				match(T__9);
				setState(237);
				((DeclContext)_localctx).TYPEV = match(TYPEV);
				setState(238);
				match(T__7);
				setState(239);
				((DeclContext)_localctx).ENTIER = match(ENTIER);
				setState(240);
				match(T__8);
				setState(241);
				finInstruction();

				            int nElem = Integer.parseInt((((DeclContext)_localctx).ENTIER!=null?((DeclContext)_localctx).ENTIER.getText():null));
				            int elemSize = (((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null).equals("float") ? 2 : 1;
				            int nAlloc = elemSize * (nElem + 1); 

				            if (_localctx.size != (nElem * elemSize) ){
				                throw new IllegalArgumentException("Nombre de valeurs incorrects : " + _localctx.size);
				            }

				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				            tablesSymboles.addVarDecl((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),(((DeclContext)_localctx).TYPEV!=null?((DeclContext)_localctx).TYPEV.getText():null)+"[]");
				            arraySize.put((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), nElem);


				            VariableInfo vi = tablesSymboles.getVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null));

				            _localctx.code += "ALLOC " + nAlloc + "\n"+ "PUSHSP\n"+ "PUSHI " + nAlloc + "\n"+ "SUB\n" + "DUP\n" + "PUSHI " + (elemSize * nElem) + "\nADD\n" + "PUSHI -1\n" + "STORER 0\n";

				            for (int i = 0; i < nElem; i++) {
				                int off = i * elemSize;
				                _localctx.code += storeArray(vi, off, elemSize, initValues.get(i));
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignationContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext expression;
		public Token operator;
		public Token ENTIER;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignation);
		int _la;
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(246);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(247);
				match(T__10);
				setState(248);
				((AssignationContext)_localctx).expression = expression(0);
				   VariableInfo vi = tablesSymboles.getVar((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
				            int currentDepth = tablesSymboles.getCurrentDepth();
				            int diffDepth = currentDepth - vi.depth;
				            
				            if (diffDepth <= 1) {
				                if (vi.type.equals("float")) {
				                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                        ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code + "STOREL " + (vi.address + 1) + "\nSTOREL " + vi.address + "\n";
				                    } else {
				                        ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code + "STOREG " + (vi.address + 1) + "\nSTOREG " + vi.address + "\n";
				                    }
				                } else {
				                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                        ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code + "STOREL " + vi.address + "\n";
				                    } else {
				                        ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code + "STOREG " + vi.address + "\n";
				                    }
				                }
				            } else {
				                String tmp = "PUSHL 0\n";
				                for (int i = 1; i < diffDepth; i++) {
				                    tmp += "PUSHR 0\n";
				                }
				                if (vi.type.equals("float")) {
				                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code + tmp + "STORER " + (vi.address+1) + "\n" + tmp + "STORER " + vi.address + "\n";
				                } else {
				                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).expression.code + tmp + "STORER " + vi.address + "\n";
				                }
				            }
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(252);
				((AssignationContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
					((AssignationContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				            VariableInfo vi = tablesSymboles.getVar((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
				            int currentDepth = tablesSymboles.getCurrentDepth();
				            int diffDepth = currentDepth - vi.depth;
				            
				            if (diffDepth <= 1) {
				                if (vi.type.equals("float")) {
				                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                        ((AssignationContext)_localctx).code =  "PUSHL " + vi.address + "\nPUSHL " + (vi.address + 1) + "\n" + push(vi.type,"1");
				                    } else {
				                        ((AssignationContext)_localctx).code =  "PUSHG " + vi.address + "\nPUSHG " + (vi.address + 1) + "\n" + push(vi.type,"1");
				                    }
				                } else {
				                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                        ((AssignationContext)_localctx).code =  "PUSHL " + vi.address + "\n" + push(vi.type,"1");
				                    } else {
				                        ((AssignationContext)_localctx).code =  "PUSHG " + vi.address + "\n" + push(vi.type,"1");
				                    }
				                }
				            } else {
				                String tmp = "PUSHL 0\n";
				                for (int i = 1; i < diffDepth; i++) {
				                    tmp += "PUSHR 0\n";
				                }
				                if (vi.type.equals("float")) {
				                    ((AssignationContext)_localctx).code =  tmp + "PUSHR " + vi.address + "\n" + tmp + "PUSHR " + (vi.address+1) + "\n" + push(vi.type,"1");
				                } else {
				                    ((AssignationContext)_localctx).code =  tmp + "PUSHR " + vi.address + "\n" + push(vi.type,"1");
				                }
				            }
				            if((((AssignationContext)_localctx).operator!=null?((AssignationContext)_localctx).operator.getText():null).equals("++")){
				                _localctx.code += evalexpr("+",vi.type);
				            }
				            else{
				                
				                _localctx.code += evalexpr("-",vi.type);
				            }
				            if (diffDepth <= 1) {
				                if (vi.type.equals("float")) {
				                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                        _localctx.code += "STOREL " + (vi.address + 1) + "\nSTOREL " + vi.address + "\n";
				                    } else {
				                        _localctx.code += "STOREG " + (vi.address + 1) + "\nSTOREG " + vi.address + "\n";
				                    }
				                } else {
				                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
				                        _localctx.code += "STOREL " + vi.address + "\n";
				                    } else {
				                        _localctx.code += "STOREG " + vi.address + "\n";
				                    }
				                }
				            } else {
				                String tmp = "PUSHL 0\n";
				                for (int i = 1; i < diffDepth; i++) {
				                    tmp += "PUSHR 0\n";
				                }
				                if (vi.type.equals("float")) {
				                    _localctx.code += tmp + "STORER " + (vi.address+1) + "\n" + tmp + "STORER " + vi.address + "\n";
				                } else {
				                    _localctx.code += tmp + "STORER " + vi.address + "\n";
				                }
				            }
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(254);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(255);
				match(T__10);
				setState(256);
				match(T__7);

				            java.util.List<String> initValues = new java.util.ArrayList<String>();
				            String type = "int";
				        
				setState(258);
				((AssignationContext)_localctx).expression = expression(0);

				            initValues.add(((AssignationContext)_localctx).expression.code);
				            if (((AssignationContext)_localctx).expression.type.equals("float")){
				                type = "float";
				            }
				        
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(260);
					match(T__11);
					setState(261);
					((AssignationContext)_localctx).expression = expression(0);

					            
					            if (((AssignationContext)_localctx).expression.type.equals("float") && type.equals("float")){
					                type = "float";
					                initValues.add(((AssignationContext)_localctx).expression.code);
					            }
					            else if (((AssignationContext)_localctx).expression.type.equals("int") && type.equals("int")){
					                type = "int";
					                initValues.add(((AssignationContext)_localctx).expression.code);
					            }
					            else {
					                throw new IllegalArgumentException("Type incorrect: 2" + type);
					            }
					        
					}
					}
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(269);
				match(T__8);

				            VariableInfo vi = tablesSymboles.getVar((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));

				            String typeVi = vi.type.replaceAll("\\[\\]$", "");
				            String brackets = vi.type.substring(typeVi.length());
				            int nElem = initValues.size();
				            
				            if (brackets.equals("[]") && typeVi.equals(type)) {
				                Integer size= arraySize.get((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
				                if (size != null && nElem != size) {
				                    throw new IllegalArgumentException("Nombre d'éléments incorrect: " + nElem + " fournis, " + size + " attendus pour le tableau " + (((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
				                }
				                int elemSize = type.equals("float") ? 2 : 1;
				                ((AssignationContext)_localctx).code =  "";
				                for (int i = 0; i < nElem; i++) {
				                    int off = i * elemSize;
				                    _localctx.code += storeArray(vi, off, elemSize, initValues.get(i));
				                }
				            } else {
				                throw new IllegalArgumentException("Type incorrect: " + vi.type);
				            }
				        
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(272);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(273);
				match(T__7);
				setState(274);
				((AssignationContext)_localctx).ENTIER = match(ENTIER);
				setState(275);
				match(T__8);
				setState(276);
				match(T__10);
				setState(277);
				((AssignationContext)_localctx).expression = expression(0);

				            VariableInfo vi = tablesSymboles.getVar((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));

				            String typeVi = vi.type.replaceAll("\\[\\]$", "");
				            String brackets = vi.type.substring(typeVi.length());
				            if (brackets.equals("[]") && typeVi.equals(((AssignationContext)_localctx).expression.type)) {
				                int pre = Integer.parseInt((((AssignationContext)_localctx).ENTIER!=null?((AssignationContext)_localctx).ENTIER.getText():null));
				                int elemSize = typeVi.equals("float") ? 2 : 1;
				                int off = pre * elemSize;
				                ((AssignationContext)_localctx).code =  storeArray(vi, off, elemSize, ((AssignationContext)_localctx).expression.code);
				            } else {
				                throw new IllegalArgumentException("Type incorrect: " + vi.type);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GetContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public GetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get; }
	}

	public final GetContext get() throws RecognitionException {
		GetContext _localctx = new GetContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_get);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(T__14);
			setState(283);
			((GetContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(284);
			match(T__3);

			            VariableInfo vi = tablesSymboles.getVar((((GetContext)_localctx).IDENTIFIANT!=null?((GetContext)_localctx).IDENTIFIANT.getText():null));
			            int currentDepth = tablesSymboles.getCurrentDepth();
			            int diffDepth = currentDepth - vi.depth;
			            
			            if (diffDepth <= 1) {
			                if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
			                    if (vi.type.equals("int") || vi.type.equals("bool")){
			                        ((GetContext)_localctx).code =  "READ\n" + "STOREL " + vi.address + "\n";
			                    } else if (vi.type.equals("float")){
			                        ((GetContext)_localctx).code =  "READF\n" + "STOREL " + (vi.address+1) + "\n" + "STOREL " + vi.address + "\n";
			                    }
			                } else {
			                    if (vi.type.equals("int") || vi.type.equals("bool")){
			                        ((GetContext)_localctx).code =  "READ\n" + "STOREG " + vi.address + "\n";
			                    } else if (vi.type.equals("float")){
			                        ((GetContext)_localctx).code =  "READF\n" + "STOREG " + (vi.address+1) + "\n" + "STOREG " + vi.address + "\n";
			                    }
			                }
			            } else {
			                String tmp = "PUSHL 0\n";
			                for (int i = 1; i < diffDepth; i++) {
			                    tmp += "PUSHR 0\n";
			                }
			                if (vi.type.equals("int") || vi.type.equals("bool")){
			                    ((GetContext)_localctx).code =  "READ\n" + tmp + "STORER " + vi.address + "\n";
			                } else if (vi.type.equals("float")){
			                    ((GetContext)_localctx).code =  "READF\n" + tmp + "STORER " + (vi.address+1) + "\n" + tmp + "STORER " + vi.address + "\n";
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

	@SuppressWarnings("CheckReturnValue")
	public static class PutContext extends ParserRuleContext {
		public String code;
		public ExpressionContext expression;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_put; }
	}

	public final PutContext put() throws RecognitionException {
		PutContext _localctx = new PutContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_put);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(T__15);
			setState(288);
			((PutContext)_localctx).expression = expression(0);
			setState(289);
			match(T__3);
			   
			            if(((PutContext)_localctx).expression.type.equals("int") || ((PutContext)_localctx).expression.type.equals("bool")){
			                ((PutContext)_localctx).code =  ((PutContext)_localctx).expression.code + "WRITE\n" + "POP\n";
			            } else if (((PutContext)_localctx).expression.type.equals("float")){
			                ((PutContext)_localctx).code =  ((PutContext)_localctx).expression.code + "WRITEF\n" + "POP\nPOP\n";
			            } else if ( ((PutContext)_localctx).expression.type.endsWith("[]") ){
			                String push = ((PutContext)_localctx).expression.code;
			                int elemSize = ((PutContext)_localctx).expression.type.equals("float") ? 2 : 1;

			                String start = newLabel();
			                String end = newLabel();

			                ((PutContext)_localctx).code =  push + "PUSHI 1\n" + "ADD\n" + "LABEL " + start + "\n";

			                if (elemSize == 1) {
			                    _localctx.code += "DUP\n" + "PUSHR 0\n" + "DUP\n" + "PUSHI -1\n" + "EQUAL\n" + "PUSHI 0\n" + "EQUAL\n" + "JUMPF " + end + "\n" + "WRITE\n" + "POP\n" + "PUSHI " + elemSize + "\n" + "ADD\n" + "JUMP " + start + "\n"+ "LABEL " + end + "\n"+ "POP\n";             
			                } else {
			                    _localctx.code += "DUP\n" + "PUSHR 0\n" + "DUP\n" + "PUSHI -1\n" + "EQUAL\n" + "PUSHI 0\n" + "EQUAL\n" + "JUMPF " + end + "\n" + push + "PUSHR 1\n" + "WRITEF\n" + "POP\nPOP\n" + "PUSHI " + elemSize + "\n"+ "ADD\n" + "JUMP " + start + "\n"+ "LABEL " + end + "\n"+ "POP\n";              
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

	@SuppressWarnings("CheckReturnValue")
	public static class BlocContext extends ParserRuleContext {
		public String code;
		public InstructionContext instruction;
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public BlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloc; }
	}

	public final BlocContext bloc() throws RecognitionException {
		BlocContext _localctx = new BlocContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_bloc);
		 ((BlocContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(T__16);
			setState(294);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(293);
				match(NEWLINE);
				}
				break;
			}
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1355328749582L) != 0)) {
				{
				{
				setState(296);
				((BlocContext)_localctx).instruction = instruction();
				_localctx.code += ((BlocContext)_localctx).instruction.code;
				}
				}
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(304);
			match(T__17);
			setState(308);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(305);
					match(NEWLINE);
					}
					} 
				}
				setState(310);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
	public static class ConditionContext extends ParserRuleContext {
		public String code;
		public ConditionContext c;
		public ConditionContext e;
		public ExpressionContext a;
		public Token op;
		public ExpressionContext b;
		public ConditionContext g;
		public ConditionContext d;
		public ConditionContext f;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OPERATEUR() { return getToken(CalculetteParser.OPERATEUR, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_condition, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(312);
				((ConditionContext)_localctx).a = expression(0);

				            if (((ConditionContext)_localctx).a.type.equals("bool")) {
				                ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).a.code;
				            } else if (((ConditionContext)_localctx).a.type.equals("int")) {
				                ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).a.code + "PUSHI 0\nEQUAL\nPUSHI 0\nEQUAL\n";
				            } else if (((ConditionContext)_localctx).a.type.equals("float")) {
				                ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).a.code + "FTOI\nPUSHI 0\nEQUAL\nPUSHI 0\nEQUAL\n";
				            } else {
				                ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).a.code;
				            }
				        
				}
				break;
			case 2:
				{
				setState(315);
				((ConditionContext)_localctx).a = expression(0);
				setState(316);
				((ConditionContext)_localctx).op = match(OPERATEUR);
				setState(317);
				((ConditionContext)_localctx).b = expression(0);

				            String[] prom = promotion(((ConditionContext)_localctx).a.code,((ConditionContext)_localctx).a.type,((ConditionContext)_localctx).b.code,((ConditionContext)_localctx).b.type) ; 
				            ((ConditionContext)_localctx).code =  prom[0] + evaloper((((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null), prom[1]);
				        
				}
				break;
			case 3:
				{
				setState(320);
				match(T__20);
				setState(321);
				((ConditionContext)_localctx).g = condition(1);
				((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).g.code + "PUSHI 0\n" + "EQUAL\n";
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(338);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(336);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(326);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(327);
						match(T__18);
						setState(328);
						((ConditionContext)_localctx).d = condition(4);
						((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + ((ConditionContext)_localctx).d.code + "MUL\n" + "PUSHI 1\n" + "EQUAL\n";
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(331);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(332);
						match(T__19);
						setState(333);
						((ConditionContext)_localctx).f = condition(3);
						((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).e.code + ((ConditionContext)_localctx).f.code + "ADD\n" + "PUSHI 1\n" + "SUPEQ\n";
						}
						break;
					}
					} 
				}
				setState(340);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoucleContext extends ParserRuleContext {
		public String code;
		public ConditionContext condition;
		public InstructionContext instruction;
		public AssignationContext a;
		public ConditionContext b;
		public AssignationContext c;
		public InstructionContext d;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public List<AssignationContext> assignation() {
			return getRuleContexts(AssignationContext.class);
		}
		public AssignationContext assignation(int i) {
			return getRuleContext(AssignationContext.class,i);
		}
		public BoucleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boucle; }
	}

	public final BoucleContext boucle() throws RecognitionException {
		BoucleContext _localctx = new BoucleContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_boucle);
		try {
			setState(359);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(341);
				match(T__21);
				setState(342);
				match(T__2);
				setState(343);
				((BoucleContext)_localctx).condition = condition(0);
				setState(344);
				match(T__3);
				setState(345);
				((BoucleContext)_localctx).instruction = instruction();

				            String start = newLabel();
				            String end = newLabel();

				            breakStack.push(end);
				            continueStack.push(start);

				            ((BoucleContext)_localctx).code =  "LABEL " + start + "\n" + ((BoucleContext)_localctx).condition.code + "JUMPF " + end + "\n" + ((BoucleContext)_localctx).instruction.code + "JUMP " + start + "\n" + "LABEL " + end + "\n";

				            breakStack.pop();
				            continueStack.pop();
				        
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
				match(T__22);
				setState(349);
				match(T__2);
				setState(350);
				((BoucleContext)_localctx).a = assignation();
				setState(351);
				match(T__23);
				setState(352);
				((BoucleContext)_localctx).b = condition(0);
				setState(353);
				match(T__23);
				setState(354);
				((BoucleContext)_localctx).c = assignation();
				setState(355);
				match(T__3);
				setState(356);
				((BoucleContext)_localctx).d = instruction();

				            String start = newLabel();
				            String end = newLabel();
				            String continueLabel = newLabel();

				            breakStack.push(end);
				            continueStack.push(continueLabel);

				            ((BoucleContext)_localctx).code =  ((BoucleContext)_localctx).a.code + "LABEL " + start + "\n" + ((BoucleContext)_localctx).b.code + "JUMPF " + end + "\n" + ((BoucleContext)_localctx).d.code + "LABEL " + continueLabel + "\n" + ((BoucleContext)_localctx).c.code + "JUMP " + start + "\n" + "LABEL " + end + "\n";

				            breakStack.pop();
				            continueStack.pop();
				        
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
	public static class BreakBoucleContext extends ParserRuleContext {
		public String code;
		public BreakBoucleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakBoucle; }
	}

	public final BreakBoucleContext breakBoucle() throws RecognitionException {
		BreakBoucleContext _localctx = new BreakBoucleContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_breakBoucle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(T__24);

			            if (breakStack.isEmpty()) {
			                throw new IllegalStateException("Break statement not within a loop");
			            }
			            ((BreakBoucleContext)_localctx).code =  "JUMP " + breakStack.peek() + "\n";
			        
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
	public static class ContinueBoucleContext extends ParserRuleContext {
		public String code;
		public ContinueBoucleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueBoucle; }
	}

	public final ContinueBoucleContext continueBoucle() throws RecognitionException {
		ContinueBoucleContext _localctx = new ContinueBoucleContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_continueBoucle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(T__25);

			            if (continueStack.isEmpty()) {
			                throw new IllegalStateException("Continue statement not within a loop");
			            }
			            ((ContinueBoucleContext)_localctx).code =  "JUMP " + continueStack.peek() + "\n";
			        
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
	public static class BranchementContext extends ParserRuleContext {
		public String code;
		public ConditionContext c;
		public InstructionContext a;
		public InstructionContext b;
		public ConditionContext condition;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public BranchementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branchement; }
	}

	public final BranchementContext branchement() throws RecognitionException {
		BranchementContext _localctx = new BranchementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_branchement);
		try {
			setState(385);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(367);
				match(T__26);
				setState(368);
				match(T__2);
				setState(369);
				((BranchementContext)_localctx).c = condition(0);
				setState(370);
				match(T__3);
				setState(371);
				match(T__27);
				setState(372);
				((BranchementContext)_localctx).a = instruction();
				setState(373);
				match(T__28);
				setState(374);
				((BranchementContext)_localctx).b = instruction();

				            String elseLabel = newLabel();
				            String end = newLabel();
				            ((BranchementContext)_localctx).code =  ((BranchementContext)_localctx).c.code + "JUMPF " + elseLabel + "\n" + ((BranchementContext)_localctx).a.code + "JUMP " + end + "\n" + "LABEL " + elseLabel + "\n" + ((BranchementContext)_localctx).b.code + "LABEL " + end + "\n";
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(T__26);
				setState(378);
				match(T__2);
				setState(379);
				((BranchementContext)_localctx).condition = condition(0);
				setState(380);
				match(T__3);
				setState(381);
				match(T__27);
				setState(382);
				((BranchementContext)_localctx).a = instruction();

				            String end = newLabel();
				            ((BranchementContext)_localctx).code =  ((BranchementContext)_localctx).condition.code + "JUMPF " + end + "\n" + ((BranchementContext)_localctx).a.code + "LABEL " + end + "\n" ;
				        
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParamsContext extends ParserRuleContext {
		public Token IDENTIFIANT;
		public Token TYPEV;
		public List<TerminalNode> IDENTIFIANT() { return getTokens(CalculetteParser.IDENTIFIANT); }
		public TerminalNode IDENTIFIANT(int i) {
			return getToken(CalculetteParser.IDENTIFIANT, i);
		}
		public List<TerminalNode> TYPEV() { return getTokens(CalculetteParser.TYPEV); }
		public TerminalNode TYPEV(int i) {
			return getToken(CalculetteParser.TYPEV, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(388);
			match(T__9);
			setState(389);
			((ParamsContext)_localctx).TYPEV = match(TYPEV);

			            tablesSymboles.addParam((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null),(((ParamsContext)_localctx).TYPEV!=null?((ParamsContext)_localctx).TYPEV.getText():null));
			        
			setState(398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(391);
				match(T__11);
				setState(392);
				((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(393);
				match(T__9);
				setState(394);
				((ParamsContext)_localctx).TYPEV = match(TYPEV);

				                tablesSymboles.addParam((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null),(((ParamsContext)_localctx).TYPEV!=null?((ParamsContext)_localctx).TYPEV.getText():null));
				            
				}
				}
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class FonctionContext extends ParserRuleContext {
		public String code;
		public Token TYPEV;
		public Token IDENTIFIANT;
		public DeclContext decl;
		public InstructionContext instruction;
		public TerminalNode TYPEV() { return getToken(CalculetteParser.TYPEV, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_fonction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			((FonctionContext)_localctx).TYPEV = match(TYPEV);
			setState(402);
			match(T__29);
			setState(403);
			((FonctionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

			            String skip = newLabel();
			            tablesSymboles.addFunction((((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null), (((FonctionContext)_localctx).TYPEV!=null?((FonctionContext)_localctx).TYPEV.getText():null));
			            ((FonctionContext)_localctx).code =  "JUMP " + skip + "\n" + "LABEL " + (((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null) + "\n";

			            enterTableStack(); // il faut entrer apres avoir defini la fonction sinon il ne la trouve pas
			        
			setState(405);
			match(T__2);
			setState(407);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIANT) {
				{
				setState(406);
				params();
				}
			}

			setState(409);
			match(T__3);
			setState(410);
			match(T__16);
			setState(412);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(411);
				match(NEWLINE);
				}
				break;
			}
			setState(419);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(414);
					((FonctionContext)_localctx).decl = decl();
					 _localctx.code += ((FonctionContext)_localctx).decl.code; 
					}
					} 
				}
				setState(421);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(425);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(422);
					match(NEWLINE);
					}
					} 
				}
				setState(427);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1355328749582L) != 0)) {
				{
				{
				setState(428);
				((FonctionContext)_localctx).instruction = instruction();
				 _localctx.code += ((FonctionContext)_localctx).instruction.code; 
				}
				}
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(436);
			match(T__17);
			setState(440);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(437);
					match(NEWLINE);
					}
					} 
				}
				setState(442);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			 _localctx.code += "RETURN\n" + "LABEL " + skip + "\n"; 
			}
			_ctx.stop = _input.LT(-1);
			 exitTableStack(); 
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
	public static class ArgsContext extends ParserRuleContext {
		public String code;
		public int size;
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_args);
		 ((ArgsContext)_localctx).code =  new String(); ((ArgsContext)_localctx).size =  0; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1340029796366L) != 0)) {
				{
				setState(445);
				((ArgsContext)_localctx).expression = expression(0);

				        _localctx.code += ((ArgsContext)_localctx).expression.code;
				        ((ArgsContext)_localctx).size =  ((ArgsContext)_localctx).expression.type.equals("float") ? 2 : 1;
				    
				setState(453);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(447);
					match(T__11);
					setState(448);
					((ArgsContext)_localctx).expression = expression(0);

					        _localctx.code += ((ArgsContext)_localctx).expression.code;
					        _localctx.size += ((ArgsContext)_localctx).expression.type.equals("float") ? 2 : 1;
					    
					}
					}
					setState(455);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FinInstructionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public FinInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finInstruction; }
	}

	public final FinInstructionContext finInstruction() throws RecognitionException {
		FinInstructionContext _localctx = new FinInstructionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_finInstruction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(459); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(458);
					_la = _input.LA(1);
					if ( !(_la==T__23 || _la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(461); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 9:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u01d0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001+\b\u0001"+
		"\n\u0001\f\u0001.\t\u0001\u0001\u0001\u0001\u0001\u0005\u00012\b\u0001"+
		"\n\u0001\f\u00015\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		":\b\u0001\n\u0001\f\u0001=\t\u0001\u0001\u0001\u0005\u0001@\b\u0001\n"+
		"\u0001\f\u0001C\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001I\b\u0001\n\u0001\f\u0001L\t\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002\u0088\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"\u00b5\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"\u00c1\b\u0003\n\u0003\f\u0003\u00c4\t\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u0004\u00e7\b\u0004\n\u0004\f\u0004\u00ea\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004\u00f5\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u0109\b\u0005"+
		"\n\u0005\f\u0005\u010c\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u0119\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0003\b\u0127\b\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\b\u012c\b\b\n\b\f\b\u012f\t\b\u0001\b\u0001\b\u0005\b\u0133\b"+
		"\b\n\b\f\b\u0136\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0145\b\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0005\t\u0151\b\t\n\t\f\t\u0154\t\t\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0168\b\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0182\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u018d\b\u000e\n\u000e\f\u000e"+
		"\u0190\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u0198\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u019d\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u01a2\b\u000f\n\u000f\f\u000f\u01a5\t\u000f\u0001\u000f\u0005\u000f\u01a8"+
		"\b\u000f\n\u000f\f\u000f\u01ab\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0005\u000f\u01b0\b\u000f\n\u000f\f\u000f\u01b3\t\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\u01b7\b\u000f\n\u000f\f\u000f\u01ba\t\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u01c4\b\u0010\n\u0010\f\u0010\u01c7\t\u0010\u0003"+
		"\u0010\u01c9\b\u0010\u0001\u0011\u0004\u0011\u01cc\b\u0011\u000b\u0011"+
		"\f\u0011\u01cd\u0001\u0011\u0000\u0002\u0006\u0012\u0012\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"\u0000\u0004\u0001\u0000\u0005\u0007\u0001\u0000\u0001\u0002\u0001\u0000"+
		"\r\u000e\u0002\u0000\u0018\u0018!!\u01f7\u0000$\u0001\u0000\u0000\u0000"+
		"\u0002,\u0001\u0000\u0000\u0000\u0004\u0087\u0001\u0000\u0000\u0000\u0006"+
		"\u00b4\u0001\u0000\u0000\u0000\b\u00f4\u0001\u0000\u0000\u0000\n\u0118"+
		"\u0001\u0000\u0000\u0000\f\u011a\u0001\u0000\u0000\u0000\u000e\u011f\u0001"+
		"\u0000\u0000\u0000\u0010\u0124\u0001\u0000\u0000\u0000\u0012\u0144\u0001"+
		"\u0000\u0000\u0000\u0014\u0167\u0001\u0000\u0000\u0000\u0016\u0169\u0001"+
		"\u0000\u0000\u0000\u0018\u016c\u0001\u0000\u0000\u0000\u001a\u0181\u0001"+
		"\u0000\u0000\u0000\u001c\u0183\u0001\u0000\u0000\u0000\u001e\u0191\u0001"+
		"\u0000\u0000\u0000 \u01c8\u0001\u0000\u0000\u0000\"\u01cb\u0001\u0000"+
		"\u0000\u0000$%\u0003\u0002\u0001\u0000%&\u0005\u0000\u0000\u0001&\u0001"+
		"\u0001\u0000\u0000\u0000\'(\u0003\b\u0004\u0000()\u0006\u0001\uffff\uffff"+
		"\u0000)+\u0001\u0000\u0000\u0000*\'\u0001\u0000\u0000\u0000+.\u0001\u0000"+
		"\u0000\u0000,*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-/\u0001"+
		"\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000/3\u0006\u0001\uffff\uffff"+
		"\u000002\u0005!\u0000\u000010\u0001\u0000\u0000\u000025\u0001\u0000\u0000"+
		"\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u00004;\u0001\u0000"+
		"\u0000\u000053\u0001\u0000\u0000\u000067\u0003\u001e\u000f\u000078\u0006"+
		"\u0001\uffff\uffff\u00008:\u0001\u0000\u0000\u000096\u0001\u0000\u0000"+
		"\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000"+
		"\u0000\u0000<A\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>@\u0005"+
		"!\u0000\u0000?>\u0001\u0000\u0000\u0000@C\u0001\u0000\u0000\u0000A?\u0001"+
		"\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BD\u0001\u0000\u0000\u0000"+
		"CA\u0001\u0000\u0000\u0000DJ\u0006\u0001\uffff\uffff\u0000EF\u0003\u0004"+
		"\u0002\u0000FG\u0006\u0001\uffff\uffff\u0000GI\u0001\u0000\u0000\u0000"+
		"HE\u0001\u0000\u0000\u0000IL\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000"+
		"\u0000JK\u0001\u0000\u0000\u0000KM\u0001\u0000\u0000\u0000LJ\u0001\u0000"+
		"\u0000\u0000MN\u0006\u0001\uffff\uffff\u0000N\u0003\u0001\u0000\u0000"+
		"\u0000OP\u0003\u0006\u0003\u0000PQ\u0003\"\u0011\u0000QR\u0006\u0002\uffff"+
		"\uffff\u0000R\u0088\u0001\u0000\u0000\u0000ST\u0003\n\u0005\u0000TU\u0003"+
		"\"\u0011\u0000UV\u0006\u0002\uffff\uffff\u0000V\u0088\u0001\u0000\u0000"+
		"\u0000WX\u0003\f\u0006\u0000XY\u0003\"\u0011\u0000YZ\u0006\u0002\uffff"+
		"\uffff\u0000Z\u0088\u0001\u0000\u0000\u0000[\\\u0003\u000e\u0007\u0000"+
		"\\]\u0003\"\u0011\u0000]^\u0006\u0002\uffff\uffff\u0000^\u0088\u0001\u0000"+
		"\u0000\u0000_`\u0003\u0012\t\u0000`a\u0003\"\u0011\u0000ab\u0006\u0002"+
		"\uffff\uffff\u0000b\u0088\u0001\u0000\u0000\u0000cd\u0003\b\u0004\u0000"+
		"de\u0006\u0002\uffff\uffff\u0000e\u0088\u0001\u0000\u0000\u0000fg\u0003"+
		"\u0010\b\u0000gh\u0003\"\u0011\u0000hi\u0006\u0002\uffff\uffff\u0000i"+
		"\u0088\u0001\u0000\u0000\u0000jk\u0003\u0016\u000b\u0000kl\u0003\"\u0011"+
		"\u0000lm\u0006\u0002\uffff\uffff\u0000m\u0088\u0001\u0000\u0000\u0000"+
		"no\u0003\u0018\f\u0000op\u0003\"\u0011\u0000pq\u0006\u0002\uffff\uffff"+
		"\u0000q\u0088\u0001\u0000\u0000\u0000rs\u0003\u0014\n\u0000st\u0006\u0002"+
		"\uffff\uffff\u0000t\u0088\u0001\u0000\u0000\u0000uv\u0003\u001a\r\u0000"+
		"vw\u0006\u0002\uffff\uffff\u0000w\u0088\u0001\u0000\u0000\u0000xy\u0003"+
		"\u001a\r\u0000yz\u0003\"\u0011\u0000z{\u0006\u0002\uffff\uffff\u0000{"+
		"\u0088\u0001\u0000\u0000\u0000|}\u0003\u001e\u000f\u0000}~\u0006\u0002"+
		"\uffff\uffff\u0000~\u0088\u0001\u0000\u0000\u0000\u007f\u0080\u0003\""+
		"\u0011\u0000\u0080\u0081\u0006\u0002\uffff\uffff\u0000\u0081\u0088\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0005 \u0000\u0000\u0083\u0084\u0003\u0006"+
		"\u0003\u0000\u0084\u0085\u0003\"\u0011\u0000\u0085\u0086\u0006\u0002\uffff"+
		"\uffff\u0000\u0086\u0088\u0001\u0000\u0000\u0000\u0087O\u0001\u0000\u0000"+
		"\u0000\u0087S\u0001\u0000\u0000\u0000\u0087W\u0001\u0000\u0000\u0000\u0087"+
		"[\u0001\u0000\u0000\u0000\u0087_\u0001\u0000\u0000\u0000\u0087c\u0001"+
		"\u0000\u0000\u0000\u0087f\u0001\u0000\u0000\u0000\u0087j\u0001\u0000\u0000"+
		"\u0000\u0087n\u0001\u0000\u0000\u0000\u0087r\u0001\u0000\u0000\u0000\u0087"+
		"u\u0001\u0000\u0000\u0000\u0087x\u0001\u0000\u0000\u0000\u0087|\u0001"+
		"\u0000\u0000\u0000\u0087\u007f\u0001\u0000\u0000\u0000\u0087\u0082\u0001"+
		"\u0000\u0000\u0000\u0088\u0005\u0001\u0000\u0000\u0000\u0089\u008a\u0006"+
		"\u0003\uffff\uffff\u0000\u008a\u008b\u0005\u0001\u0000\u0000\u008b\u008c"+
		"\u0003\u0006\u0003\r\u008c\u008d\u0006\u0003\uffff\uffff\u0000\u008d\u00b5"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0005\u0002\u0000\u0000\u008f\u0090"+
		"\u0003\u0006\u0003\f\u0090\u0091\u0006\u0003\uffff\uffff\u0000\u0091\u00b5"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u0003\u0000\u0000\u0093\u0094"+
		"\u0005\u001f\u0000\u0000\u0094\u0095\u0005\u0004\u0000\u0000\u0095\u0096"+
		"\u0003\u0006\u0003\u000b\u0096\u0097\u0006\u0003\uffff\uffff\u0000\u0097"+
		"\u00b5\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0003\u0000\u0000\u0099"+
		"\u009a\u0003\u0006\u0003\u0000\u009a\u009b\u0005\u0004\u0000\u0000\u009b"+
		"\u009c\u0006\u0003\uffff\uffff\u0000\u009c\u00b5\u0001\u0000\u0000\u0000"+
		"\u009d\u009e\u0005$\u0000\u0000\u009e\u00b5\u0006\u0003\uffff\uffff\u0000"+
		"\u009f\u00a0\u0005#\u0000\u0000\u00a0\u00b5\u0006\u0003\uffff\uffff\u0000"+
		"\u00a1\u00a2\u0005%\u0000\u0000\u00a2\u00b5\u0006\u0003\uffff\uffff\u0000"+
		"\u00a3\u00a4\u0005(\u0000\u0000\u00a4\u00b5\u0006\u0003\uffff\uffff\u0000"+
		"\u00a5\u00a6\u0005(\u0000\u0000\u00a6\u00a7\u0005\u0003\u0000\u0000\u00a7"+
		"\u00a8\u0005\u0004\u0000\u0000\u00a8\u00b5\u0006\u0003\uffff\uffff\u0000"+
		"\u00a9\u00aa\u0005(\u0000\u0000\u00aa\u00ab\u0005\u0003\u0000\u0000\u00ab"+
		"\u00ac\u0003 \u0010\u0000\u00ac\u00ad\u0005\u0004\u0000\u0000\u00ad\u00ae"+
		"\u0006\u0003\uffff\uffff\u0000\u00ae\u00b5\u0001\u0000\u0000\u0000\u00af"+
		"\u00b0\u0005(\u0000\u0000\u00b0\u00b1\u0005\b\u0000\u0000\u00b1\u00b2"+
		"\u0005$\u0000\u0000\u00b2\u00b3\u0005\t\u0000\u0000\u00b3\u00b5\u0006"+
		"\u0003\uffff\uffff\u0000\u00b4\u0089\u0001\u0000\u0000\u0000\u00b4\u008e"+
		"\u0001\u0000\u0000\u0000\u00b4\u0092\u0001\u0000\u0000\u0000\u00b4\u0098"+
		"\u0001\u0000\u0000\u0000\u00b4\u009d\u0001\u0000\u0000\u0000\u00b4\u009f"+
		"\u0001\u0000\u0000\u0000\u00b4\u00a1\u0001\u0000\u0000\u0000\u00b4\u00a3"+
		"\u0001\u0000\u0000\u0000\u00b4\u00a5\u0001\u0000\u0000\u0000\u00b4\u00a9"+
		"\u0001\u0000\u0000\u0000\u00b4\u00af\u0001\u0000\u0000\u0000\u00b5\u00c2"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b7\n\t\u0000\u0000\u00b7\u00b8\u0007"+
		"\u0000\u0000\u0000\u00b8\u00b9\u0003\u0006\u0003\n\u00b9\u00ba\u0006\u0003"+
		"\uffff\uffff\u0000\u00ba\u00c1\u0001\u0000\u0000\u0000\u00bb\u00bc\n\b"+
		"\u0000\u0000\u00bc\u00bd\u0007\u0001\u0000\u0000\u00bd\u00be\u0003\u0006"+
		"\u0003\t\u00be\u00bf\u0006\u0003\uffff\uffff\u0000\u00bf\u00c1\u0001\u0000"+
		"\u0000\u0000\u00c0\u00b6\u0001\u0000\u0000\u0000\u00c0\u00bb\u0001\u0000"+
		"\u0000\u0000\u00c1\u00c4\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u0007\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005(\u0000"+
		"\u0000\u00c6\u00c7\u0005\n\u0000\u0000\u00c7\u00c8\u0005\u001f\u0000\u0000"+
		"\u00c8\u00c9\u0003\"\u0011\u0000\u00c9\u00ca\u0006\u0004\uffff\uffff\u0000"+
		"\u00ca\u00f5\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005(\u0000\u0000\u00cc"+
		"\u00cd\u0005\n\u0000\u0000\u00cd\u00ce\u0005\u001f\u0000\u0000\u00ce\u00cf"+
		"\u0005\b\u0000\u0000\u00cf\u00d0\u0005$\u0000\u0000\u00d0\u00d1\u0005"+
		"\t\u0000\u0000\u00d1\u00d2\u0003\"\u0011\u0000\u00d2\u00d3\u0006\u0004"+
		"\uffff\uffff\u0000\u00d3\u00f5\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005"+
		"(\u0000\u0000\u00d5\u00d6\u0005\u000b\u0000\u0000\u00d6\u00d7\u0003\u0006"+
		"\u0003\u0000\u00d7\u00d8\u0005\n\u0000\u0000\u00d8\u00d9\u0005\u001f\u0000"+
		"\u0000\u00d9\u00da\u0003\"\u0011\u0000\u00da\u00db\u0006\u0004\uffff\uffff"+
		"\u0000\u00db\u00f5\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005(\u0000\u0000"+
		"\u00dd\u00de\u0005\u000b\u0000\u0000\u00de\u00df\u0005\b\u0000\u0000\u00df"+
		"\u00e0\u0006\u0004\uffff\uffff\u0000\u00e0\u00e1\u0003\u0006\u0003\u0000"+
		"\u00e1\u00e8\u0006\u0004\uffff\uffff\u0000\u00e2\u00e3\u0005\f\u0000\u0000"+
		"\u00e3\u00e4\u0003\u0006\u0003\u0000\u00e4\u00e5\u0006\u0004\uffff\uffff"+
		"\u0000\u00e5\u00e7\u0001\u0000\u0000\u0000\u00e6\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00eb\u0001\u0000\u0000"+
		"\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00eb\u00ec\u0005\t\u0000\u0000"+
		"\u00ec\u00ed\u0005\n\u0000\u0000\u00ed\u00ee\u0005\u001f\u0000\u0000\u00ee"+
		"\u00ef\u0005\b\u0000\u0000\u00ef\u00f0\u0005$\u0000\u0000\u00f0\u00f1"+
		"\u0005\t\u0000\u0000\u00f1\u00f2\u0003\"\u0011\u0000\u00f2\u00f3\u0006"+
		"\u0004\uffff\uffff\u0000\u00f3\u00f5\u0001\u0000\u0000\u0000\u00f4\u00c5"+
		"\u0001\u0000\u0000\u0000\u00f4\u00cb\u0001\u0000\u0000\u0000\u00f4\u00d4"+
		"\u0001\u0000\u0000\u0000\u00f4\u00dc\u0001\u0000\u0000\u0000\u00f5\t\u0001"+
		"\u0000\u0000\u0000\u00f6\u00f7\u0005(\u0000\u0000\u00f7\u00f8\u0005\u000b"+
		"\u0000\u0000\u00f8\u00f9\u0003\u0006\u0003\u0000\u00f9\u00fa\u0006\u0005"+
		"\uffff\uffff\u0000\u00fa\u0119\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005"+
		"(\u0000\u0000\u00fc\u00fd\u0007\u0002\u0000\u0000\u00fd\u0119\u0006\u0005"+
		"\uffff\uffff\u0000\u00fe\u00ff\u0005(\u0000\u0000\u00ff\u0100\u0005\u000b"+
		"\u0000\u0000\u0100\u0101\u0005\b\u0000\u0000\u0101\u0102\u0006\u0005\uffff"+
		"\uffff\u0000\u0102\u0103\u0003\u0006\u0003\u0000\u0103\u010a\u0006\u0005"+
		"\uffff\uffff\u0000\u0104\u0105\u0005\f\u0000\u0000\u0105\u0106\u0003\u0006"+
		"\u0003\u0000\u0106\u0107\u0006\u0005\uffff\uffff\u0000\u0107\u0109\u0001"+
		"\u0000\u0000\u0000\u0108\u0104\u0001\u0000\u0000\u0000\u0109\u010c\u0001"+
		"\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010a\u010b\u0001"+
		"\u0000\u0000\u0000\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u010a\u0001"+
		"\u0000\u0000\u0000\u010d\u010e\u0005\t\u0000\u0000\u010e\u010f\u0006\u0005"+
		"\uffff\uffff\u0000\u010f\u0119\u0001\u0000\u0000\u0000\u0110\u0111\u0005"+
		"(\u0000\u0000\u0111\u0112\u0005\b\u0000\u0000\u0112\u0113\u0005$\u0000"+
		"\u0000\u0113\u0114\u0005\t\u0000\u0000\u0114\u0115\u0005\u000b\u0000\u0000"+
		"\u0115\u0116\u0003\u0006\u0003\u0000\u0116\u0117\u0006\u0005\uffff\uffff"+
		"\u0000\u0117\u0119\u0001\u0000\u0000\u0000\u0118\u00f6\u0001\u0000\u0000"+
		"\u0000\u0118\u00fb\u0001\u0000\u0000\u0000\u0118\u00fe\u0001\u0000\u0000"+
		"\u0000\u0118\u0110\u0001\u0000\u0000\u0000\u0119\u000b\u0001\u0000\u0000"+
		"\u0000\u011a\u011b\u0005\u000f\u0000\u0000\u011b\u011c\u0005(\u0000\u0000"+
		"\u011c\u011d\u0005\u0004\u0000\u0000\u011d\u011e\u0006\u0006\uffff\uffff"+
		"\u0000\u011e\r\u0001\u0000\u0000\u0000\u011f\u0120\u0005\u0010\u0000\u0000"+
		"\u0120\u0121\u0003\u0006\u0003\u0000\u0121\u0122\u0005\u0004\u0000\u0000"+
		"\u0122\u0123\u0006\u0007\uffff\uffff\u0000\u0123\u000f\u0001\u0000\u0000"+
		"\u0000\u0124\u0126\u0005\u0011\u0000\u0000\u0125\u0127\u0005!\u0000\u0000"+
		"\u0126\u0125\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000"+
		"\u0127\u012d\u0001\u0000\u0000\u0000\u0128\u0129\u0003\u0004\u0002\u0000"+
		"\u0129\u012a\u0006\b\uffff\uffff\u0000\u012a\u012c\u0001\u0000\u0000\u0000"+
		"\u012b\u0128\u0001\u0000\u0000\u0000\u012c\u012f\u0001\u0000\u0000\u0000"+
		"\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000"+
		"\u012e\u0130\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000"+
		"\u0130\u0134\u0005\u0012\u0000\u0000\u0131\u0133\u0005!\u0000\u0000\u0132"+
		"\u0131\u0001\u0000\u0000\u0000\u0133\u0136\u0001\u0000\u0000\u0000\u0134"+
		"\u0132\u0001\u0000\u0000\u0000\u0134\u0135\u0001\u0000\u0000\u0000\u0135"+
		"\u0011\u0001\u0000\u0000\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0137"+
		"\u0138\u0006\t\uffff\uffff\u0000\u0138\u0139\u0003\u0006\u0003\u0000\u0139"+
		"\u013a\u0006\t\uffff\uffff\u0000\u013a\u0145\u0001\u0000\u0000\u0000\u013b"+
		"\u013c\u0003\u0006\u0003\u0000\u013c\u013d\u0005&\u0000\u0000\u013d\u013e"+
		"\u0003\u0006\u0003\u0000\u013e\u013f\u0006\t\uffff\uffff\u0000\u013f\u0145"+
		"\u0001\u0000\u0000\u0000\u0140\u0141\u0005\u0015\u0000\u0000\u0141\u0142"+
		"\u0003\u0012\t\u0001\u0142\u0143\u0006\t\uffff\uffff\u0000\u0143\u0145"+
		"\u0001\u0000\u0000\u0000\u0144\u0137\u0001\u0000\u0000\u0000\u0144\u013b"+
		"\u0001\u0000\u0000\u0000\u0144\u0140\u0001\u0000\u0000\u0000\u0145\u0152"+
		"\u0001\u0000\u0000\u0000\u0146\u0147\n\u0003\u0000\u0000\u0147\u0148\u0005"+
		"\u0013\u0000\u0000\u0148\u0149\u0003\u0012\t\u0004\u0149\u014a\u0006\t"+
		"\uffff\uffff\u0000\u014a\u0151\u0001\u0000\u0000\u0000\u014b\u014c\n\u0002"+
		"\u0000\u0000\u014c\u014d\u0005\u0014\u0000\u0000\u014d\u014e\u0003\u0012"+
		"\t\u0003\u014e\u014f\u0006\t\uffff\uffff\u0000\u014f\u0151\u0001\u0000"+
		"\u0000\u0000\u0150\u0146\u0001\u0000\u0000\u0000\u0150\u014b\u0001\u0000"+
		"\u0000\u0000\u0151\u0154\u0001\u0000\u0000\u0000\u0152\u0150\u0001\u0000"+
		"\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u0013\u0001\u0000"+
		"\u0000\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0155\u0156\u0005\u0016"+
		"\u0000\u0000\u0156\u0157\u0005\u0003\u0000\u0000\u0157\u0158\u0003\u0012"+
		"\t\u0000\u0158\u0159\u0005\u0004\u0000\u0000\u0159\u015a\u0003\u0004\u0002"+
		"\u0000\u015a\u015b\u0006\n\uffff\uffff\u0000\u015b\u0168\u0001\u0000\u0000"+
		"\u0000\u015c\u015d\u0005\u0017\u0000\u0000\u015d\u015e\u0005\u0003\u0000"+
		"\u0000\u015e\u015f\u0003\n\u0005\u0000\u015f\u0160\u0005\u0018\u0000\u0000"+
		"\u0160\u0161\u0003\u0012\t\u0000\u0161\u0162\u0005\u0018\u0000\u0000\u0162"+
		"\u0163\u0003\n\u0005\u0000\u0163\u0164\u0005\u0004\u0000\u0000\u0164\u0165"+
		"\u0003\u0004\u0002\u0000\u0165\u0166\u0006\n\uffff\uffff\u0000\u0166\u0168"+
		"\u0001\u0000\u0000\u0000\u0167\u0155\u0001\u0000\u0000\u0000\u0167\u015c"+
		"\u0001\u0000\u0000\u0000\u0168\u0015\u0001\u0000\u0000\u0000\u0169\u016a"+
		"\u0005\u0019\u0000\u0000\u016a\u016b\u0006\u000b\uffff\uffff\u0000\u016b"+
		"\u0017\u0001\u0000\u0000\u0000\u016c\u016d\u0005\u001a\u0000\u0000\u016d"+
		"\u016e\u0006\f\uffff\uffff\u0000\u016e\u0019\u0001\u0000\u0000\u0000\u016f"+
		"\u0170\u0005\u001b\u0000\u0000\u0170\u0171\u0005\u0003\u0000\u0000\u0171"+
		"\u0172\u0003\u0012\t\u0000\u0172\u0173\u0005\u0004\u0000\u0000\u0173\u0174"+
		"\u0005\u001c\u0000\u0000\u0174\u0175\u0003\u0004\u0002\u0000\u0175\u0176"+
		"\u0005\u001d\u0000\u0000\u0176\u0177\u0003\u0004\u0002\u0000\u0177\u0178"+
		"\u0006\r\uffff\uffff\u0000\u0178\u0182\u0001\u0000\u0000\u0000\u0179\u017a"+
		"\u0005\u001b\u0000\u0000\u017a\u017b\u0005\u0003\u0000\u0000\u017b\u017c"+
		"\u0003\u0012\t\u0000\u017c\u017d\u0005\u0004\u0000\u0000\u017d\u017e\u0005"+
		"\u001c\u0000\u0000\u017e\u017f\u0003\u0004\u0002\u0000\u017f\u0180\u0006"+
		"\r\uffff\uffff\u0000\u0180\u0182\u0001\u0000\u0000\u0000\u0181\u016f\u0001"+
		"\u0000\u0000\u0000\u0181\u0179\u0001\u0000\u0000\u0000\u0182\u001b\u0001"+
		"\u0000\u0000\u0000\u0183\u0184\u0005(\u0000\u0000\u0184\u0185\u0005\n"+
		"\u0000\u0000\u0185\u0186\u0005\u001f\u0000\u0000\u0186\u018e\u0006\u000e"+
		"\uffff\uffff\u0000\u0187\u0188\u0005\f\u0000\u0000\u0188\u0189\u0005("+
		"\u0000\u0000\u0189\u018a\u0005\n\u0000\u0000\u018a\u018b\u0005\u001f\u0000"+
		"\u0000\u018b\u018d\u0006\u000e\uffff\uffff\u0000\u018c\u0187\u0001\u0000"+
		"\u0000\u0000\u018d\u0190\u0001\u0000\u0000\u0000\u018e\u018c\u0001\u0000"+
		"\u0000\u0000\u018e\u018f\u0001\u0000\u0000\u0000\u018f\u001d\u0001\u0000"+
		"\u0000\u0000\u0190\u018e\u0001\u0000\u0000\u0000\u0191\u0192\u0005\u001f"+
		"\u0000\u0000\u0192\u0193\u0005\u001e\u0000\u0000\u0193\u0194\u0005(\u0000"+
		"\u0000\u0194\u0195\u0006\u000f\uffff\uffff\u0000\u0195\u0197\u0005\u0003"+
		"\u0000\u0000\u0196\u0198\u0003\u001c\u000e\u0000\u0197\u0196\u0001\u0000"+
		"\u0000\u0000\u0197\u0198\u0001\u0000\u0000\u0000\u0198\u0199\u0001\u0000"+
		"\u0000\u0000\u0199\u019a\u0005\u0004\u0000\u0000\u019a\u019c\u0005\u0011"+
		"\u0000\u0000\u019b\u019d\u0005!\u0000\u0000\u019c\u019b\u0001\u0000\u0000"+
		"\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d\u01a3\u0001\u0000\u0000"+
		"\u0000\u019e\u019f\u0003\b\u0004\u0000\u019f\u01a0\u0006\u000f\uffff\uffff"+
		"\u0000\u01a0\u01a2\u0001\u0000\u0000\u0000\u01a1\u019e\u0001\u0000\u0000"+
		"\u0000\u01a2\u01a5\u0001\u0000\u0000\u0000\u01a3\u01a1\u0001\u0000\u0000"+
		"\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4\u01a9\u0001\u0000\u0000"+
		"\u0000\u01a5\u01a3\u0001\u0000\u0000\u0000\u01a6\u01a8\u0005!\u0000\u0000"+
		"\u01a7\u01a6\u0001\u0000\u0000\u0000\u01a8\u01ab\u0001\u0000\u0000\u0000"+
		"\u01a9\u01a7\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000\u0000"+
		"\u01aa\u01b1\u0001\u0000\u0000\u0000\u01ab\u01a9\u0001\u0000\u0000\u0000"+
		"\u01ac\u01ad\u0003\u0004\u0002\u0000\u01ad\u01ae\u0006\u000f\uffff\uffff"+
		"\u0000\u01ae\u01b0\u0001\u0000\u0000\u0000\u01af\u01ac\u0001\u0000\u0000"+
		"\u0000\u01b0\u01b3\u0001\u0000\u0000\u0000\u01b1\u01af\u0001\u0000\u0000"+
		"\u0000\u01b1\u01b2\u0001\u0000\u0000\u0000\u01b2\u01b4\u0001\u0000\u0000"+
		"\u0000\u01b3\u01b1\u0001\u0000\u0000\u0000\u01b4\u01b8\u0005\u0012\u0000"+
		"\u0000\u01b5\u01b7\u0005!\u0000\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000"+
		"\u01b7\u01ba\u0001\u0000\u0000\u0000\u01b8\u01b6\u0001\u0000\u0000\u0000"+
		"\u01b8\u01b9\u0001\u0000\u0000\u0000\u01b9\u01bb\u0001\u0000\u0000\u0000"+
		"\u01ba\u01b8\u0001\u0000\u0000\u0000\u01bb\u01bc\u0006\u000f\uffff\uffff"+
		"\u0000\u01bc\u001f\u0001\u0000\u0000\u0000\u01bd\u01be\u0003\u0006\u0003"+
		"\u0000\u01be\u01c5\u0006\u0010\uffff\uffff\u0000\u01bf\u01c0\u0005\f\u0000"+
		"\u0000\u01c0\u01c1\u0003\u0006\u0003\u0000\u01c1\u01c2\u0006\u0010\uffff"+
		"\uffff\u0000\u01c2\u01c4\u0001\u0000\u0000\u0000\u01c3\u01bf\u0001\u0000"+
		"\u0000\u0000\u01c4\u01c7\u0001\u0000\u0000\u0000\u01c5\u01c3\u0001\u0000"+
		"\u0000\u0000\u01c5\u01c6\u0001\u0000\u0000\u0000\u01c6\u01c9\u0001\u0000"+
		"\u0000\u0000\u01c7\u01c5\u0001\u0000\u0000\u0000\u01c8\u01bd\u0001\u0000"+
		"\u0000\u0000\u01c8\u01c9\u0001\u0000\u0000\u0000\u01c9!\u0001\u0000\u0000"+
		"\u0000\u01ca\u01cc\u0007\u0003\u0000\u0000\u01cb\u01ca\u0001\u0000\u0000"+
		"\u0000\u01cc\u01cd\u0001\u0000\u0000\u0000\u01cd\u01cb\u0001\u0000\u0000"+
		"\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce#\u0001\u0000\u0000\u0000"+
		"\u001f,3;AJ\u0087\u00b4\u00c0\u00c2\u00e8\u00f4\u010a\u0118\u0126\u012d"+
		"\u0134\u0144\u0150\u0152\u0167\u0181\u018e\u0197\u019c\u01a3\u01a9\u01b1"+
		"\u01b8\u01c5\u01c8\u01cd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}