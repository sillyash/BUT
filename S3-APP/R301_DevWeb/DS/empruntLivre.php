<?php

// https://.../routeur.php?controleur=ControleurEmprunt&action=emprunterLivre&id=999
// routeur.php appelle (automatiquement via le $_GET) :

ControleurEmprunt::emprunterLivre();

// controllers/ControleurEmprunt.php

public static function emprunterLivre() {
	include(VUE . 'debut.php');
	
	$livre = $_GET['id'];
	$user = $_SESSION['userID'];

	include(MODELE . static::$objet);
	$succes = Emprunt::emprunter($livre, $user);

	if ($succes) {
		echo "<p>Votre emprunt a bien été effectué</p>";
	} else {
		echo "<p>Erreur : l'emprunt n'a pas réussi : le livre n'est pas disponible.</p>";
		echo "<a href='routeur.php?controleur=ControleurReservation&action=reserverLivre&id=$livre'>Réserver</a>"
	}

	include(VUE . 'fin.php');
}

// Modele/Emprunt.php

public static function emprunter($idLivre, $idUtilisateur) {
	$table = static::$objet;
	$req = "INSERT INTO $table (id_livre, id_utilisateur, date_emprunt) VALUES (:livre, :user, SYSDATE)";

	$stmt = Connexion::pdo()->query($req);
	$stmt->bindValue(':livre', $idLivre, PDO::PARAM_INT);
	$stmt->bindValue(':user', $idUtilisateur, PDO::PARAM_INT);

	try {
		$stmt->execute();
	} catch (PDOException $e) {
		$e->getMessage();
		return false;
	}

	return true;
}

?>