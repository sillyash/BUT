
public class DVD extends Document implements Empruntable {

	public final static String JEU_VIDEO = "jeu-vidéo";
	public final static String DOCUMENTAIRE = "documentaire";
	public final static String FILM = "film";
	public final static int dureeEmpruntMax = 10;
	protected String categorie;
	
	public DVD(String titreDoc, String description, String categorie) {
		super(titreDoc, description);
		this.categorie = categorie;
	}

	// ----------------- Getters / Setters --------------------
	
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	// ----------------- Methods --------------------

	@Override
	public int dureeEmprunt() {
		return DVD.dureeEmpruntMax;
	}

	@Override
	public String toString() {
		return "DVD [categorie=" + categorie + ", titreDoc=" + titreDoc + ", description=" + description + "]";
	}
}
