
public class Cercle extends FormeGeometrique {
	private double rayon;
	
	Cercle (double x, double y, double rayon) {
		super(x,y);
		this.rayon = rayon;
	}
	
	@Override
	double perimetre() {
		return 2 * Math.PI * getRayon();
	}

	@Override
	double surface() {
		return Math.PI * Math.pow(getRayon(), 2);
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		this.rayon = rayon;
	}

}
