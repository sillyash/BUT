package Table;

public class Magasin {
	
	final int NB_TABLES_MAX;
	String nom;
	Table [] tables;
	int nbTablesStockees;
	
	public Magasin (String nom, int nbTablesMax) {
		this.NB_TABLES_MAX = nbTablesMax;
		this.nom = nom;
		this.tables = new Table [NB_TABLES_MAX];
		this.nbTablesStockees = 0;
	}
	
	public void add (Table t) {
		this.tables[nbTablesStockees] = t;
		this.nbTablesStockees++;
	}
	
	public void afficher () {
		System.out.println("Nom :\t\t\t" + this.nom);
		System.out.println("Tables stockées :\t" + this.nbTablesStockees);
		System.out.println("Stockage max :\t\t" + this.NB_TABLES_MAX);
		System.out.println("Valeur du stock :\t$" + this.valeurStock());
		this.afficherTables();
		System.out.println();
	}
	
	public void afficherTables () {
		for (int i=0; i<this.nbTablesStockees; i++) {
			// System.out.println("\t\033[0;1m" + "Table " + i + "\033[m");
			// imprime le texte en gras (certains terminals)
			System.out.println("\tTable " + i);
			this.tables[i].afficher(1);
		}
	}
	
	public float valeurStock () {
		float sommePrix = 0;
		for (int i=0; i<nbTablesStockees; i++) {
			sommePrix += this.tables[i].prix;
		}
		return sommePrix;
	}
	
	public static void main (String args[]) {
		Magasin magasinIkeaEvry = new Magasin ("Ikea Evry", 3);
		Magasin magasinIkea = new Magasin ("Ikea", 6);
		
		magasinIkeaEvry.add(new Table ());
		magasinIkeaEvry.add(new Table (4, Table.NOIR, 19.99f));
		magasinIkeaEvry.add(new Table (5, Table.BLANC, 48.82f));
		
		magasinIkea.add(new Table ());
		magasinIkea.add(new Table (4, Table.NOIR, 19.99f));
		magasinIkea.add(new Table (5, Table.BLANC, 48.82f));
		magasinIkea.add(new Table (4, Table.MARRON, 35.50f));
		
		magasinIkeaEvry.afficher();
		magasinIkea.afficher();
	}
}