<?php

class Voiture
{
	private string $marque;
	private string $couleur;
	private string $immatriculation;

	public function __construct(
		string $marque = NULL,
		string $couleur = NULL,
		string $immatriculation = NULL
		) {

		if (!is_null($immatriculation))
		{
			$this->marque = $m;
			$this->couleur = $c;
			$this->immatriculation = $i;
		}
	}

	public function getMarque() { return $this->marque; }
	public function getCouleur() { return $this->couleur; }
	public function getImmatriculation() { return $this->immatriculation; }

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


	public function setMarque(string $m) { $this->marque = $m; }
	public function setCouleur(string $c) { $this->couleur = $c; }
	public function setImmatriculation(string $i) { $this->immatriculation = $i; }

	public function afficher() {
		$s = "Voiture <b>"
		. $this->getImmatriculation()
		. "</b> de marque <b>"
		. $this->getMarque()
		. "</b> et de couleur <b>"
		. $this->getCouleur()
		. "</b>. ";
		echo "$s";
	}

	public function __toString() {
		$s = "Voiture "
		. $this->getImmatriculation()
		. " de marque "
		. $this->getMarque()
		. " et de couleur "
		. $this->getCouleur()
		. ".";
		return $s;
	}
}

?>