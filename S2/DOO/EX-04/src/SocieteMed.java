import vehicules.*;
import java.util.ArrayList;
import java.util.Collection;

public class SocieteMed {
	
	protected ArrayList<Vehicule> sesVehicules = new ArrayList<>();
	protected String nom;
	
	public SocieteMed(String nom) {
		this.nom = nom;
	}
	
	public void addVehicule(Vehicule vehicule) {
		this.sesVehicules.add(vehicule);
	}

	public static void main(String[] args) {
		
		SocieteMed testMed = new SocieteMed("La Croix Rouge");
		
		testMed.addVehicule((Vehicule) new PosteCommandement());
		testMed.addVehicule(new PosteCommandement());
		
		testMed.addVehicule(new VehiculeSecours());
		addVehicule(new VehiculeSecours());
		addVehicule(new VehiculeSecours());
		
		addVehicule(new VehiculeAssistance());
		addVehicule(new VehiculeAssistance());
		
	}

}
