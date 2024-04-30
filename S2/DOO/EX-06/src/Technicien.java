
public class Technicien extends Employe {
	
	protected int unitesProduitesMensuel;
	protected static final int multiplicateurSalaire = 10;
	
	public Technicien(String nom, String prenom, int age, int anneeAnciennete, int unitesProduitesMensuel) {
		super(nom, prenom, age, anneeAnciennete);
		this.unitesProduitesMensuel = unitesProduitesMensuel;
	}
	

	@Override
	protected float calculerSalaire() {
		return getUnitesProduitesMensuel() * multiplicateurSalaire;
	}

	public int getUnitesProduitesMensuel() {
		return unitesProduitesMensuel;
	}

	public void setUnitesProduitesMensuel(int unitesProduitesMensuel) {
		this.unitesProduitesMensuel = unitesProduitesMensuel;
	}
}
