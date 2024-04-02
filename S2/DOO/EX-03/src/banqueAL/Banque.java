package banqueAL;

import java.util.ArrayList;

public class Banque {

	private String nom;
	
	private ArrayList<CompteCourant> comptesC;
	private ArrayList<CompteEpargne> comptesE;
	private ArrayList<Client> clients;
	
	private static float tauxRemuneration = 0.01f;
	
	// --------- Constructor(s) ---------
	
	Banque (String nom) {
		this.nom = nom;
		clients = new ArrayList<>();
		comptesC = new ArrayList<>();
		comptesE = new ArrayList<>();
	}
	
	Banque (String nom, Client client) {
		this.nom = nom;
		clients = new ArrayList<>();
		comptesC = new ArrayList<>();
		comptesE = new ArrayList<>();
		clients.add(client);
		client.setBanque(this);
	}
	
	// -------- Getters and Setters --------
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
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
	
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	static float getTauxRemuneration() {
		return tauxRemuneration;
	}
	
	static void setTauxRemuneration(float taux) {
		tauxRemuneration = taux;
	}
	
	// ----------- Methods -----------
	
	public String toString () {
		  String s = "";
		  
		  s += "Nom : ";
		  s += nom;
		  
		  s += "\t| Nombre de comptes : ";
		  s += comptesC.size() + comptesE.size();
		  
		  s += "\t| Nombre de clients : ";
		  s += clients.size();
		  
		  return s;
	}
	
	public void ajouterClient(Client client) {
		this.clients.add(client);
		client.setBanque(this);
	}
	
	public void ajouterCompteC(CompteCourant compte) {
		this.comptesC.add(compte);
	}
	
	public void ajouterCompteE(CompteEpargne compte) {
		this.comptesE.add(compte);
	}
	
	public Client searchClientWithName(String nomCherche) {
		for (Client client : this.clients) {
			if (client.getNom().equals(nomCherche)) {
				return client;
			}
		}
		return null;
	}
	
	public void searchAndPrintDetails(String nomCherche) {
		Client client = searchClientWithName(nomCherche);
		if (client != null) {
			System.out.println(client);
			for (CompteCourant compte : client.getComptesC()) {
				System.out.println(compte);
			}
			for (CompteEpargne compte : client.getComptesE()) {
				System.out.println(compte);
			}
		}
		else System.out.println("Client introuvable. Vérifiez l'orthographe du nom.");
	}
	
	public void printComptes() {
		System.out.println(this.nom + " : affichage de tous les comptes.");
		for (CompteCourant compte : this.comptesC) {
			System.out.println(compte);
		}
		
		for (CompteEpargne compte : this.comptesE) {
			System.out.println(compte);
		}
	}
	
	public Client searchClientWithAccountNumber(int accountNum) {
		for (CompteCourant compte : this.comptesC) {
			if (compte.getNumero() == accountNum) {
				return compte.getClient();
			}
		}
		return null;
	}
	
	public void searchAndPrintDetails(int accountNum) {
		Client client = searchClientWithAccountNumber(accountNum);
		if (client != null) {
			System.out.println("Compte trouvé ! " + client.getNom());
			for (CompteCourant compte : client.getComptesC()) {
				System.out.println(compte);
			}
			for (CompteEpargne compte : client.getComptesE()) {
				System.out.println(compte);
			}
		}
		else System.out.println("Client introuvable. Vérifiez le numéro de compte.");
	}
	
	public static void main(String args[]) {
		// Banques
		Banque banque1 = new Banque ("Caisse d'Épargne");
		Banque banque2 = new Banque ("Crédit Lyonnais");
		
		// Clients
		Client client1 = new Client ("Nicolas Vouilloux", "7 résidence de la Roche 91340 Ollainville");
		Client client2 = new Client ("Emma Escoffier", "3 rue du Havre, 64160 Précy-sur-Oise");
		Client client3 = new Client ("Ashley Merienne", "5 rue Jean-Jauris, 91190 Gif-sur-Yvette");
		
		// ComptesC
		CompteCourant c1 = new CompteCourant (554.0f, 200.0f);
		CompteCourant c2 = new CompteCourant (236.0f);
		CompteCourant c3 = new CompteCourant (889.0f);
		CompteCourant c4 = new CompteCourant (400.0f);
		
		// ComptesE
		CompteEpargne e1 = new CompteEpargne (723.0f, 0.05f);
		CompteEpargne e2 = new CompteEpargne (541.0f, 0.10f);
		CompteEpargne e3 = new CompteEpargne (984.0f, 0.03f);
		
		// Affectation de comptes
		System.out.println("Test de Client.ajouterCompte...");
		client1.ajouterCompteC(c1);
		client1.ajouterCompteC(c2);
		client1.ajouterCompteE(e1);
		
		client2.ajouterCompteC(c3);
		client2.ajouterCompteE(e2);
		
		client3.ajouterCompteC(c4);
		client3.ajouterCompteE(e3);
		
		// Affectation de clients
		System.out.println("Test de Banque.ajouterClient...");
		banque1.ajouterClient(client1);
		banque1.ajouterClient(client3);
		banque2.ajouterClient(client2);
		System.out.println();
		
		// Affichage banques
		System.out.println("Test de print(Banque)...");
		System.out.println(banque1);
		System.out.println(banque2);
		System.out.println();
		
		// Affichage clients
		System.out.println("Test de print(Client)...");
		System.out.println(client1);
		System.out.println(client2);
		System.out.println(client3);
		System.out.println();
		
		// Affichage comptesC
		System.out.println("Test de print(CompteC)...");
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println();
		
		// Affichage comptesE
		System.out.println("Test de print(CompteE)...");
		System.out.println(e1);
		System.out.println(e2);
		System.out.println(e3);
		System.out.println();
		
		// Test débit
		System.out.println("Test de debiter...");
		c3.debiter(100f);
		c3.debiter(900f); // test erreur 
		System.out.println();
		
		// Test crédit
		System.out.println("Test de credit...");
		c2.crediter(200f);
		c2.setSolde(1500f); // test erreur
		System.out.println();
		
		// Test recherche1
		System.out.println("Test de searchAndPrintDetails(nom)...");
		banque1.searchAndPrintDetails("Emma Escoffier"); // test erreur
		System.out.println();
		banque1.searchAndPrintDetails("Nicolas Vouilloux");
		System.out.println();
		
		// Test affichage comptes banque
		System.out.println("Test de Banque.printComptes...");
		banque1.printComptes();
		System.out.println();
		
		// Test affichage comptes client
		System.out.println("Test de Client.printComptes...");
		client1.afficherComptes();
		System.out.println();
		
		// Test recherche2
		System.out.println("Test de searchAndPrintDetails(num)...");
		banque1.searchAndPrintDetails(1);
		System.out.println();
		
		// Test tauxRemuneration
		System.out.println("Test de tauxRemuneration...");
		float ancienSolde = c2.getSolde();
		float montantDepose = 200f;
		c2.crediter(montantDepose);
		float montantCredite = c2.getSolde() - ancienSolde;
		
		System.out.println("Ancien solde : " + ancienSolde);
		System.out.println("Montant envoyé : " + montantDepose);
		System.out.println("Montant crédité : " + montantCredite);
		System.out.print("Taux de rémunération : ");
		System.out.print(Banque.getTauxRemuneration()*100 + "%\n");
		
		Banque.setTauxRemuneration(0.02f);
		
		ancienSolde = c2.getSolde();
		montantDepose = 100f;
		c2.crediter(montantDepose);
		montantCredite = c2.getSolde() - ancienSolde;
		
		System.out.println();
		System.out.println("Ancien solde : " + ancienSolde);
		System.out.println("Montant envoyé : " + montantDepose);
		System.out.println("Montant crédité : " + montantCredite);
		System.out.print("Taux de rémunération : ");
		System.out.print(Banque.getTauxRemuneration()*100 + "%\n");
	}
}