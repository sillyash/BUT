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
}

?>