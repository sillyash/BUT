package volailler;

import java.util.ArrayList;
import java.util.Arrays;

public class Canard extends Volaille {

	protected static double prixKg = 14.96f;
	protected static double poidsAbattage = 1.2;
	protected static ArrayList<Double> historiquePrix = new ArrayList<>(Arrays.asList(prixKg));
	
	Canard(double poids, int numeroID) {
		super(poids, numeroID);
	}
	
	// getters & setters
	
	public static double getPrixKg() {
		return Poulet.prixKg;
	}
	
	public void changePrixKg(double prix) {
		Canard.prixKg = prix;
		historiquePrix.add(Poulet.prixKg);
	}
	
	// methods
	
	public double getPrix() {
		return Canard.prixKg * this.getPoids();
	}
	
	public Boolean estAAbbatre() {
		return this.poids >= poidsAbattage;
	}
}
