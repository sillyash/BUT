public class Rec extends FormeGeometrique {

	private double largeur;
	private double longueur;
	
	Rec (double x, double y, double largeur, double longueur) {
		super(x, y);
		this.largeur = largeur;
		this.longueur = longueur;
	}
	
	@Override
	double perimetre() {
		return 2 * (getLongueur() + getLargeur());
	}

	@Override
	double surface() {
		return getLongueur() * getLargeur();
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}
	
	

}
