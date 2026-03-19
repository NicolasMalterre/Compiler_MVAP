grammar Calculette;

@parser::members {

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

    
}




start : calcul EOF;

calcul returns [ String code ]
@init{ 
    $code = new String(); 
    int globalSize = 0;
}
@after{ System.out.println($code); }
    :   (decl { 
            $code += $decl.code; 
            globalSize += $decl.size;
        })*
        { $code += "  JUMP Start\n"; }
        NEWLINE*

        (fonction { $code += $fonction.code; })*
        NEWLINE*

        { $code += "LABEL Start\n"; }
        (instruction { $code += $instruction.code; })*

        { 
            
            for(int i = 0; i < globalSize; i++) {
                $code += "POP\n";
            }
            $code += "HALT\n"; 
        }
    ;


instruction returns [ String code ] 
    : expression finInstruction
        { 
            $code = $expression.code ;
        }
    | assignation finInstruction
        {
            $code = $assignation.code ;
        }
    | get finInstruction
        {
            $code = $get.code;
        }
    | put finInstruction
        {
            $code = $put.code;
        }
    | condition finInstruction
        {
            $code = $condition.code;
        }
    | decl 
        {
            $code = $decl.code;
        }
    | bloc finInstruction
        {
            $code = $bloc.code;
        }
    | breakBoucle finInstruction
        {
            $code = $breakBoucle.code;
        }
    | continueBoucle finInstruction
        {
            $code = $continueBoucle.code;
        }
    | boucle
        {
            $code = $boucle.code;
        }
    | branchement
        {
            $code = $branchement.code;
        }
    | branchement finInstruction
        {
            $code = $branchement.code;
        }
    |fonction
        {
            $code = $fonction.code;
        }
    | finInstruction
        {
            $code="";
        }
    | RETURN expression finInstruction    
        {
            VariableInfo vi = tablesSymboles.getReturn();

            if ($expression.type.equals("float")) {
                $code = $expression.code
                    + "STOREL " + (vi.address + 1) + "\n"
                    + "STOREL " + vi.address + "\n"
                    + "RETURN\n";
            } else if ($expression.type.equals("int") || $expression.type.equals("bool")){
                $code = $expression.code
                    + "STOREL " + vi.address + "\n"
                    + "RETURN\n";
            }
        }
    ;

expression returns [ String code, String type ]
    : '+' e=expression {$code = $e.code; $type = $e.type;}
    |'-' e=expression {
        $type = $e.type; 
        $code = push($e.type,"-1") + $e.code + evalexpr("*",$e.type);
        }
    |'(' TYPEV ')' e=expression {
        if ($TYPEV.text.equals("int")) {
            if ($e.type.equals("float")) {
                $code = $e.code + "FTOI\n";
            } else {
                $code = $e.code;
            }
            $type = "int";
        } else if ($TYPEV.text.equals("float")) {
            if ($e.type.equals("int") || $e.type.equals("bool")) {
                $code = $e.code + "ITOF\n";
            } else {
                $code = $e.code;
            }
            $type = "float";
        } else if ($TYPEV.text.equals("bool")) {
            if ($e.type.equals("int") || $e.type.equals("bool")) {
                $code = $e.code + "PUSHI 0\nEQUAL\nPUSHI 0\nEQUAL\n";
            } else if ($e.type.equals("float")) {
                $code = $e.code + "FTOI\nPUSHI 0\nEQUAL\nPUSHI 0\nEQUAL\n";
            } else {
                $code = $e.code;
            }
            $type = "bool";
        } else {
            $code = $e.code;
            $type = $e.type;
        }
    }
    |'(' e=expression ')' {$code = $e.code; $type = $e.type;}
    | a=expression op=('*'|'/'|'%') b=expression {
        String[] prom = promotion($a.code,$a.type,$b.code,$b.type) ; 
        $code = prom[0] + evalexpr($op.text, prom[1]);
        $type = prom[1];
    }
    | a=expression op=('+'|'-') b=expression {
        String[] prom = promotion($a.code,$a.type,$b.code,$b.type) ; 
        $code = prom[0] + evalexpr($op.text, prom[1]);
        $type = prom[1];
    }
    | ent=ENTIER {$code = "PUSHI "+$ent.text+"\n"; $type = "int";}
    | fl=FLOTTANT {$code = "PUSHF "+$fl.text+"\n"; $type = "float";}
    | bo=BOOL { $code = $bo.getText().equals("True") ? "PUSHI 1\n" : "PUSHI 0\n"; $type = "bool"; }
    | IDENTIFIANT {
        VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);
        $type = vi.type;

        int currentDepth = tablesSymboles.getCurrentDepth();
        int diffDepth = currentDepth - vi.depth;

        if (diffDepth <= 1){
            if (vi.type.equals("float")) {
                if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                    $code = "PUSHL " + vi.address + "\nPUSHL " + (vi.address + 1) + "\n";
                } else {
                    $code = "PUSHG " + vi.address + "\nPUSHG " + (vi.address + 1) + "\n";
                }
            } else {
                if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                    $code = "PUSHL " + vi.address + "\n";
                } else {
                    $code = "PUSHG " + vi.address + "\n";
                }
            }
        } else {
            String tmp = "PUSHL 0\n";
            for (int i = 1; i < diffDepth; i++) {
                tmp += "PUSHR 0\n";  // Suivre la chaîne
            }
            if (vi.type.equals("float")) {
                $code = tmp + "PUSHR " + vi.address + "\n" + tmp + "PUSHR " + (vi.address+1) + "\n";
            } else {
                $code = tmp + "PUSHR " + vi.address + "\n";
            }
        }
    }
    | IDENTIFIANT '('')' 
        {
            $type = tablesSymboles.getFunction($IDENTIFIANT.text);
            $code = push($type,"0") + "PUSHFP\n" + "CALL " + $IDENTIFIANT.text + "\n";
            $code += "POP\n"; 
        }
    | IDENTIFIANT  '(' args ')'
        {
            $type = tablesSymboles.getFunction($IDENTIFIANT.text);
            $code = push($type,"0") + $args.code + "PUSHFP\n" +"CALL " + $IDENTIFIANT.text + "\n";

            for(int i = 0; i < $args.size+1; i++) {
                $code += "POP\n";
            }
        }
    | IDENTIFIANT '[' ENTIER ']'
        {
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);
            String typeVi = vi.type.replaceAll("\\[\\]$", "");
            String brackets = vi.type.substring(typeVi.length());

            $type = typeVi;

            if (brackets.equals("[]") ) {
                int pre = Integer.parseInt($ENTIER.text);
                int elemSize = typeVi.equals("float") ? 2 : 1;
                int off = pre * elemSize;
                if (elemSize == 1) {
                    $code = "PUSHI " + vi.address + "\n" + "PUSHR " + off + "\n";
                } else {
                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                        $code =  "PUSHL " + (vi.address + off + 1) + "\n" + "PUSHL " + (vi.address + off) + "\n";
                    } else {
                        $code = "PUSHG " + (vi.address + off + 1) + "\n" + "PUSHG " + (vi.address + off) + "\n";
                    }
                }
            } else {
                throw new IllegalArgumentException("Type incorrect: " + vi.type);
            }
        }
    ;


decl returns [ String code, int size ]
    :
       IDENTIFIANT '::' TYPEV finInstruction
        {
        if ($TYPEV.text.equals("int")){
            $code = "PUSHI 0\n";
            $size = 1;
            tablesSymboles.addVarDecl($IDENTIFIANT.text,"int");
        } else if ($TYPEV.text.equals("float")){
            $code = "PUSHF 0.0\n";
            $size = 2;
            tablesSymboles.addVarDecl($IDENTIFIANT.text,"float");
        } else if ($TYPEV.text.equals("bool")){
            $code = "PUSHI 0\n";
            $size = 1;
            tablesSymboles.addVarDecl($IDENTIFIANT.text,"bool");
        }
        }
    |   IDENTIFIANT '::' TYPEV '[' ENTIER ']' finInstruction //tableau -> fin = taille_tableau
        {
            int nElem = Integer.parseInt($ENTIER.text);
            int elemSize = $TYPEV.text.equals("float") ? 2 : 1;
            int nAlloc = elemSize * (nElem + 1); 
            $code = "PUSHI 0\n";

    
            tablesSymboles.addVarDecl($IDENTIFIANT.text,$TYPEV.text + "[]");
            arraySize.put($IDENTIFIANT.text, nElem);
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);

            $code += "ALLOC " + nAlloc + "\n"+ "PUSHSP\n"+ "PUSHI " + nAlloc + "\n"+ "SUB\n" + "DUP\n" + "PUSHI " + (elemSize * nElem) + "\nADD\n" + "PUSHI -1\n" + "STORER 0\n";
            
            $size = 1 + nAlloc;
        }
    |  IDENTIFIANT '=' expression '::' TYPEV finInstruction
        {
        if ($TYPEV.text.equals("int")){
            $code = $expression.code;
            $size = 1;
            tablesSymboles.addVarDecl($IDENTIFIANT.text,"int");
        
        } else if ($TYPEV.text.equals("float")){
            $code = $expression.code;
            $size = 2;
            tablesSymboles.addVarDecl($IDENTIFIANT.text,"float");
        } else if ($TYPEV.text.equals("bool")){
            $code = $expression.code;
            $size = 1;
            tablesSymboles.addVarDecl($IDENTIFIANT.text,"bool");
        }
        }
    |IDENTIFIANT '=' '[' 
        {
            java.util.List<String> initValues = new java.util.ArrayList<String>();
            $size = 0;
        }
        expression
        {
            initValues.add($expression.code);
            $size = $expression.type.equals("float") ? 2 : 1;
        }
        ( ',' expression
        {
            initValues.add($expression.code);
            $size += $expression.type.equals("float") ? 2 : 1;
        }
        )*
        ']'
        '::' TYPEV '[' ENTIER ']' finInstruction 
        {
            int nElem = Integer.parseInt($ENTIER.text);
            int elemSize = $TYPEV.text.equals("float") ? 2 : 1;
            int nAlloc = elemSize * (nElem + 1); 

            if ($size != (nElem * elemSize) ){
                throw new IllegalArgumentException("Nombre de valeurs incorrects : " + $size);
            }

            $code = "PUSHI 0\n";
            tablesSymboles.addVarDecl($IDENTIFIANT.text,$TYPEV.text+"[]");
            arraySize.put($IDENTIFIANT.text, nElem);


            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);

            $code += "ALLOC " + nAlloc + "\n"+ "PUSHSP\n"+ "PUSHI " + nAlloc + "\n"+ "SUB\n" + "DUP\n" + "PUSHI " + (elemSize * nElem) + "\nADD\n" + "PUSHI -1\n" + "STORER 0\n";

            for (int i = 0; i < nElem; i++) {
                int off = i * elemSize;
                $code += storeArray(vi, off, elemSize, initValues.get(i));
            }
        }
    ;

assignation returns [ String code ] 
    : IDENTIFIANT '=' expression 
        {   VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);
            int currentDepth = tablesSymboles.getCurrentDepth();
            int diffDepth = currentDepth - vi.depth;
            
            if (diffDepth <= 1) {
                if (vi.type.equals("float")) {
                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                        $code = $expression.code + "STOREL " + (vi.address + 1) + "\nSTOREL " + vi.address + "\n";
                    } else {
                        $code = $expression.code + "STOREG " + (vi.address + 1) + "\nSTOREG " + vi.address + "\n";
                    }
                } else {
                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                        $code = $expression.code + "STOREL " + vi.address + "\n";
                    } else {
                        $code = $expression.code + "STOREG " + vi.address + "\n";
                    }
                }
            } else {
                String tmp = "PUSHL 0\n";
                for (int i = 1; i < diffDepth; i++) {
                    tmp += "PUSHR 0\n";
                }
                if (vi.type.equals("float")) {
                    $code = $expression.code + tmp + "STORER " + (vi.address+1) + "\n" + tmp + "STORER " + vi.address + "\n";
                } else {
                    $code = $expression.code + tmp + "STORER " + vi.address + "\n";
                }
            }
        }
    | IDENTIFIANT operator = ('++'|'--')
        {
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);
            int currentDepth = tablesSymboles.getCurrentDepth();
            int diffDepth = currentDepth - vi.depth;
            
            if (diffDepth <= 1) {
                if (vi.type.equals("float")) {
                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                        $code = "PUSHL " + vi.address + "\nPUSHL " + (vi.address + 1) + "\n" + push(vi.type,"1");
                    } else {
                        $code = "PUSHG " + vi.address + "\nPUSHG " + (vi.address + 1) + "\n" + push(vi.type,"1");
                    }
                } else {
                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                        $code = "PUSHL " + vi.address + "\n" + push(vi.type,"1");
                    } else {
                        $code = "PUSHG " + vi.address + "\n" + push(vi.type,"1");
                    }
                }
            } else {
                String tmp = "PUSHL 0\n";
                for (int i = 1; i < diffDepth; i++) {
                    tmp += "PUSHR 0\n";
                }
                if (vi.type.equals("float")) {
                    $code = tmp + "PUSHR " + vi.address + "\n" + tmp + "PUSHR " + (vi.address+1) + "\n" + push(vi.type,"1");
                } else {
                    $code = tmp + "PUSHR " + vi.address + "\n" + push(vi.type,"1");
                }
            }
            if($operator.text.equals("++")){
                $code += evalexpr("+",vi.type);
            }
            else{
                
                $code += evalexpr("-",vi.type);
            }
            if (diffDepth <= 1) {
                if (vi.type.equals("float")) {
                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                        $code += "STOREL " + (vi.address + 1) + "\nSTOREL " + vi.address + "\n";
                    } else {
                        $code += "STOREG " + (vi.address + 1) + "\nSTOREG " + vi.address + "\n";
                    }
                } else {
                    if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                        $code += "STOREL " + vi.address + "\n";
                    } else {
                        $code += "STOREG " + vi.address + "\n";
                    }
                }
            } else {
                String tmp = "PUSHL 0\n";
                for (int i = 1; i < diffDepth; i++) {
                    tmp += "PUSHR 0\n";
                }
                if (vi.type.equals("float")) {
                    $code += tmp + "STORER " + (vi.address+1) + "\n" + tmp + "STORER " + vi.address + "\n";
                } else {
                    $code += tmp + "STORER " + vi.address + "\n";
                }
            }
        }
    | IDENTIFIANT '=' '[' 
        {
            java.util.List<String> initValues = new java.util.ArrayList<String>();
            String type = "int";
        }
        expression
        {
            initValues.add($expression.code);
            if ($expression.type.equals("float")){
                type = "float";
            }
        }
        ( ',' expression
        {
            
            if ($expression.type.equals("float") && type.equals("float")){
                type = "float";
                initValues.add($expression.code);
            }
            else if ($expression.type.equals("int") && type.equals("int")){
                type = "int";
                initValues.add($expression.code);
            }
            else {
                throw new IllegalArgumentException("Type incorrect: 2" + type);
            }
        }
        )*
        ']'
        {
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);

            String typeVi = vi.type.replaceAll("\\[\\]$", "");
            String brackets = vi.type.substring(typeVi.length());
            int nElem = initValues.size();
            
            if (brackets.equals("[]") && typeVi.equals(type)) {
                Integer size= arraySize.get($IDENTIFIANT.text);
                if (size != null && nElem != size) {
                    throw new IllegalArgumentException("Nombre d'éléments incorrect: " + nElem + " fournis, " + size + " attendus pour le tableau " + $IDENTIFIANT.text);
                }
                int elemSize = type.equals("float") ? 2 : 1;
                $code = "";
                for (int i = 0; i < nElem; i++) {
                    int off = i * elemSize;
                    $code += storeArray(vi, off, elemSize, initValues.get(i));
                }
            } else {
                throw new IllegalArgumentException("Type incorrect: " + vi.type);
            }
        }
    | IDENTIFIANT'[' ENTIER ']' '=' expression
        {
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);

            String typeVi = vi.type.replaceAll("\\[\\]$", "");
            String brackets = vi.type.substring(typeVi.length());
            if (brackets.equals("[]") && typeVi.equals($expression.type)) {
                int pre = Integer.parseInt($ENTIER.text);
                int elemSize = typeVi.equals("float") ? 2 : 1;
                int off = pre * elemSize;
                $code = storeArray(vi, off, elemSize, $expression.code);
            } else {
                throw new IllegalArgumentException("Type incorrect: " + vi.type);
            }

        } 
    ;

get returns [String code]
    : 'get(' IDENTIFIANT ')'
        {
            VariableInfo vi = tablesSymboles.getVar($IDENTIFIANT.text);
            int currentDepth = tablesSymboles.getCurrentDepth();
            int diffDepth = currentDepth - vi.depth;
            
            if (diffDepth <= 1) {
                if (vi.scope == VariableInfo.Scope.PARAM || vi.scope == VariableInfo.Scope.LOCAL) {
                    if (vi.type.equals("int") || vi.type.equals("bool")){
                        $code = "READ\n" + "STOREL " + vi.address + "\n";
                    } else if (vi.type.equals("float")){
                        $code = "READF\n" + "STOREL " + (vi.address+1) + "\n" + "STOREL " + vi.address + "\n";
                    }
                } else {
                    if (vi.type.equals("int") || vi.type.equals("bool")){
                        $code = "READ\n" + "STOREG " + vi.address + "\n";
                    } else if (vi.type.equals("float")){
                        $code = "READF\n" + "STOREG " + (vi.address+1) + "\n" + "STOREG " + vi.address + "\n";
                    }
                }
            } else {
                String tmp = "PUSHL 0\n";
                for (int i = 1; i < diffDepth; i++) {
                    tmp += "PUSHR 0\n";
                }
                if (vi.type.equals("int") || vi.type.equals("bool")){
                    $code = "READ\n" + tmp + "STORER " + vi.address + "\n";
                } else if (vi.type.equals("float")){
                    $code = "READF\n" + tmp + "STORER " + (vi.address+1) + "\n" + tmp + "STORER " + vi.address + "\n";
                }
            }
        }
    ;

put returns [String code]
    : 'put(' expression ')'
        {   
            if($expression.type.equals("int") || $expression.type.equals("bool")){
                $code = $expression.code + "WRITE\n" + "POP\n";
            } else if ($expression.type.equals("float")){
                $code = $expression.code + "WRITEF\n" + "POP\nPOP\n";
            } else if ( $expression.type.endsWith("[]") ){
                String push = $expression.code;
                int elemSize = $expression.type.equals("float") ? 2 : 1;

                String start = newLabel();
                String end = newLabel();

                $code = push + "PUSHI 1\n" + "ADD\n" + "LABEL " + start + "\n";

                if (elemSize == 1) {
                    $code += "DUP\n" + "PUSHR 0\n" + "DUP\n" + "PUSHI -1\n" + "EQUAL\n" + "PUSHI 0\n" + "EQUAL\n" + "JUMPF " + end + "\n" + "WRITE\n" + "POP\n" + "PUSHI " + elemSize + "\n" + "ADD\n" + "JUMP " + start + "\n"+ "LABEL " + end + "\n"+ "POP\n";             
                } else {
                    $code += "DUP\n" + "PUSHR 0\n" + "DUP\n" + "PUSHI -1\n" + "EQUAL\n" + "PUSHI 0\n" + "EQUAL\n" + "JUMPF " + end + "\n" + push + "PUSHR 1\n" + "WRITEF\n" + "POP\nPOP\n" + "PUSHI " + elemSize + "\n"+ "ADD\n" + "JUMP " + start + "\n"+ "LABEL " + end + "\n"+ "POP\n";              
                }
            }
        }
    ;

bloc returns [ String code ]  @init{ $code = new String(); } 
    : '{' 
            NEWLINE? (instruction {$code += $instruction.code;})*    
      '}' NEWLINE*
    ;


/*logique returns [String code]
    : 'and' {$code = "and";}
    | 'or' {$code = "or";}
    ;*/


//float operation voir promotion ?? + modif operateur
condition returns [String code]
    : a = expression
        {
            if ($a.type.equals("bool")) {
                $code = $a.code;
            } else if ($a.type.equals("int")) {
                $code = $a.code + "PUSHI 0\nEQUAL\nPUSHI 0\nEQUAL\n";
            } else if ($a.type.equals("float")) {
                $code = $a.code + "FTOI\nPUSHI 0\nEQUAL\nPUSHI 0\nEQUAL\n";
            } else {
                $code = $a.code;
            }
        }
    | a = expression op=OPERATEUR b = expression
        {
            String[] prom = promotion($a.code,$a.type,$b.code,$b.type) ; 
            $code = prom[0] + evaloper($op.text, prom[1]);
        }
    | c = condition 'and' d=condition
        {$code = $c.code + $d.code + "MUL\n" + "PUSHI 1\n" + "EQUAL\n";} 
    | e = condition 'or' f=condition
        {$code = $e.code + $f.code + "ADD\n" + "PUSHI 1\n" + "SUPEQ\n";}
    | 'not' g=condition
        {$code = $g.code + "PUSHI 0\n" + "EQUAL\n";} 
    ;

boucle returns [String code]
    : 'while' '(' condition ')' instruction 
        {
            String start = newLabel();
            String end = newLabel();

            breakStack.push(end);
            continueStack.push(start);

            $code = "LABEL " + start + "\n" + $condition.code + "JUMPF " + end + "\n" + $instruction.code + "JUMP " + start + "\n" + "LABEL " + end + "\n";

            breakStack.pop();
            continueStack.pop();
        }
    | 'for' '(' a=assignation ';' b=condition ';' c=assignation ')' d=instruction
        {
            String start = newLabel();
            String end = newLabel();
            String continueLabel = newLabel();

            breakStack.push(end);
            continueStack.push(continueLabel);

            $code = $a.code + "LABEL " + start + "\n" + $b.code + "JUMPF " + end + "\n" + $d.code + "LABEL " + continueLabel + "\n" + $c.code + "JUMP " + start + "\n" + "LABEL " + end + "\n";

            breakStack.pop();
            continueStack.pop();
        }
   
    ;

breakBoucle returns [String code]
    : 'break' 
        {
            if (breakStack.isEmpty()) {
                throw new IllegalStateException("Break statement not within a loop");
            }
            $code = "JUMP " + breakStack.peek() + "\n";
        }
    ;

continueBoucle returns [String code]
    : 'continue'
        {
            if (continueStack.isEmpty()) {
                throw new IllegalStateException("Continue statement not within a loop");
            }
            $code = "JUMP " + continueStack.peek() + "\n";
        }
    ;

branchement returns [String code]
    : 'if' '(' c=condition ')' 'then' a=instruction  'else' b=instruction
        {
            String elseLabel = newLabel();
            String end = newLabel();
            $code = $c.code + "JUMPF " + elseLabel + "\n" + $a.code + "JUMP " + end + "\n" + "LABEL " + elseLabel + "\n" + $b.code + "LABEL " + end + "\n";
        }
    | 'if' '(' condition ')' 'then' a=instruction 
        {
            String end = newLabel();
            $code = $condition.code + "JUMPF " + end + "\n" + $a.code + "LABEL " + end + "\n" ;
        }
    ;

params
    :IDENTIFIANT '::' TYPEV
        {
            tablesSymboles.addParam($IDENTIFIANT.text,$TYPEV.text);
        }
        ( ',' IDENTIFIANT '::' TYPEV
            {
                tablesSymboles.addParam($IDENTIFIANT.text,$TYPEV.text);
            }
        )*
    ;

fonction returns [ String code ] //A FAIRE fonction imbriqué s'execute alors que pas appelé --> JUMP pour skip la fct imbriquée 
@after{ exitTableStack(); }
    : TYPEV ':=' IDENTIFIANT
        {
            String skip = newLabel();
            tablesSymboles.addFunction($IDENTIFIANT.text, $TYPEV.text);
            $code = "JUMP " + skip + "\n" + "LABEL " + $IDENTIFIANT.text + "\n";

            enterTableStack(); // il faut entrer apres avoir defini la fonction sinon il ne la trouve pas
        }
         '('  params ? ')'

        '{' 

        NEWLINE?

        (decl { $code += $decl.code; })*

        NEWLINE*

         (  instruction  { $code += $instruction.code; } )*

        '}' 

        NEWLINE*

         { $code += "RETURN\n" + "LABEL " + skip + "\n"; }
    ;

// init nécessaire à cause du ? final et donc args peut être vide (mais $args sera non null) 
args returns [ String code, int size] @init{ $code = new String(); $size = 0; }
    : ( expression
    {
        $code += $expression.code;
        $size = $expression.type.equals("float") ? 2 : 1;
    }
    ( ',' expression
    {
        $code += $expression.code;
        $size += $expression.type.equals("float") ? 2 : 1;
    }
    )*
      )?
    ;

/*expr returns [ String code, String type ]
    : IDENTIFIANT '('')' // appel de fonction
        {
            $code = "CALL " + $IDENTIFIANT.text + "\n";
        }
    | IDENTIFIANT  '(' args ')'
        {
            $code = $args.code + "CALL " + $IDENTIFIANT.text + "\n";

            // Nettoyage de la pile après l'appel
            for(int i = 0; i < $args.size; i++) {
                $code += "POP\n";
            }
        }
    ;*/





// lexer
TYPEV : 'int' | 'float' | 'bool' ;

RETURN: 'return';

finInstruction : ( NEWLINE | ';' )+ ;

NEWLINE : '\r'? '\n';

WS :   (' '|'\t')+ -> skip  ;

FLOTTANT : ('0'..'9')+ '.' ('0'..'9')* ;

ENTIER : ('0'..'9')+  ;

BOOL : 'True' | 'False';

OPERATEUR : '>'|'>='|'<'|'<='|'=='|'!=' ;

COMMENT
    : ('//' ~[\r\n]* | '/*' .*? '*/') -> skip  ;

IDENTIFIANT: [a-zA-Z][a-zA-Z0-9]*;

UNMATCH : . -> skip ;


/*
Fonction localement dans fonction:
une fonction peut acceder au variable de ses fonctions parents
les fonctions sont initialisés normalement donc pas de probleme juste un CALL dans la fonction parent

indic: pile de tablessymboles + info a ajouter dans VariableInfo
a chaque entree de fonc, on empile une table
a la sortie, on depile

fonction recherche dans les tables de la var
voir comment push la variable dans la pile
ou se trouve la variable dans la pile ? 
si var dans la fonction, basique
sinon trouver dans quelle fonction elle se trouve et faire un pushr(?) de la variable mais comment trouver la variable ?

VariableInfo : -type ; adresse; scope; ??->profondeur (dans la pile) -> cb d'imbrications? -> de combien remonter pour acceder a la variable
    ---> faire fonction pour trouver profondeur
        (de combien remonter par profondeur ?)
- adresse de son parent a stocker dans la fonction
    ---> PUSHL adresse fonction parent (puis comme PUSHR=P(sp-1)+n) PUSHR jusqu'a la variable puis PUSHR de la variable (vi.address)

 */
