
public class Periodique extends Document {
	
	protected int parutionsAnnuelles;
	public static int MENSUEL = 12;
	public static int HEBDOMADAIRE = 52;
	public static int QUOTIDIEN = 365;

	public Periodique(String titreDoc, String description, int parutionsAnnuelles) {
		super(titreDoc, description);
		this.parutionsAnnuelles = parutionsAnnuelles;
	}

	// ----------------- Getters / Setters --------------------
	
	public int getParutionsAnnuelles() {
		return parutionsAnnuelles;
	}

	public void setParutionsAnnuelles(int parutionsAnnuelles) {
		this.parutionsAnnuelles = parutionsAnnuelles;
	}
	
	// ----------------- Methods --------------------
	
	@Override
	public String toString() {
		return "Periodique [parutionsAnnuelles=" + parutionsAnnuelles + ", titreDoc=" + titreDoc + ", description="
				+ description + "]";
	}

}
