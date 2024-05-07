
public class Vendeur extends Commercial {
	
	protected static final float PART_SALAIRE = 0.2f;
	protected static final float BONUS_SALAIRE = 200f;

	public Vendeur(String nom, String prenom, int age, int anneeAnciennete, 
			boolean employeARisque, float chiffreAffairesMensuel) {
		super(nom, prenom, age, anneeAnciennete, employeARisque, chiffreAffairesMensuel, BONUS_SALAIRE, PART_SALAIRE);
	}

}
