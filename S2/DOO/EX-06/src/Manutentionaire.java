
public class Manutentionaire extends Employe {
	
	protected int heuresTravailleesMensuel;
	protected static final int multiplicateurSalaire = 15;
	
	public Manutentionaire(String nom, String prenom, int age, int anneeAnciennete, boolean employeARisque, int unitesProduitesMensuel) {
		super(nom, prenom, age, anneeAnciennete, employeARisque);
		this.heuresTravailleesMensuel = unitesProduitesMensuel;
	}

	@Override
	protected float calculerSalaire() {
		if (this.employeARisque)
			return getHeuresTravailleesMensuel() * multiplicateurSalaire + BONUS_RISQUE;
		else return getHeuresTravailleesMensuel() * multiplicateurSalaire;
	}

	public int getHeuresTravailleesMensuel() {
		return heuresTravailleesMensuel;
	}

	public void setHeuresTravailleesMensuel(int unitesProduitesMensuel) {
		this.heuresTravailleesMensuel = unitesProduitesMensuel;
	}

	@Override
	public String toString() {
		String s = "";
		
		s += super.ToString() + ", ";
		s += "heures travaillées : " + getHeuresTravailleesMensuel();
		s += ", salaire : " + calculerSalaire();
		
		return s;
	}
}
