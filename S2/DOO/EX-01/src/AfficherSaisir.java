import java.util.Scanner;

public class AfficherSaisir {

	public static void main(String[] args) {
		System.out.println("Bonjour");
		
		// Afficher PI avec 3 chiffres derriere la virgule 
		System.out.printf("%.3f\n\n", Math.PI);
		
		
		Scanner sc = new Scanner(System.in);		// open input stream
		System.out.println("Saisissez un nombre entier :");
		int n = sc.nextInt();
		sc.close();									// close input stream
		
		// Générer un nombre entier random et l'afficher
		int rand = (int) (n * Math.random());
		System.out.println("Your random number is " + rand);
	}

}
