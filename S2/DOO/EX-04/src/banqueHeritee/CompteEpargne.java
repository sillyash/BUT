package banqueHeritee;

public class CompteEpargne extends Compte {

	protected float tauxInteret;
	
	// ---------- Constructors ----------
	
	CompteEpargne(float solde, float tauxInteret) {
		super(solde);
		setTauxInteret(tauxInteret);
	}
	
	CompteEpargne(float solde, float tauxInteret, Client client) {
		super(solde, client);
		setTauxInteret(tauxInteret);
	}
	
	CompteEpargne(float solde, float tauxInteret, Banque banque) {
		super(solde, banque);
		setTauxInteret(tauxInteret);
	}
	
	CompteEpargne(float solde, float tauxInteret, Banque banque, Client client) {
		super(solde, banque, client);
		setTauxInteret(tauxInteret);
	}
	
	// ------- Getters & setters -------

	public float getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(float tauxInteret) {
		this.tauxInteret = tauxInteret;
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
		float montantDepose = montant*getTauxInteret();
		super.setSolde(super.getSolde() + montantDepose);
	}
}
