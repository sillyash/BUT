import vehicules.*;
import vehicules.engins.*;
import java.util.ArrayList;
import java.util.Collection;

public class SocieteMed {
	
	// ---------- Attributs ----------
	
	protected ArrayList<Vehicule> sesVehicules = new ArrayList<>();
	protected String nom;
	
	// -------- Constructeurs --------
	
	public SocieteMed() {
		this.nom = "";
	}
	
	public SocieteMed(String nom) {
		this.nom = nom;
	}
	
	// ----------- Méthodes -----------
	
	public void ajouterVehicule(Vehicule vehicule) {
		this.sesVehicules.add(vehicule);
	}
	
	public float calculerCout() {
		int coutTT = 0;
		
		for (Vehicule vehicule : this.sesVehicules) {
			coutTT += vehicule.getCout();
		}
		
		return coutTT;
	}
	
	public String toString() {
		String s = "";
		s += "Société Médicale : ";
		s += this.nom;
		s += "\n";
		for (Vehicule vehicule : sesVehicules) {
			s += vehicule.toString() + "\n";
		}
		return s;
	}
	
	// ------------- Main -------------

	public static void main(String[] args) {
		SocieteMed s = new SocieteMed();
	 	
	 	// Créer les véhicules
	 	PC pc1 = new PC ();
	 	PC pc2 = new PC ();
	 	VS vs1 = new VS ();
	 	VS vs2 = new VS ();
	 	VS vs3 = new VS ();
	 	VA va1 = new VA ();
	 	VA va2 = new VA ();
	 	
	 	// Ajouter les véhicules à la société
	 	s.ajouterVehicule (pc1);    	
	 	s.ajouterVehicule (pc2);    	
	 	s.ajouterVehicule (vs1);    	
	 	s.ajouterVehicule (vs2);    	
	 	s.ajouterVehicule (vs3);    	
	 	s.ajouterVehicule (va1);    	
	 	s.ajouterVehicule (va2);    	
	 	
	 	// Affecter qq véhicules à une intervention
	 	int numeroIntervention = 17 ;
	 	int distance = 35 ;
	 	pc1.affecterIntervention (numeroIntervention, distance);
	 	vs1.affecterIntervention (numeroIntervention, distance);
	 	vs2.affecterIntervention (numeroIntervention, distance);
	 	va2.affecterIntervention (numeroIntervention, distance);
	 	 
	 	// Lors de l'intervention, embarquer quelques personnes
	 	vs1.embarquer("Dupont");
	 	vs2.embarquer("Martin", "Durand");
	 	// Afficher les informations sur tous les véhicules
	 	System.out.println (s);
	 	
	 	// Calculer le cout de l'intervention
	 	System.out.println ("Cout total de la journée : " + s.calculerCout()); 
	}

}
