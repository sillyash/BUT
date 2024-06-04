
public abstract class Livre extends Document {

	protected String auteur;

	public Livre(String titreDoc, String description, String auteur) {
		super(titreDoc, description);
		this.auteur = auteur;
	}

	// ----------------- Getters / Setters --------------------
	
	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	// ----------------- Methods --------------------

	public abstract boolean estDifficile();
	
}
