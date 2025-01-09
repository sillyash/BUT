<?php

include("modele.php");

class Voiture extends Modele
{
	protected string $marque;
	protected string $couleur;
	protected string $immatriculation;

	public static function getAllVoitures() {
		$req = "SELECT * FROM Voiture;";
		$res = Connexion::pdo()->query($req);
		$res->setFetchmode(PDO::FETCH_CLASS, "voiture");
		$garage = $res->fetchAll();

		return $garage;
	}

	public static function getVoitureByImmat(string $i) {
		$req = "SELECT * FROM Voiture V 
						WHERE V.immatriculation = :immat;";
		$prep = Connexion::pdo()->prepare($req);

		$args = array("immat" => $i);
		try {
			$prep->execute($args);
		} catch (PDOException $e) {
			$e->getMessage();
		}

		$prep->setFetchmode(PDO::FETCH_CLASS, "voiture");
		$res = $prep->fetchAll();

		if (sizeof($res) >= 1) {
			$voiture = $res[0];
		} else {
			$voiture = null;
		}

		return $voiture;
	}

	public function afficher() {
		$s = "Voiture <b>"
		. $this->get("immatriculation")
		. "</b> de marque <b>"
		. $this->get("marque")
		. "</b> et de couleur <b>"
		. $this->get("couleur")
		. "</b>. ";
		echo "$s";
	}

	public function __toString() {
		$s = "Voiture "
		. $this->get("immatriculation")
		. " de marque "
		. $this->get("marque")
		. " et de couleur "
		. $this->get("couleur")
		. ".";
		return $s;
	}
}

?>