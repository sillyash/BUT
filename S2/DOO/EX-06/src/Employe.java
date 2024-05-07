
public abstract class Employe {
	protected String nom;
	protected String prenom;
	protected int age;
	protected int anneeAnciennete;
	protected boolean employeARisque;
	protected static float BONUS_RISQUE = 250f;
	
	public Employe (String nom, String prenom, int age, int anneeAnciennete, boolean employeARisque) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.anneeAnciennete = anneeAnciennete;
		this.employeARisque = employeARisque;
	}
	
	protected abstract float calculerSalaire();
	
	public String getNom() {
		return "L'employé.e " + this.prenom + " " + this.nom;
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAnneeAnciennete() {
		return anneeAnciennete;
	}

	public void setAnneeAnciennete(int anneeAnciennete) {
		this.anneeAnciennete = anneeAnciennete;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isEmployeARisque() {
		return employeARisque;
	}

	public void setEmployeARisque(boolean employeARisque) {
		this.employeARisque = employeARisque;
	}

	public static float getBONUS_RISQUE() {
		return BONUS_RISQUE;
	}

	public static void setBONUS_RISQUE(float bONUS_RISQUE) {
		BONUS_RISQUE = bONUS_RISQUE;
	}

	public String ToString() {
		String s = "";
		
		s += getNom() + " :\t";
		s += getAge()+ " ans, ";
		s += getAnneeAnciennete() + " année(s) d'ancienneté, ";
		s += "à risque : ";
		if (this.employeARisque) s += "Oui";
		else s += "Non";
		
		return s;
	}
}
