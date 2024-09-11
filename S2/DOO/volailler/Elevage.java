package volailler;

import java.util.ArrayList;

public class Elevage {

	ArrayList<Volaille> sesVolailles;
	
	Elevage() {
		sesVolailles = new ArrayList<Volaille>();
	}

	// getters & setters
	
	public ArrayList<Volaille> getSesVolailles() {
		return sesVolailles;
	}

	public void setSesVolailles(ArrayList<Volaille> sesVolailles) {
		this.sesVolailles = sesVolailles;
	}
	
	// methods
	
	public void ajouter(Volaille volaille) {
		sesVolailles.add(volaille);
	}
	
	public void changePoids(int numeroID, double poids) {
		for (Volaille volaille : sesVolailles) {
			if (volaille.getNumeroID() == numeroID) {
				volaille.setPoids(poids);
			}
		}
	}
	
	public Volaille searchVolaille(int numeroID) {
		for (Volaille volaille : sesVolailles) {
			if (volaille.getNumeroID() == numeroID) {
				return volaille;
			}
		}
		return null;
	}
	
	public void ecrire() {
		System.out.println("Affichage de l'état de l'élevage :");
		for (Volaille volaille : sesVolailles) {
			System.out.println(volaille);
		}
	}
	
	public ArrayList<Volaille> afficherBetesAAbattre() {
		ArrayList<Volaille> betesAAbbatre = new ArrayList<>();
		for (Volaille volaille : sesVolailles) {
			if (volaille.estAAbbatre())
				betesAAbbatre.add(volaille);
		}
		return betesAAbbatre;
	}
	
	public ArrayList<Volaille> envoyerALAbattoir() {
		ArrayList<Volaille> betesAAbbatre = afficherBetesAAbattre();
		for (Volaille volaille : betesAAbbatre) {
			sesVolailles.remove(volaille);
		}
		return betesAAbbatre;
	}
	
	public double evaluerBetesAAbattre() {
		ArrayList<Volaille> betesAAbbatre = afficherBetesAAbattre();
		double valeurTT = 0;
		for (Volaille volaille : betesAAbbatre) {
			valeurTT += volaille.getPrix();
		}
		return valeurTT;
	}
	
}
