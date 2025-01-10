<?php

require_once(WORKDIR . "/modele/modele.php");
require_once(WORKDIR . "/modele/utilisateur.php");
require_once(WORKDIR . "/modele/voiture.php");

class Controleur
{
	public static function lireObjets() {
		$titre = "les " . $objet . "s";
		$table = ucfirst($objet);
		$cle = $table::$cle;
		$tab_o = $table::getAll();
		
		include(WORKDIR . "/vue/debut.php");
		include(WORKDIR . "/vue/menu.html");
		include(WORKDIR . "/vue/lesObjets.php");
		include(WORKDIR . "/vue/fin.html");
	}

	public static function lireObjet() {
		$titre = "un.e " . $objet . ".ice";
		$table = ucfirst($objet);
		$cle = $table::$cle;
		$message = "L'" . $objet . "ice";
		
		if (!isset($_GET["login"])) $_GET["login"] = "igor";
		$l = $_GET["login"];
		$obj = Utilisateur::getObjetById($l);

		include(WORKDIR . "/vue/debut.php");
		include(WORKDIR . "/vue/menu.html");

		if (!$obj) {
			include(WORKDIR . "/vue/erreur.php");
		} else {
			include(WORKDIR . "/vue/unObjet.php");
		}
		
		include(WORKDIR . "/vue/fin.html");
	}
}

?>