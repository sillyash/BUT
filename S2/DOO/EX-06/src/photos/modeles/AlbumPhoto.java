package photos.modeles;

import java.util.ArrayList;
import java.util.Collections;

public class AlbumPhoto implements EnsemblePhotos {
	
	protected ArrayList<Photo> sesPhotos;

	public AlbumPhoto(ArrayList<Photo> photos) {
		this.sesPhotos = photos;
	}
	
	public AlbumPhoto(Photo photo) {
		this.sesPhotos = new ArrayList<Photo>();
		this.addPhoto(photo);
	}

	@Override
	public void addPhoto(Photo photo) {
		this.sesPhotos.add(photo);
	}

	@Override
	public void sortPhotos() {
		Collections.sort(this.sesPhotos);
	}

	@Override
	public ArrayList<Photo> rechercheMotCle(String motCle) {
		ArrayList<Photo> resultatRecherche = new ArrayList<Photo>();
		
		motCle = motCle.toUpperCase();
		
		for (Photo photo : this.sesPhotos)
		{
			if (photo.getCommentaire().toUpperCase().contains(motCle))
				resultatRecherche.add(photo);
		}
		return resultatRecherche;
	}
	
	public String toString() {
		String s = "";
		
		s += "Album de ";
		s += this.sesPhotos.size();
		s += " photos :\n";
		
		for (Photo photo : this.sesPhotos) {
			s += this.sesPhotos.indexOf(photo);
			s += " ";
			s += photo;
			s += "\n";
		}
		
		return s;
	}
	
	public static void main(String args[]) {
		
		Photo chat1 = new Photo("car-eepy.png", "Etats-Unis", 2012, "Un chaton dort");
		Photo chat2 = new Photo("neco.jpg", "Allemagne", 2009, "Un Chat boit de l'eau");
		Photo doo = new Photo("s1256.jpg", "Belgique", 2024, "Une tragédie en une image");
		Photo omori = new Photo("omori.jpg", "Allemagne", 2020, "Un très bon jeu vidéo");
		
		AlbumPhoto album = new AlbumPhoto(chat1);
		
		album.addPhoto(omori);
		album.addPhoto(doo);
		album.addPhoto(chat2);
		
		System.out.println("Affichage de l'album avant tri...");
		System.out.println(album);
		System.out.println();
		
		album.sortPhotos();
		
		System.out.println("Affichage de l'album après tri...");
		System.out.println(album);
		System.out.println();
		
		AlbumPhoto chats = new AlbumPhoto(album.rechercheMotCle("chat"));
		System.out.println("Résultat de la recherche \"chat\" :");
		System.out.println(chats);
	}

}
