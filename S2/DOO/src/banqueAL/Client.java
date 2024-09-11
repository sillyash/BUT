package banqueAL;

import java.util.ArrayList;

public class Client {

	private int numero;
	private static int lastNum;
	private String nom;
	private String adresse;
	
	private Banque saBanque;
	
	private ArrayList<CompteCourant> comptesC;
	private ArrayList<CompteEpargne> comptesE;

	// --------- Constructor(s) ---------
	
	Client (String nom, String adresse, CompteCourant compte) {
		this.numero = ++lastNum;
		this.nom = nom;
		this.adresse = adresse;
		compte.setClient(this);
		comptesC = new ArrayList<>();
		comptesE = new ArrayList<>();
		comptesC.add(compte);
	}
	
	Client (String nom, String adresse) {
		this.numero = ++lastNum;
		this.nom = nom;
		this.adresse = adresse;
		comptesE = new ArrayList<>();
		comptesC = new ArrayList<>();
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
		  s += comptesC.size() + comptesE.size();
		  
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
		
		for (CompteCourant compte : this.comptesC) {
			compte.setBanque(banque);
			banque.ajouterCompteC(compte);
		}
		
		for (CompteEpargne compte : this.comptesE) {
			compte.setBanque(banque);
			banque.ajouterCompteE(compte);
		}
	}
	
	public Banque getBanque() {
		return this.saBanque;
	}

	public ArrayList<CompteCourant> getComptesC() {
		return comptesC;
	}

	public void setComptesC(ArrayList<CompteCourant> comptes) {
		this.comptesC = comptes;
	}
	
	public ArrayList<CompteEpargne> getComptesE() {
		return comptesE;
	}

	public void setComptesE(ArrayList<CompteEpargne> comptes) {
		this.comptesE = comptes;
	}
	
	// ----------- Methods -----------
	
	public void ajouterCompteC(CompteCourant compte) {
		this.comptesC.add(compte);
		compte.setClient(this);
		compte.setBanque(this.saBanque);
	}
	
	public void ajouterCompteE(CompteEpargne compte) {
		this.comptesE.add(compte);
		compte.setClient(this);
		compte.setBanque(this.saBanque);
	}
	
	public void afficherComptes() {
		System.out.println(this.nom + " : affichage de tous les comptes.");
		for (CompteCourant compte : this.comptesC) {
			System.out.println(compte);
		}
		
		for (CompteEpargne compte : this.comptesE) {
			System.out.println(compte);
		}
	}

}