package banqueAL;

public class CompteCourant {

	public static final float SEUIL_SECURITE = 1000.00f;
	public static int lastNum = 0;
	
	private Client sonClient;
	private Banque saBanque;
	
	private int numero;
	private float solde;
	private float seuilDecouvertAutorise;
	
	// --------- Constructor(s) ---------
	
	CompteCourant (float solde, float seuilDecouvertAutorise) {
		numero = ++lastNum;
		if (solde <= SEUIL_SECURITE) {
			this.solde = solde;
		}
		else {
			System.out.println("ATTENTION tentative d’affectation "
					+ "suspecte d’un nouveau solde : compte N° " + numero);
		}
		this.seuilDecouvertAutorise = seuilDecouvertAutorise;
	}
	
	CompteCourant (float solde) {
		numero = ++lastNum;
		if (solde <= SEUIL_SECURITE) {
			this.solde = solde;
		}
		else {
			System.out.println("ATTENTION tentative d’affectation "
					+ "suspecte d’un nouveau solde : compte N° " + numero);
		}
		this.seuilDecouvertAutorise = SEUIL_SECURITE / 2;
	}

	// -------- Getters and Setters --------
	
	public int getNumero() {
		return numero;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {	
		if (solde <= SEUIL_SECURITE) {
			this.solde = solde;
		}
		else {
			System.out.println("ATTENTION tentative d’affectation "
					+ "suspecte d’un nouveau solde : compte N° " + numero);
		}
	}

	public float getSeuilDecouvertAutorise() {
		return seuilDecouvertAutorise;
	}

	public void setSeuilDecouvertAutorise(float seuilDecouvertAutorise) {
		this.seuilDecouvertAutorise = seuilDecouvertAutorise;
	}
	
	public Client getClient() {
		return sonClient;
	}
	
	public void setClient(Client client) {
		this.sonClient = client;
	}
	
	public Banque getBanque() {
		return saBanque;
	}
	
	public void setBanque(Banque banque) {
		this.saBanque = banque;
	}
	
	public static int getLastNum() {
		return lastNum;
	}
	
	public static void setLastNum(int num) {
		lastNum = num;
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

	public void debiter(float montant) {
		if (solde < montant) {
			System.out.println("Erreur : le solde du compte est "
					+ "inférieur au montant débiteur.");
		}
		else solde -= montant;
	}

}