<?php

require_once("modele.php");

class Voiture extends Modele
{
	protected static $objet = "voiture";
	protected static $cle = "immatriculation";

	protected string $marque;
	protected string $couleur;
	protected string $immatriculation;

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