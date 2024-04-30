package banqueAbstraite;

public class CompteCourant extends Compte {

	protected static final float SEUIL_SECURITE = 1000.00f;
	protected float seuilDecouvertAutorise;
	
	// --------- Constructor(s) ---------
	
	CompteCourant (float solde, float seuilDecouvertAutorise) {
		super(solde, SEUIL_SECURITE);
		this.seuilDecouvertAutorise = seuilDecouvertAutorise;
		setSolde(solde);
	}
	
	CompteCourant (float solde) {
		super(solde, SEUIL_SECURITE);
		this.seuilDecouvertAutorise = SEUIL_SECURITE;
	}
	
	CompteCourant (float solde, Client client) {
		super(solde, client);
		this.seuilDecouvertAutorise = SEUIL_SECURITE;
	}
	
	CompteCourant (float solde, Banque banque) {
		super(solde, banque);
		this.seuilDecouvertAutorise = SEUIL_SECURITE;
	}

	CompteCourant (float solde, Banque banque, Client client) {
		super(solde, banque, client);
		this.seuilDecouvertAutorise = SEUIL_SECURITE;
	}
	
	// -------- Getters and Setters --------

	public float getSeuilDecouvertAutorise() {
		return seuilDecouvertAutorise;
	}

	public void setSeuilDecouvertAutorise(float seuilDecouvertAutorise) {
		this.seuilDecouvertAutorise = seuilDecouvertAutorise;
	}

	public static float getSeuilSecurite() {
		return SEUIL_SECURITE;
	}
	
	@Override
	public void setSolde(float solde) {	
		if (checkSeuil(solde, SEUIL_SECURITE)) this.solde = solde;
		else System.out.println("Tentative d'affectation de montant suspecte.");
	}
	
	// ----------- Methods -----------
	
	public String toString () {
		  String s = "";
		  
		  s += "Courant";
		  
		  s += "\t| Compte N° ";
		  s += numero;
		  
		  s += "\t| Solde : ";
		  s += solde;
		  
		  s += "\t| Seuil de découvert : ";
		  s += seuilDecouvertAutorise;
		  
		  s += "\t| Banque : ";
		  if (saBanque != null) {
			  s += saBanque.getNom();
		  }
		  else s += saBanque.getNom();
			  
		  s += "\t| Client : ";
		  if (sonClient != null) {
			  s += sonClient.getNom();
		  }
		  else s += sonClient.getNom();
		  
		  return s ;
	}

	public void crediter(float montant) {
		float montantDepose = montant + montant * Banque.getTauxRemuneration();
		setSolde(this.solde + montantDepose);
	}

	@Override
	public void debiter(float montant) {
		if (this.solde + getSeuilDecouvertAutorise() < montant) {
			System.out.println("Erreur : le solde du compte est "
			+ "inférieur au montant débiteur.");
		}
		else setSolde(this.solde - montant);
	}
	
	protected Boolean checkSeuil(float montant, float seuil) {
		if (this.solde + montant <= seuil) return true;
		else return false;
	}

}