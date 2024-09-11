package banqueAbstraite;

import java.util.ArrayList;

public class Client {

	private int numero;
	private static int lastNum;
	private String nom;
	private String adresse;
	
	private Banque saBanque;
	
	private ArrayList<Compte> sesComptes;

	// --------- Constructor(s) ---------
	
	Client (String nom, String adresse, Compte compte) {
		this.numero = ++lastNum;
		this.nom = nom;
		this.adresse = adresse;
		compte.setClient(this);
		sesComptes = new ArrayList<>();
		sesComptes.add(compte);
	}
	
	Client (String nom, String adresse) {
		this.numero = ++lastNum;
		this.nom = nom;
		this.adresse = adresse;
		sesComptes = new ArrayList<>();
	}
	
	// -------- Getters and Setters --------
	
	public String toString () {
		  String s = "";
		  
		  s += "Nom : ";
		  s += nom;
		  
		  s += "\t| Adresse : ";
		  s += adresse;
		  
		  s += "\t| Banque : ";
		  if (saBanque != null) {
			  s += saBanque.getNom();
		  }
		  else s += "aucune";
		  
		  s += "\t| Nombre de comptes : ";
		  s += sesComptes.size();
		  
		  return s ;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public void setBanque(Banque banque) {
		this.saBanque = banque;
		
		for (Compte compte : this.sesComptes) {
			compte.setBanque(banque);
			banque.ajouterCompte(compte);
		}
	}
	
	public Banque getBanque() {
		return this.saBanque;
	}
	
	public ArrayList<Compte> getComptes() {
		return sesComptes;
	}

	public void setComptes(ArrayList<Compte> comptes) {
		this.sesComptes = comptes;
	}
	
	// ----------- Methods -----------
	
	public void ajouterCompte(Compte compte) {
		this.sesComptes.add(compte);
		compte.setClient(this);
		compte.setBanque(this.saBanque);
	}
	
	public void afficherComptes() {
		System.out.println(this.nom + " : affichage de tous les comptes.");
		for (Compte compte : this.sesComptes) {
			System.out.println(compte);
		}
	}

}