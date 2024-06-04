
public class LivreEcrit extends Livre implements Empruntable {

	protected int nbPages;
	
	public LivreEcrit(String titreDoc, String description, String auteur, int nbPages) {
		super(titreDoc, description, auteur);
		this.nbPages = nbPages;
	}
	
	// ----------------- Getters / Setters --------------------
	
	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
		
	// ----------------- Methods --------------------
	
	@Override
	public boolean estDifficile() {
		return this.nbPages > 300;
	}

	@Override
	public int dureeEmprunt() {
		if (this.estDifficile()) return 30;
		else if (this.nbPages > 150) return 15;
		else return 7;
	}

	@Override
	public String toString() {
		return "LivreEcrit [nbPages=" + nbPages + ", auteur=" + auteur + ", titreDoc=" + titreDoc + ", description="
				+ description + "]";
	}
	
}
