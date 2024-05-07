import java.util.ArrayList;

public class Salaires {
	
	private static ArrayList<Employe> employes = new ArrayList<>();
	
	public static void main(String args[]) {
		
		employes.add(new Manutentionaire("Escoffier", "Emma", 23, 4, false, 140));
		employes.add(new Technicien("Vouilloux", "Nicolas", 19, 1, true, 254));
		
		employes.add(new Vendeur("Fazer", "Naomie", 18, 0, true, 5200f));
		employes.add(new Representant("Merienne", "Ashley", 18, 0, false, 8456.74f));
		
		for (Employe employe : employes) {
			System.out.println(employe);
		}
		
	}
	
}
