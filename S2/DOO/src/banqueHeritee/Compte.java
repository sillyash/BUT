package banqueHeritee;

public abstract class Compte {
	
	protected Banque saBanque;
	protected Client sonClient;
	
	protected int numero;
	protected float solde;
	
	// to keep track of account numbers
	protected static int lastNum = 1;
	
	
	// ----- Constructors -----
	
	public Compte(float solde) {
		setNumero(lastNum++);
		setSolde(solde);
	}
	
	public Compte(float solde, float seuil) {
		setNumero(lastNum++);
	}
	
	public Compte(float solde, Client client) {
		setNumero(lastNum++);
		setSolde(solde);
		setClient(client);
	}

	public Compte(float solde, Banque banque) {
		setNumero(lastNum++);
		setSolde(solde);
		setBanque(banque);
	}
	
	public Compte(float solde, Banque banque, Client client) {
		setNumero(lastNum++);
		setSolde(solde);
		setClient(client);;
		setBanque(banque);
	}
	
	// --- Getters & Setters ---
	
	public void setBanque(Banque banque) {
		this.saBanque = banque;
	}
	
	public Banque getBanque() {
		return saBanque;
	}
	
	public void setClient(Client client) {
		this.sonClient = client;
	}

	public Client getClient() {
		return sonClient;
	}
	
	public float getSolde() {
		return solde;
	}
	
	public void setSolde(float solde) {	
		this.solde = solde;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public static int getLastNum() {
		return lastNum;
	}

	public static void setLastNum(int lastNum) {
		Compte.lastNum = lastNum;
	}
	
	// ------ Methodes ------
	
	public void debiter(float montant) {
		if (this.getSolde() < montant) {
			System.out.println("Erreur : le solde du compte est "
			+ "inférieur au montant débiteur.");
		}
		else setSolde(getSolde() - montant);
	}
	
}
