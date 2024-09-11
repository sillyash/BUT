package banqueAbstraite;
import java.util.ArrayList;

public class TestSalarie {
	public static void main(String args[]) {
		
		ArrayList<Salarie> salaries = new ArrayList<>();
		
		salaries.add(new ClientInterne((float) 1784.26, new CompteCourant((float) 320.2)));
		salaries.add(new ClientInterne((float) 2254.0, new CompteCourant((float) 120.45)));
		salaries.add(new Employe((float) 1645.5));
		salaries.add(new Employe((float) 1223.65));
		
		for (Salarie salarie : salaries) {
			if (salarie.getClass().getName().equals("banqueAbstraite.ClientInterne")) {
				System.out.println("Solde avant versement du salaire : \t" + ((ClientInterne) salarie).getCompte().getSolde());
				salarie.verserSalaire();
				System.out.println("Solde après versement du salaire : \t" + ((ClientInterne) salarie).getCompte().getSolde());
			}
			else salarie.verserSalaire();
			System.out.println();
		}
	}
}
