package Pile;

public class PileObjet {

	final int NB_MAX;
	int [] pile;
	int nbValeurs = 0;
	
	PileObjet () {
		this.NB_MAX = 10;
		this.pile = new int [NB_MAX];
	}
	
	PileObjet (int nbMax) {
		if (nbMax > 0) {
			this.NB_MAX = nbMax;
		}
		else NB_MAX = 10;
		this.pile = new int [NB_MAX];
	}
	
	PileObjet (int nbMax, int val) {
		if (nbMax > 0) {
			this.NB_MAX = nbMax;
		}
		else NB_MAX = 10;
		this.pile = new int [NB_MAX];
		this.empiler(val);
	}
	
	public void empiler (int i) {
		// si la pile n'est pas pleine
		if (this.nbValeurs < this.NB_MAX) {
			this.pile[nbValeurs] = i;
			this.nbValeurs++;
		}
		else System.out.print("Cannot stack : stack is full");
	}
	
	public void afficherSommet () {
		// si la pile n'est pas vide
		if (this.nbValeurs > 0) {
			System.out.print(this.pile[nbValeurs-1]);
		}
		else System.out.print("Cannot print : stack is empty.");
	}
	
	public void empiler (int i, int j) {
		this.empiler(i);
		this.empiler(j);
	}
	
	public void depiler () {
		// si la pile n'est pas vide
		if (nbValeurs > 0) {
			nbValeurs--;
		}
		else System.out.print("Cannot unstack : stack is empty.");
	}
	
	public static void main(String[] args) {
		
		System.out.println("Test : constructeur");
		PileObjet p1 = new PileObjet ();
		PileObjet p2 = new PileObjet (1);
		PileObjet p3 = new PileObjet (5, 13);
		p2.empiler(9);
		p3.afficherSommet();
		System.out.println(); System.out.println();
		
		System.out.println("Test : affichage pour pile vide");
		p1.afficherSommet();
		System.out.println(); System.out.println();
		
		System.out.println("Test : insertion d'une valeur "
				+ "dans la pile et affichage du sommet");
		p1.empiler(12);
		p1.afficherSommet();
		System.out.println(); System.out.println();
		
		System.out.println("Test : insertion de plusieurs valeurs");
		p1.empiler(4, 48);
		p1.afficherSommet();
		System.out.println(); System.out.println();
		
		System.out.println("Test : depilement");
		p1.depiler();
		p1.afficherSommet();
		System.out.println(); System.out.println();
	}
}

