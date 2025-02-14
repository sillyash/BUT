<?php

// https://.../routeur.php?controleur=ControleurLivre&action=getLivreByTitre&titre=titreDuLivre
// routeur.php appelle (automatiquement via le $_GET) :

ControleurLivre::getLivrebyTitre();

// controllers/ControleurLivre.php

public static function getLivrebyTitre() {
	include(VUE . 'debut.php');
	
	$titre = $_GET['titre'];

	include(MODELE . static::$objet);
	$objet = Livre::getLivreByTitre($titre);

	include(VUE . 'lesObjets.php'); // Il peut y avoir plusieurs livres
	include(VUE . 'fin.php');
}

// models/Livre.php

public static function getLivreByNom($titre) {
	$table = ucfirst(static::$objet);
	$req = "SELECT * FROM $table WHERE titre = :titre";

	$stmt = Connexion::pdo()->query($req);
	$stmt->bindValue(':titre', $titre, PDO::PARAM_STR);

	try {
		$stmt->execute();
		$data = $stmt->fetchAll(PDO::FETCH_ASSOC);
	} catch (PDOException $e) {
		$e->getMessage();
	}

	return $data;
}

?>
