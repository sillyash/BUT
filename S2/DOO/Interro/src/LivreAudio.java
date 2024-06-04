
public class LivreAudio extends Livre implements Comparable<LivreAudio> {

	protected int note;
	protected String url;
	
	public LivreAudio(String titreDoc, String description, String auteur, int note, String url) {
		super(titreDoc, description, auteur);
		this.note = note;
		this.url = url;
	}
	
	// ----------------- Getters / Setters --------------------

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		if (20 >= note && note >= 0)
			this.note = note;
		else
		System.out.println("Erreur : tentative d'attribution "
			+ "d'une note non comprise entre 0 et 20.");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	// ----------------- Methods --------------------
	
	@Override
	public boolean estDifficile() {
		return this.url.contains("difficile");
	}

	public int compareTo(LivreAudio livre) {
		if (this.note == livre.note) return 0;
		else if (this.note > livre.note) return 1;
		else return -1;
	}

	@Override
	public String toString() {
		return "LivreAudio [note=" + note + ", url=" + url + ", auteur=" + auteur + ", titreDoc=" + titreDoc
				+ ", description=" + description + "]";
	}
	
	
	
}
