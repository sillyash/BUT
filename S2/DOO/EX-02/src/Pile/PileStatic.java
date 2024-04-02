package Pile;

public class PileStatic {
	
	final static int NB_MAX = 100;
	static int [] pile = new int [NB_MAX];
	static int nbValeurs = 0;
	
	public static void empiler (int i) {
		pile[nbValeurs] = i;
		nbValeurs++;
	}
	
	public static void afficherSommet () {
		if (nbValeurs > 0) {
			System.out.print(pile[nbValeurs-1]);
		}
		else System.out.print("Cannot print : stack is empty.");
	}
	
	public static void empiler (int i, int j) {
		empiler(i);
		empiler(j);
	}
	
	public static void depiler () {
		if (nbValeurs > 0) {
			nbValeurs--;
		}
		else System.out.print("Cannot unstack : stack is empty.");
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Test : affichage pour pile vide");
		PileStatic.afficherSommet();
		System.out.println(); System.out.println();
		
		System.out.println("Test : insertion d'une valeur "
				+ "dans la pile et affichage du sommet");
		PileStatic.empiler(12);
		PileStatic.afficherSommet();
		System.out.println(); System.out.println();
		
		System.out.println("Test : insertion de plusieurs valeurs");
		PileStatic.empiler(4, 48);
		PileStatic.afficherSommet();
		System.out.println(); System.out.println();
		
		System.out.println("Test : depilement");
		PileStatic.depiler();
		PileStatic.afficherSommet();
	}

}
