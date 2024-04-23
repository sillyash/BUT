package vehicules.engins;

import vehicules.Vehicule;
import java.util.ArrayList;

public abstract class Engin extends Vehicule {
	
	// ---------- Attributs ----------
	
	protected int capacite;
	protected ArrayList<String> sesPatients;
	protected String hopitalDestination;
		
	// -------- Constructeurs --------
	
	public Engin() {
		super();
		this.sesPatients = new ArrayList<>();
	}
		
	// ----------- Méthodes -----------
	
	public void embarquer (String nomPersonne1) {
		sesPatients.add(nomPersonne1);
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Capacité : ";
		s += this.capacite;
		s += " |\tPatients : ";
		for (int i=0; i<this.sesPatients.size(); i++) {
			s += this.sesPatients.get(i);
			if (i != this.sesPatients.size() - 1) s += ", ";
		}
		s += " |\tHôpital de destination : ";
		s += this.hopitalDestination;
		return s;
	}
}
