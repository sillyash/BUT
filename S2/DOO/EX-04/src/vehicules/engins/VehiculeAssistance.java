package vehicules.engins;

public class VehiculeAssistance extends Engin {
	
	// ---------- Attributs ----------
	
	protected static final int CAPACITE = 1;
	protected static final float COUT = 300;
	
	// -------- Constructeurs --------
	
	public VehiculeAssistance() {
		super();
	}
		
	// ----------- Méthodes -----------
	
	public float getCout() {
		return COUT;
	}
}
