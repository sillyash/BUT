package banqueAL;

public class CompteEpargne {
	private int numero;
	private float solde;
	private float tauxInteret;
	private Client sonClient;
	private Banque saBanque;
	
	// ---------- Constructors ----------
	
	CompteEpargne(float solde, float tauxInteret) {
		this.numero = CompteCourant.getLastNum() + 1;
		CompteCourant.setLastNum(CompteCourant.lastNum + 1);
		this.solde = solde;
		this.tauxInteret = tauxInteret;
	}
	
	// ------- Getters & setters -------
	
	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public float getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(float tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public Client getSonClient() {
		return sonClient;
	}

	public void setSonClient(Client sonClient) {
		this.sonClient = sonClient;
	}

	public int getNumero() {
		return numero;
	}
	
	public Banque getBanque() {
		return this.saBanque;
	}
	
	public void setBanque(Banque banque) {
		this.saBanque = banque;
	}
	
	public Client getClient() {
		return this.sonClient;
	}
	
	public void setClient(Client client) {
		this.sonClient = client;
	}
	
	// ---------- Methods ----------
	
	public String toString() {
		String s = "";
		
		s += "Epargne";
		
		s += "\t| Compte N° ";
		s += numero;
		  
		s += "\t| Solde : ";
		s += solde;
		  
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
		float montantDepose = montant + montant * this.getTauxInteret();
		this.setSolde(this.getSolde() + montantDepose);
	}
	
	public void debiter(float montant) {
		if (this.getSolde() < montant) {
			System.out.println("Erreur : le solde du compte est "
					+ "inférieur au montant débiteur.");
		}
		else this.setSolde(this.getSolde() - montant);
	}
	
}
