<?php

require_once(WORKDIR."/modele/utilisateur.php");

class controleurUtilisateur
{
	public static function lireUtilisateurs() {
		$titre = "tous les utilisateurs";
		$tab_o = Utilisateur::getAll();
		$table = "Utilisateur";
		$cle = "login";
		
		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");
		include(WORKDIR."/vue/lesObjets.php");
		include(WORKDIR."/vue/fin.html");
	}

	public static function lireUtilisateur() {
		$titre = "un.e utilisateur.ice";
		$table = "Utilisateur";
		$cle = "login";
		$message = "L'utilisateur.ice";
		
		if (!isset($_GET["login"])) $_GET["login"] = "igor";
		$l = $_GET["login"];
		$obj = Utilisateur::getObjetById($l);

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