package volailler;

public abstract class Volaille {
	
	protected final int numeroID;
	protected double poids;
	
	public Volaille(double poids, int numeroID) {
		this.numeroID = numeroID;
		this.poids = poids;
	}
	
	// -- Getters & setters --
	
	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public int getNumeroID() {
		return numeroID;
	}
	
	// ------ Methods ------
	
	public abstract double getPrix();
	
	public abstract Boolean estAAbbatre();
	
	public abstract void changePrixKg(double prix);
	
	public String toString() {
		String s = "";
		s += "n°";
		s += this.numeroID;
		s += "\t";
		s += this.poids;
		s += "kg";
		return s;
	}
	
}
