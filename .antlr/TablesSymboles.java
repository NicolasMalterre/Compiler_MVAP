import java.util.*;

/** 
 * 3 Tables des symboles :
 *     _ Une table pour les variables globales; 
 *     _ Une pour les paramÃ¨tres;
 *     _ une pour les variables locales.
 *
 *     Chaque table donne pour chaque variable sa position (son adresse dans la pile).
 *     On recherche d'abord en local puis en paramÃ¨tre si dÃ©fini.
 *     Comme on manipule des variables typÃ©es, on stocke Ã©galement le type et le scope.
 *
 *     On utlise ici des tables de hachage stockant des objets VariableInfo
 *
 *     Pour autoriser un fonction et une variable de mÃªme nom, on ajoute aussi :
 *     _ Une Table des Ã©tiquettes des fonctions.
 *
 *     Note : une pile de tables pourrait Ãªtre nÃ©cessaire,
 *       si on voulait pouvoir dÃ©finir des fonctions dans des fonctions...
 *
 *    Et on conserve la derniÃ¨re fonction ajoutÃ©e pour savoir le type de la valeur de retour
 */
class TablesSymboles {

    
    private TableSimple _tableGlobale = new TableSimple(); // Table des variables globales 
    private TableSimple _tableParam = null; // Table des paramÃªtres
    private TableSimple _tableLocale = null; // Table des variables locales
    private HashMap<String, String> _tableFonction = new HashMap<String, String>(); // Table des fonctions 

    private String _returnType = null;
	private int _currentDepth;
	private TablesSymboles _parent = null; // RÃ©fÃ©rence Ã  la table parent pour fonctions imbriquÃ©es

	// Constructeur par dÃ©faut
	public TablesSymboles() {
		this._parent = null;
	}

	// Constructeur avec parent
	public TablesSymboles(TablesSymboles parent) {
		this._parent = parent;
		if (parent != null) {
			this._currentDepth = parent._currentDepth;
			this._returnType = parent._returnType; // Copier le type de retour
		}
	}

    /* 
     * Partie pour la crÃ©ation / cloture des tables locale
     * Ã€ faire lors de lâ€™entrÃ©e ou la sortie dâ€™une fonction
     */

    // CrÃ©er les tables locales pour les variables dans une fonction
    public void enterFunction() { 
	_tableLocale = new TableSimple();
	_tableParam = new TableSimple();
	_currentDepth+=1;
    }

    // DÃ©truire les tables locales des variables Ã  la sortie dâ€™une fonction
    public void exitFunction() { 
	_tableLocale = null;
        _tableParam = null;
		_currentDepth-=1;
    }

	public int getCurrentDepth() {
        return _currentDepth;
    }



    // Connaitre la taille occupÃ©e par les variables locales
    public int getVariableLocalSize() {
	    if ( _tableLocale == null) {
		 System.err.println("Erreur: Impossible de connaÃ®tre la taille des variables locales car les tables locales ne sont pas initialisÃ©es");
		 return 0;
	    }
	    return _tableLocale.getSize();
    }



    /* 
     * Partie pour lâ€™ajout ou la rÃ©cupÃ©ration de variable et paramÃ¨tres
     */

    // Ajouter une nouvelle variable Locale ou globale
    public void addVarDecl(String name, String t) {
	if ( _tableLocale == null ) { // On regarde si on est Ã  lâ€™extÃ©rieur dâ€™une fonction
		// On a une variable globale
		_tableGlobale.addVar(name,VariableInfo.Scope.GLOBAL,t,0); 
	} else {
		// On a une variable locale 
		_tableLocale.addVar(name,VariableInfo.Scope.LOCAL,t,_currentDepth);

	}
    }

	

    // Ajouter un paramÃ¨tre de fonction
    public void addParam(String name, String t) {
        if ( _tableParam == null ) {
	       	System.err.println("Erreur: Impossible dâ€™ajouter la variable "+name+
			"car les tables locales ne sont pas initialisÃ©es");
	} else { 
                _tableParam.addVar(name,VariableInfo.Scope.PARAM,t,_currentDepth);
        }
    }

    // RÃ©cupÃ©rer les infos dâ€™une variable (ou dâ€™un paramÃ¨tre)
    public VariableInfo  getVar(String name) {
	if ( _tableLocale != null ) {  
		// On cherche dâ€™abord parmi les variables locales
		VariableInfo vi = _tableLocale.getVariableInfo(name);
		if (vi != null) { return vi;} // On a trouvÃ©
	}
	if ( _tableParam != null ) {
		// On cherche ensuite parmi les paramÃ¨tres
		VariableInfo vi = _tableParam.getVariableInfo(name);
		if (vi != null) 
		{
			return new VariableInfo( 
				vi.address - (_tableParam.getSize() + 3),
				// On calcule l'adresse du paramètre (+3 pour static_link, ancien_fp, adr_retour)
				vi.scope,
				vi.type,
				vi.depth);	
		}
	}
	// Enfin, on cherche parmi les variables globales
	VariableInfo vi = _tableGlobale.getVariableInfo(name);
	if (vi != null) {
		return vi;
	}
	// Si non trouvÃ©, chercher dans le parent (pour fonctions imbriquÃ©es)
	if (_parent != null) {
		return _parent.getVar(name);
	}
	System.err.println("## Erreur : la variable \"" + name + "\" n'existe pas");
	return null; // Attention: ceci ne doit pas arriver et va probablement faire planter le programme
    }

    // RÃ©cupÃ©rer lâ€™adresse de la valeur de retour
    //  Note: Cette fonction ne doit Ãªtre appelÃ© quâ€™aprÃ¨s avoir dÃ©clarer les paramÃ¨tres
    public VariableInfo getReturn() {
	    if ( _tableParam == null ) {
        	        System.err.println("Erreur: Impossible de calculer lâ€™emplacement"+
					" de la valeur de retour car les tables locales"+
				        " ne sont pas initialisÃ©es");
			return null;  // Attention: ceci ne doit pas arriver et va probablement faire planter le programme
	    }
	    return  new VariableInfo(
                         - (_tableParam.getSize() + 3 + VariableInfo.getSize(_returnType)), // +3 pour static_link, ancien_fp, adr_retour
                         VariableInfo.Scope.PARAM, 
                         _returnType);
    }


    /* 
     * Partie pour les fonctions 
     *
     */
    public String getFunction(String function) {
	String l = _tableFonction.get(function);
	if (l != null)
	    return l;
	// Si non trouvÃ©, chercher dans le parent (pour fonctions imbriquÃ©es)
	if (_parent != null) {
		return _parent.getFunction(function);
	}
	System.err.println("Appel Ã  une fonction non dÃ©finie \""+function+"\"");
	return null;
    }

    public void addFunction(String function,String type) {
        String fat = _tableFonction.get(function);
	if ( fat!= null ) {
	    System.err.println("Fonction \""+ function + 
			    "\" dÃ©jÃ  dÃ©finie avec type de retour \"" + fat +"\".");
	    return;
	}
	_returnType=type;
	_tableFonction.put(function, type);
	return;
    }
	

}
    
