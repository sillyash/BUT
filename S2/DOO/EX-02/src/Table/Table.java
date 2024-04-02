package Table;

public class Table {

	int nbPieds;
	String couleur;
	float prix;
	
	final static String MARRON = "marron"; // DEFAULT
	final static String NOIR = "noir";
	final static String BLANC = "blanche";
	final static String COULEUR_DEF = MARRON;
	final int NB_PIEDS_DEF = 4;
	final float PRIX_DEF = 0.0f;
	
	public Table() {
		couleur = COULEUR_DEF;
		nbPieds = NB_PIEDS_DEF;
		prix = PRIX_DEF;
	}
	
	public Table(int nbPieds) {
		couleur = COULEUR_DEF;
		this.nbPieds = nbPieds;
	}
	
	public Table(int nbPieds, String coul) {
		this.nbPieds = nbPieds;
		this.couleur = coul;
		prix = PRIX_DEF;
	}
	
	public Table(int nbPieds, String couleur, float prix) {
		this.nbPieds = nbPieds;
		this.couleur = couleur;
		this.prix = prix;
	}
	
	public void afficher () {
		System.out.println("Pieds :\t\t" + this.nbPieds);
		System.out.println("Couleur :\t" + this.couleur);
		System.out.println("Prix :\t\t" +  this.prix);
		System.out.println();
	}
	
	public void afficher (int nbTab) {
		// Affiche les données après nbTab tabulations
		System.out.println("\t".repeat(nbTab) + "Pieds :\t\t" + this.nbPieds);
		System.out.println("\t".repeat(nbTab) + "Couleur :\t" + this.couleur);
		System.out.println("\t".repeat(nbTab) + "Prix :\t\t" +  this.prix);
		System.out.println();
	}
	
	public static void main(String args []) {
		Table t = new Table ();
    	t.afficher();
   	 
    	Table t2 = new Table (3);
    	t2.afficher();
   	 
    	Table t3 = new Table (6, Table.NOIR);
    	t3.afficher();
    	
    	// -------------- EX-B --------------
    	Table t4 = t3;
    	
    	System.out.println("Ancien prix :\t" + t4.prix);
    	t4.prix = 80;
    	System.out.println("Nouveau prix :\t" + t4.prix + "\n");
    	
    	// -------------- EX-C ---------------
    	// ------- V1 -------
    	Table [] tableauTables = new Table [4];
    	
    	tableauTables[0] = t2;
    	tableauTables[1] = t3;
    	tableauTables[2] = new Table (4, Table.BLANC, 15);
    	
    	float sommePrixTables = 0.0f;
    	for (int i=0; i < tableauTables.length; i++) {
    		if (tableauTables[i] != null) {
    			sommePrixTables += tableauTables[i].prix;
    			System.out.print("Prix de la table n°" + i + " : ");
    			System.out.println(tableauTables[i].prix);
    		}
    	}
    	
    	System.out.println("Total des prix : " + sommePrixTables + "\n");
	}
}

