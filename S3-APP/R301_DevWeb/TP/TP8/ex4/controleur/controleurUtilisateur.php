<?php

require_once(WORKDIR."/modele/utilisateur.php");

class controleurUtilisateur
{
	public static function lireUtilisateurs() {
		$titre = "tous les utilisateurs";
		$tab_u = Utilisateur::getAll();
		
		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");
		include(WORKDIR."/vue/utilisateur/lesUtilisateurs.php");
		include(WORKDIR."/vue/fin.html");
	}

	public static function lireUtilisateur() {
		$titre = "un.e utilisateur.ice";
		
		if (!isset($_GET["login"])) $_GET["login"] = "igor";
		$l = $_GET["login"];
		$u = Utilisateur::getObjetById($l);

		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");

		if (!$u) {
			include(WORKDIR."/vue/utilisateur/erreur.php");
		} else {
			include(WORKDIR."/vue/utilisateur/unUtilisateur.php");
		}
		
		include(WORKDIR."/vue/fin.html");
	}
}

?>