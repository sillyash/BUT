
public class Stock {

	public static void main(String[] args) {
		
		//---------------------------------------
		// Déclarer les constantes
		//---------------------------------------
		
		final int NB_ARTICLES = 3;
		
		//---------------------------------------
		// Déclarer les tableaux
		//---------------------------------------
		
		char codesArticles [];
		double prixArticles [];
		
		//---------------------------------------
		// Allouer les tableaux avec une taille max définie en constante
		//---------------------------------------
		
		codesArticles = new char [NB_ARTICLES];
		prixArticles = new double [NB_ARTICLES];
		
		//---------------------------------------
		// Mettre des valeurs dans les cases
		//---------------------------------------
		
		codesArticles[0] = 'A';
		codesArticles[1] = 'B';
		codesArticles[2] = 'C';
		
		prixArticles[0] = 10.5;
		prixArticles[1] = 2.5;
		prixArticles[2] = 21.5;
		
		//---------------------------------------
		// Parcours des deux tableaux avec deux boucles foreach
		//---------------------------------------
		
		System.out.println("EXECUTION DU MAIN:");
		System.out.println("Parcours successif des tableaux avec deux boucle foreach :");
		
		for (char code : codesArticles)
		{
			System.out.print(code + "\t");
		}

		System.out.println();
		
		for (double prix : prixArticles)
		{
			System.out.print(prix + "\t");
		}
		
		//---------------------------------------
		// Parcours des deux tableaux en parallèle avec une boucle while et un booleen
		//---------------------------------------
		
		System.out.println("\n\nParcours des deux tableaux en parallèle avec une boucle while et un booleen :");
		
		int i = 0;
		
		while (i < NB_ARTICLES)
		{
			System.out.println(codesArticles[i] + "  -  " + prixArticles[i]);
			i++;
		}
		
		
		//---------------------------------------
		// Parcours des deux tableaux en parallèle avec une boucle for “classique” (pas une boucle for each)
		//---------------------------------------
		
		System.out.println("\nParcours des deux tableaux en parallèle avec une boucle for \"classique\" :");
		
		for (int j = 0; j<NB_ARTICLES; j++)
		{
			System.out.println(codesArticles[j] + "  -  " + prixArticles[j]);
		}

	}

}
