
public abstract class Commercial extends Employe {
	
	protected float chiffreAffairesMensuel;
	protected int bonusMensuel;
	protected float partSalaire;
	
	public Commercial (String nom, String prenom, int age, int anneeAnciennete, float chiffreAffairesMensuel, int bonusMensuel, float partSalaire) {
		super(nom, prenom, age, anneeAnciennete);
		this.chiffreAffairesMensuel = chiffreAffairesMensuel;
		this.bonusMensuel = bonusMensuel;
		this.partSalaire = partSalaire;
	}
	
	@Override
	public float calculerSalaire() {
		return getPartSalaire() * getChiffreAffairesMensuel() + getBonusMensuel();
	}

	public float getChiffreAffairesMensuel() {
		return chiffreAffairesMensuel;
	}

	public void setChiffreAffairesMensuel(float chiffreAffairesMensuel) {
		this.chiffreAffairesMensuel = chiffreAffairesMensuel;
	}

	public int getBonusMensuel() {
		return bonusMensuel;
	}

	public void setBonusMensuel(int bonusMensuel) {
		this.bonusMensuel = bonusMensuel;
	}

	public float getPartSalaire() {
		return partSalaire;
	}

	public void setPartSalaire(float partSalaire) {
		this.partSalaire = partSalaire;
	}
		
}
