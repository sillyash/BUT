<?php

require_once(WORKDIR . "/modele/modele.php");
require_once(WORKDIR . "/modele/utilisateur.php");
require_once(WORKDIR . "/modele/voiture.php");

class Controleur
{
	protected static $objet;
	protected static $cle;

	public static function lireObjets() {
		$titre = "les " . static::$objet . "s";
		$table = ucfirst(static::$objet);
		$cle = static::$cle;
		$tab_o = $table::getAll();
		
		include(WORKDIR . "/vue/debut.php");
		include(WORKDIR . "/vue/menu.html");
		include(WORKDIR . "/vue/lesObjets.php");
		include(WORKDIR . "/vue/fin.html");
	}

	public static function lireUnObjet() {
		$titre = "un.e " . static::$objet . ".ice";
		$table = ucfirst(static::$objet);
		$cle = static::$cle;
		$message = "L'" . static::$objet . "ice";
		
		$l = $_GET[$cle];
		$obj = $table::getObjetById($l);

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