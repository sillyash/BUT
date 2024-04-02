package vehicules;

public abstract class Vehicule {
	
	// ---------- Attributs ----------
	
	protected static int lastID = 100;
	
	protected final int vehiculeID;
	protected int numeroIntervention;
	protected float distanceIntervention;
	
	// -------- Constructeurs --------
	
	public Vehicule() {
		this.vehiculeID = lastID++;
	}
	
	// ----------- Méthodes -----------
	
	public abstract float getCout();
	
}
