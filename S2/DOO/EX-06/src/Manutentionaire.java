
public class Manutentionaire extends Employe {
	
	protected int heuresTravailleesMensuel;
	protected static final int multiplicateurSalaire = 15;
	
	public Manutentionaire(String nom, String prenom, int age, int anneeAnciennete, int unitesProduitesMensuel) {
		super(nom, prenom, age, anneeAnciennete);
		this.heuresTravailleesMensuel = unitesProduitesMensuel;
	}

	@Override
	protected float calculerSalaire() {
		return getUnitesProduitesMensuel() * multiplicateurSalaire;
	}

	public int getUnitesProduitesMensuel() {
		return heuresTravailleesMensuel;
	}

	public void setUnitesProduitesMensuel(int unitesProduitesMensuel) {
		this.heuresTravailleesMensuel = unitesProduitesMensuel;
	}
}
