
public class Vendeur extends Commercial {
	
	protected static final int PART_SALAIRE = 0.2f;
	protected static final int BONUS_SALAIRE = 200;

	public Vendeur(String nom, String prenom, int age, int anneeAnciennete, float chiffreAffairesMensuel) {
		super(nom, prenom, age, anneeAnciennete, chiffreAffairesMensuel, BONUS_SALAIRE, PART_SALAIRE);
	}

}
