
public class Technicien extends Employe {
	
	protected int unitesProduitesMensuel;
	protected static final int multiplicateurSalaire = 10;
	
	public Technicien(String nom, String prenom, int age, int anneeAnciennete, boolean employeARisque, int unitesProduitesMensuel) {
		super(nom, prenom, age, anneeAnciennete, employeARisque);
		this.unitesProduitesMensuel = unitesProduitesMensuel;
	}
	

	@Override
	protected float calculerSalaire() {
		if (this.employeARisque)
			return getUnitesProduitesMensuel() * multiplicateurSalaire + BONUS_RISQUE;
		else return getUnitesProduitesMensuel() * multiplicateurSalaire;
	}

	public int getUnitesProduitesMensuel() {
		return unitesProduitesMensuel;
	}

	public void setUnitesProduitesMensuel(int unitesProduitesMensuel) {
		this.unitesProduitesMensuel = unitesProduitesMensuel;
	}

	@Override
	public String toString() {
		String s = "";
		
		s += super.ToString() + ", ";
		s += "unités produites : " + getUnitesProduitesMensuel();
		s += ", salaire : " + calculerSalaire();
		
		return s;
	}
}
