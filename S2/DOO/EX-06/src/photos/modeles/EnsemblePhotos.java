package photos.modeles;

import java.util.ArrayList;

public interface EnsemblePhotos {
	
	public void addPhoto(Photo photo);
	public void sortPhotos();
	public ArrayList<Photo> rechercheMotCle(String motCle);
	
}
