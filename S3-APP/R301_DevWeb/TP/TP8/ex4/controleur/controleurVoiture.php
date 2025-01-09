<?php

require_once(WORKDIR."/modele/voiture.php");

class controleurVoiture 
{
	public static function lireVoitures() {
		$titre = "toutes les voitures";	
		$tab_v = Voiture::getAll();

		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");
		include(WORKDIR."/vue/voiture/lesVoitures.php");
		include(WORKDIR."/vue/fin.html");
	}

	public static function lireVoiture() {
		$titre = "une voiture";
		
		if (!isset($_GET["immatriculation"])) $_GET["immatriculation"] = "AC161AB";
		$immat = $_GET["immatriculation"];
		$voiture = Voiture::getObjetById($immat);

		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");

		if (!$voiture) {
			include(WORKDIR."/vue/voiture/erreur.php");
		} else {
			include(WORKDIR."/vue/voiture/uneVoiture.php");
		}
		
		include(WORKDIR."/vue/fin.html");
	}
}

?>