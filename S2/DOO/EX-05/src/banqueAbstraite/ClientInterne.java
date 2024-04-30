package banqueAbstraite;

public class ClientInterne extends Salarie {
	protected Compte compte;
	
	public Compte getCompte() {
		return this.compte;
	}
	
	public void verserSalaire() {
		getCompte().crediter(getSalaire());
	}
}
