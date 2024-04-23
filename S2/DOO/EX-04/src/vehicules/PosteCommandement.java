package vehicules;

public class PosteCommandement extends Vehicule {
	
	// ---------- Attributs ----------
	
	private static final float COUT = 1000;

	// -------- Constructeurs --------
	
	public PosteCommandement() {
		super();
	}

	// ----------- Méthodes -----------
	
	public float getCout() {
		return COUT;
	}
	
}
