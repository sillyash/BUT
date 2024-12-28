<h1>Lire utilisateurs</h1>

<?php

$titre = "tous les utilisateurs";

include("vue/debut.php");
include("vue/menu.html");
require_once("config/connexion.php");
require_once("modele/utilisateur.php");

$tab_u = Utilisateur::getAllUsers();

include("vue/utilisateur/lesUtilisateurs.php");
include("vue/fin.html");

?>
