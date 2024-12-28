<?php

require_once(WORKDIR."/modele/voiture.php");

class controleurVoiture 
{
	public static function lireVoitures() {
		$titre = "toutes les voitures";	
		$tab_o = Voiture::getAll();
		$table = "Voiture";
		$cle = "immatriculation";

		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");
		include(WORKDIR."/vue/lesObjets.php");
		include(WORKDIR."/vue/fin.html");
	}

	public static function lireVoiture() {
		$titre = "une voiture";
		$table = "Voiture";
		$cle = "immatriculation";
		$message = "La voiture immatriculée";
		
		if (!isset($_GET["immatriculation"])) $_GET["immatriculation"] = "AC161AB";
		$immat = $_GET["immatriculation"];
		$obj = Voiture::getObjetById($immat);

		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");

		if (!$obj) {
			include(WORKDIR."/vue/erreur.php");
		} else {
			include(WORKDIR."/vue/unObjet.php");
		}
		
		include(WORKDIR."/vue/fin.html");
	}
}

?>