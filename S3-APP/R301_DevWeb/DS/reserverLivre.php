<?php

// https://.../routeur.php?controleur=ControleurReservation&action=reserverLivre&id=999
// routeur.php appelle (automatiquement via le $_GET) :

ControleurReservation::reserverLivre();

// controllers/ControleurReservation.php

public static function reserverLivre() {
	include(VUE . 'debut.php');
	
	$livre = $_GET['id'];
	$user = $_SESSION['userID'];

	include(MODELE . static::$objet);
	$succes = Reservation::reserver($livre, $user);

	if ($succes) {
		echo "<p>Votre réservation a bien été effectuée</p>";
	} else {
		echo "<p>Erreur : la réservation n'a pas réussi.</p>";
	}

	include(VUE . 'fin.php');
}

// Modele/Reservation.php

public static function reserver($idLivre, $idUtilisateur) {
	$table = static::$objet;
	$req = "INSERT INTO $table (id_livre, id_utilisateur, date_Reservation) VALUES (:livre, :user, SYSDATE)";

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