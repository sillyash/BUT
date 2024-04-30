public abstract class FormeGeometrique {
	private double x;
	private double y;
	
	FormeGeometrique (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void deplacer(double newX, double newY) {
		this.x = newX;
		this.y = newY;
	}
	
	public void afficher() {
		System.out.println(getClass().getName() 
				+ " - X : " + getX() 
				+ " \tY : " + getY()
				+ " \tPérimètre : " + perimetre()
				+ " \tSurface : " + surface());
	}
	
	abstract double perimetre();
	
	abstract double surface();

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
}
