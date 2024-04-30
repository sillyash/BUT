package banqueAbstraite;

public class ClientInterne extends Salarie {
	
	protected Compte compte;
	
	public ClientInterne(float salaire, Compte compte) {
		super(salaire);
		this.compte = compte;
	}
	
	public Compte getCompte() {
		return this.compte;
	}
	
	public void verserSalaire() {
		getCompte().crediter(getSalaire());
	}
}
