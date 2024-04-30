
public abstract class Employe {
	protected String nom;
	protected String prenom;
	protected int age;
	protected int anneeAnciennete;
	
	public Employe (String nom, String prenom, int age, int anneeAnciennete) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.anneeAnciennete = anneeAnciennete;
	}
	
	protected abstract float calculerSalaire();
}
