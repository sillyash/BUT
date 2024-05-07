
public class Representant extends Commercial {

	protected static final float PART_SALAIRE = 0.3f;
	protected static final float BONUS_SALAIRE = 500f;
	
	public Representant(String nom, String prenom, int age, int anneeAnciennete, 
			boolean employeARisque, float chiffreAffairesMensuel) {
		super(nom, prenom, age, anneeAnciennete, employeARisque, chiffreAffairesMensuel, BONUS_SALAIRE, PART_SALAIRE);
	}

}
