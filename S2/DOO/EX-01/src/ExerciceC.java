public class ExerciceC {

	public static void echangerChar(char a, char b) {
		
		// Affichage des valeurs avant échange
		System.out.println("Valeurs des caractères avant échange: "+ a + "\t" + b);
		
		// variable pour stocker la valeur lors de l'échange
		char temp = a;
		
		// échange b <-> a et affichage de leurs valeurs
		a = b;
		System.out.println("Nouvelle valeur du 1er caractère: " + a);
		
		b = temp;
		System.out.println("Nouvelle valeur du 2eme caractère: " + b);
	}
	
	
	public static void insererFloatDebut (float f, float [] tabFloat) {
		tabFloat[0] = f;
	}
	
	
	public static void modifAddresseTableau (double [] tabDouble) {
		tabDouble = null;
	}
	
	public static void echangerDeuxFloat(float f1, float f2) {
		
		// variable pour stocker la valeur lors de l'échange
		float temp = f2;
		
		// échange des valeurs
		f2 = f1;
		f1 = temp;
	}
	
	public static void echangerDeuxFloatDansTableau(float[] tabFloat) {
		
		// variable pour stocker la valeur lors de l'échange
		float temp = tabFloat[0];
		
		// échange des valeurs
		tabFloat[0] = tabFloat[1];
		tabFloat[1] = temp;
	}
	
	public static void main(String[] args) {
		
		System.out.println("---------- echangerChar ----------");
		
		// Création et affectations de deux caractères
		char c1 = 'x';
		char c2 = 'y';
		
		// Création d'un table de caractères
		echangerChar(c1,c2);
		
		// Parcours et affichage des valeurs après exécution
		System.out.println("Affichage des caractères :\t" + c1 + "\t" + c2);
		
		// --------------------------------------------------------------
		
		System.out.println("\n---------- insererFloatDebut ----------");
		
		float [] tabFloat = new float[1];
		
		// Affichage de la valeur à la case 0
		System.out.println("Valeur à la case 0 du tableau de floats avant exécutuion: " + tabFloat[0]);
		
		// Insertion de la valeur 150.7f dans un tableau à l'indice 0
		insererFloatDebut(150.7f, tabFloat);
		
		// Affichage de la valeur à la case 0
		System.out.println("Valeur à la case 0 du tableau de floats après exécutuion: " + tabFloat[0]);
		
		// On remarque que la méthode fonctionne en utilisant un tableau car tabFloat est une 
		// référence (addresse) et pas une copie. On modifie donc dans la mémoire la valeur.
		
		// --------------------------------------------------------------
		
		System.out.println("\n---------- modifAddresseTableau ----------");
		
		// Création d'un tableau de doubles
		double [] tabDouble = new double [1];
		
		// Affichage de l'adresse du tableau avant exécution de la méthode
		System.out.println("Addresse du tableau: " + tabDouble);
		
		// Tentative de modification de l'adresse du tableau à null
		System.out.println("Exécution de la méthode...");
		modifAddresseTableau(tabDouble);
		
		// Affichage de l'adresse du tableau après exécution de la méthode
		System.out.println("Addresse du tableau: " + tabDouble);
		
		// --------------------------------------------------------------
		
		System.out.println("\n---------- echangerDeuxFloat ----------");
		
		// Création de deux floats
		float f1 = 102.56f;
		float f2 = 41.237f;
		
		// Affichage des valeurs avant exécution de la méthode
		System.out.println("Valeur des floats avant exécution :\t" + f1 + "\t" + f2);
		
		// Echange des deux floats via la méthode
		System.out.println("Exécution de la méthode...");
		echangerDeuxFloat(f1, f2);
		
		// Affichage des valeurs après exécution de la méthode
		System.out.println("Valeur des floats après exécution :\t" + f1 + "\t" + f2);
		
		// --------------------------------------------------------------
		
		System.out.println("\n---------- echangerDeuxFloatDansTableau ----------");
		
		// Création d'un tableau contenant deux floats
		float [] tabF = {f1, f2};
		
		// Affichage des valeurs avant exécution de la méthode
		System.out.println("Valeur des floats avant exécution :\t" + tabF[0] + "\t" + tabF[1]);
		
		// Echange des deux floats via la méthode
		System.out.println("Exécution de la méthode...");
		echangerDeuxFloatDansTableau(tabF);
		
		// Affichage des valeurs après exécution de la méthode
		System.out.println("Valeur des floats après exécution :\t" + tabF[0] + "\t" + tabF[1]);
		
		// On remarque que l'échange fonctionne en utilisant un tableau car tabF est une 
		// référence et pas une copie. On échange donc dans la mémoire les valeurs.
		
	}

}
