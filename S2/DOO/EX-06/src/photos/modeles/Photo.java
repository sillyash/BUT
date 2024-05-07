package photos.modeles;

import java.io.File;

public class Photo {
	
	protected File photo;
	protected double bytes;

	public Photo(String photo) {
		this.photo = new File(photo);
        if (this.photo.exists()) { 
        	// Récupérer la taille du fichier 
        	this.bytes = this.photo.length();
        }
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public double getBytes() {
		return bytes;
	}

	public void setBytes(double bytes) {
		this.bytes = bytes;
	}

}
