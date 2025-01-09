<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>TP6 - ex1</title>
	<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
	<link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
</head>

<body>
<h1>Lire utilisateurs</h1>

<?php

include("menu.html");
require_once("config/connexion.php");
require_once("modele/utilisateur.php");

Utilisateur::getAllUsers();

?>
</body>
</html>