<?php

require_once(WORKDIR."/modele/utilisateur.php");

class controleurUtilisateur
{
	public static function lireUtilisateurs() {
		$titre = "tous les utilisateurs";
		$tab_u = Utilisateur::getAllUsers();
		
		include(WORKDIR."/vue/debut.php");
		include(WORKDIR."/vue/menu.html");
		include(WORKDIR."/vue/utilisateur/lesUtilisateurs.php");
		include(WORKDIR."/vue/fin.html");
	}
}

?>