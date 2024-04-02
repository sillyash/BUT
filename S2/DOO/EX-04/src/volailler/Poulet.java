package volailler;

import java.util.ArrayList;
import java.util.Arrays;

public class Poulet extends Volaille {

	protected static double prixKg = 12.43f;
	protected static double poidsAbattage = 1.28;
	protected static ArrayList<Double> historiquePrix = new ArrayList<>(Arrays.asList(prixKg));
	
	Poulet(double poids, int numeroID) {
		super(poids, numeroID);
	}
	
	// getter & setters
	
	public static double getPrixKg() {
		return Poulet.prixKg;
	}
	
	public void changePrixKg(double prix) {
		Poulet.prixKg = prix;
		historiquePrix.add(Poulet.prixKg);
	}
	
	// methods
	
	public double getPrix() {
		return Poulet.prixKg * this.getPoids();
	}
	
	public Boolean estAAbbatre() {
		return this.poids >= poidsAbattage;
	}
}
