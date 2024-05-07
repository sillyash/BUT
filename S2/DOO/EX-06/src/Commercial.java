
public abstract class Commercial extends Employe {
	
	protected float chiffreAffairesMensuel;
	protected float bonusMensuel;
	protected float partSalaire;
	
	public Commercial (String nom, String prenom, int age, int anneeAnciennete, boolean employeARisque, float chiffreAffairesMensuel,
			float bonusMensuel, float partSalaire) {
		super(nom, prenom, age, anneeAnciennete, employeARisque);
		this.chiffreAffairesMensuel = chiffreAffairesMensuel;
		this.bonusMensuel = bonusMensuel;
		this.partSalaire = partSalaire;
	}
	
	@Override
	public float calculerSalaire() {
		if (this.employeARisque)
			return getPartSalaire() * getChiffreAffairesMensuel() + getBonusMensuel() + BONUS_RISQUE;
		else return getPartSalaire() * getChiffreAffairesMensuel() + getBonusMensuel();
	}

	public float getChiffreAffairesMensuel() {
		return chiffreAffairesMensuel;
	}

	public void setChiffreAffairesMensuel(float chiffreAffairesMensuel) {
		this.chiffreAffairesMensuel = chiffreAffairesMensuel;
	}

	public float getBonusMensuel() {
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
	
	@Override
	public String toString() {
		String s = "";
		
		s += super.ToString() + ", ";
		s += "bonus : " + getBonusMensuel();
		s += ", part : " + Math.round(getPartSalaire()*100) + "% ";
		s += ", salaire : " + calculerSalaire();
		
		return s;
	}
		
}
