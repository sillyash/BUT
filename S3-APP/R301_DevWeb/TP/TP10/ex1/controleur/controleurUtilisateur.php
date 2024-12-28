<?php

require_once(WORKDIR . "/modele/utilisateur.php");
require_once(WORKDIR . "/vue/controleur/controleur.php");

class controleurUtilisateur extends Controleur
{
	protected static $objet = "utilisateur";
	
	public static function lireUtilisateurs() {
		$tab_o = Utilisateur::getAll();
		$cle = "login";
	}

	public static function lireUtilisateur() {
		$titre = "un.e utilisateur.ice";
		$table = "Utilisateur";
		$cle = "login";
		$message = "L'utilisateur.ice";
		
		if (!isset($_GET["login"])) $_GET["login"] = "igor";
		$l = $_GET["login"];
		$obj = Utilisateur::getObjetById($l);
	}
}

?>