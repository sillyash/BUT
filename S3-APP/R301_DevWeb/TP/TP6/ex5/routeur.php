<?php

define("WORKDIR", dirname(__FILE__));

if(!isset($_GET["controleur"])) {
	$_GET["controleur"] = "controleurVoiture"; // par défaut
}

$controleur = $_GET["controleur"];
require_once(WORKDIR . "/config/connexion.php");
Connexion::connect();
require_once(WORKDIR . "/controleur/$controleur.php");

?>