package banqueAbstraite;

public abstract class Salarie {
	protected float salaire;
	
	public Salarie(float salaire) {
		this.salaire = salaire;
	}
	
	public float getSalaire() {
		return this.salaire;
	}
	
	public abstract void verserSalaire();
}
