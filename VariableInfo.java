import java.util.*;

public  class VariableInfo {

    // Enumeration of possible scope for variable 
    public enum Scope {
	    GLOBAL, // Global variable
	    PARAM, // Parameter of a function
	    LOCAL // Local variable (inside a function
    }  
   

    
    public final int address;
    public final String type;
    public final Scope scope;
    public final int depth;

    public VariableInfo( int a, Scope s, String t){
        this(a, s, t, 0);
    }

    // Constructor with depth parameter
    public VariableInfo( int a, Scope s, String t, int depth){
        this.address = a; // Address of variable
        this.type = t;    // type of variable
    this.scope = s; // Scope of variable
    this.depth = depth;
    }

    /* Get the size of the Variable */
    public static int getSize( String t ) {
        if (t.equals("int") || t.equals("bool"))   return 1;
        if (t.equals("float")) return 2;
        if (t.endsWith("[]")) {
            return 1; 
        }
        System.err.println("Erreur: type "+ t + " non dÃ©fini");
        return 0;
    }

}

