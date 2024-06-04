
public abstract class Document {
	
	protected static int lastID = 1;
	protected int numDoc;
	protected String titreDoc;
	protected String description;
	
	public Document(String titreDoc, String description) {
		super();
		// attribution automatique de l'identifiant
		this.numDoc = lastID++;
		this.titreDoc = titreDoc;
		this.description = description;
	}
	
	// ----------------- Getters / Setters --------------------

	public int getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(int numDoc) {
		if (numDoc > 0)
			this.numDoc = numDoc;
		else
		System.out.println("Erreur : tentative d'attribution d'un "
				+ "identifiant invalide (< 1).");
	}

	public String getTitreDoc() {
		return titreDoc;
	}

	public void setTitreDoc(String titreDoc) {
		this.titreDoc = titreDoc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	// ----------------- Methods --------------------
	
	
}
