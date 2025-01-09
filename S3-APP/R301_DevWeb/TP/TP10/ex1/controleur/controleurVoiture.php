<?php

require_once(WORKDIR . "/modele/voiture.php");
require_once(WORKDIR . "/vue/controleur/controleur.php");

class controleurVoiture extends Controleur
{
	protected static $objet = "voiture";
	protected static $cle = "immatriculation";

	public static function lireVoitures() {
		$titre = "toutes les voitures";	
		$tab_o = Voiture::getAll();
		$table = "Voiture";
		$cle = "immatriculation";
	}

	public static function lireVoiture() {
		$titre = "une voiture";
		$table = "Voiture";
		$cle = "immatriculation";
		$message = "La voiture immatriculée";
		
		if (!isset($_GET["immatriculation"])) $_GET["immatriculation"] = "AC161AB";
		$immat = $_GET["immatriculation"];
		$obj = Voiture::getObjetById($immat);
	}
}

?>