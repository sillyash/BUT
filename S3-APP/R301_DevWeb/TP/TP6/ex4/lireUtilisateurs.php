<?php

define("WORKDIR", dirname(__FILE__));
require_once(WORKDIR . "/config/connexion.php");
Connexion::connect();
require_once(WORKDIR . "/controleur/controleurUtilisateur.php");

?>