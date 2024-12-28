<?php

include("modele.php");

class Utilisateur extends Modele
{
	protected string $login;
	protected string $mdp;
	protected string $nom;
	protected string $prenom;
	protected string $email;

	public static function getAllUsers() {
		$req = "SELECT * FROM Utilisateur;";
		$res = Connexion::pdo()->query($req);
		$res->setFetchmode(PDO::FETCH_CLASS, "utilisateur");
		$ppl = $res->fetchAll();

		return $ppl;
	}

	public static function getUtilisateurByLogin(string $l) {
		$req = "SELECT * FROM Utilisateur U 
						WHERE U.login = :login;";
		$prep = Connexion::pdo()->prepare($req);

		$args = array("login" => $l);
		try {
			$prep->execute($args);
		} catch (PDOException $e) {
			$e->getMessage();
		}

		$prep->setFetchmode(PDO::FETCH_CLASS, "utilisateur");
		$res = $prep->fetchAll();

		if (sizeof($res) >= 1) {
			$user = $res[0];
		} else {
			$user = null;
		}

		return $user;
	}

	public function afficher() {
		$s = "Utilisateur <b>"
		. $this->get("login")
		. "</b> ("
		. $this->get("prenom")
		. " "
		. $this->get("nom")
		. "), email = <b>"
		. $this->get("email")
		. "</b>";
		echo "$s";
	}

	public function __toString() {
		$s = "Utilisateur <b>"
		. $this->get("login")
		. "</b> ("
		. $this->get("prenom")
		. " "
		. $this->get("nom")
		. "), email = <a href='mailto:"
		. $this->get("email")
		. "'>"
		. $this->get("email")
		. "</a>";
		return $s;
	}
}

?>