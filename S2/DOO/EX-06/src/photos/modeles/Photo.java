package photos.modeles;

import java.io.File;

public class Photo implements Comparable<Photo> {
	
	protected File photo;
	protected double bytes;
	protected String nomPays;
	protected int anneePrise;
	protected String nomFichier;
	protected String commentaire;

	public Photo(String nomFichier, String nomPays, int anneePrise) {
		this.photo = new File(nomFichier);
        if (this.photo.exists()) { 
        	// Récupérer la taille du fichier 
        	this.bytes = this.photo.length();
        }
        this.anneePrise = anneePrise;
        this.nomPays = nomPays;
        this.nomFichier = nomFichier;
        this.commentaire = "";
	}
	
	public Photo(String nomFichier, String nomPays, int anneePrise, String commentaire) {
		this.photo = new File(nomFichier);
        if (this.photo.exists()) { 
        	// Récupérer la taille du fichier 
        	this.bytes = this.photo.length();
        }
        this.anneePrise = anneePrise;
        this.nomPays = nomPays;
        this.nomFichier = nomFichier;
        this.commentaire = commentaire;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = new File(photo);
	}

	public double getBytes() {
		return bytes;
	}
	
	public String getNomPays() {
		return nomPays;
	}

	public void setNomPays(String nomPays) {
		this.nomPays = nomPays;
	}

	public int getAnneePrise() {
		return anneePrise;
	}

	public void setAnneePrise(int anneePrise) {
		this.anneePrise = anneePrise;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public String getCommentaire() {
		return this.commentaire;
	}
	
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	@Override public int compareTo(Photo p) {
		if (this.bytes == p.bytes)return 0;
		if (this.bytes > p.bytes) return 1;
		else return -1;
	}
	
	public String toString() {
		String s = "";
		
		s += getNomFichier();
		s += " \tTaille : ";
		s += getBytes();
		s += " octets";
		s += " \tPays : ";
		s += getNomPays();
		s += " \tPrise en ";
		s += getAnneePrise();
		s += " \tCommentaire : ";
		s += getCommentaire();
		
		return s;
	}
	
	public static void main(String args[]) {
		Photo p1 = new Photo("car-eepy.png", "Etats-Unis", 2012, "Un chaton dort");
		Photo p2 = new Photo("neco.jpg", "Allemagne", 2009, "Un Chat boit de l'eau");
		
		System.out.println("p1 : " + p1);
		System.out.println("p2 : " + p2);
		System.out.println();
		
		System.out.println("p1.compareTo(p2) = " + p1.compareTo(p2));
		System.out.println("p2.compareTo(p1) = " + p2.compareTo(p1));
		System.out.println("p1.compareTo(p1) = " + p1.compareTo(p1));
	}
	
	
}
