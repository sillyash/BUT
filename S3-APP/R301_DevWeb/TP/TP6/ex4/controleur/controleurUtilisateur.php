<?php

$titre = "tous les utilisateurs";

include(WORKDIR."/vue/debut.php");
include(WORKDIR."/vue/menu.html");
require_once(WORKDIR."/modele/utilisateur.php");

$tab_u = Utilisateur::getAllUsers();

include(WORKDIR."/vue/utilisateur/lesUtilisateurs.php");
include(WORKDIR."/vue/fin.html");

?>