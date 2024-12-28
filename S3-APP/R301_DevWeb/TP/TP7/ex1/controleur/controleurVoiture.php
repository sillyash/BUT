<?php

require_once(WORKDIR."/modele/voiture.php");

class controleurVoiture 
{
	public static function lireVoitures() {
		$titre = "toutes les voitures";	
		$tab_v = Voiture::getAllVoitures();

		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");
		include(WORKDIR."/vue/voiture/lesVoitures.php");
		include(WORKDIR."/vue/fin.html");
	}

	public static function lireVoiture() {
		$immat = $_GET["immatriculation"];

		$titre = "voiture immatriculée '$immat'";
		$voiture = Voiture::getVoitureByImmat($immat);

		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");
		include(WORKDIR."/vue/voiture/uneVoiture.php");
		include(WORKDIR."/vue/fin.html");
	}
}

?>