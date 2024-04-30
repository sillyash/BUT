package banqueAbstraite;

public class Employe extends Salarie {

	public Employe(float salaire) {
		super(salaire);
	}
	
	@Override
	public void verserSalaire() {
		System.out.println("Versement d'un salaire d'un montant de " + getSalaire() + "€...");
	}
}
