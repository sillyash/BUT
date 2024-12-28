<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>TP4 - ex1</title>
	<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
	<link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
</head>

<body>
<h1>Affichage de la voiture créée</h1>

<?php

require_once("voiture.php");

$i = $_GET["immatriculation"];
$m = $_GET["marque"];
$c = $_GET["couleur"];

$v = new Voiture($i, $m, $c);
$v->afficher();

?>
<a href="formulaireVoiture.html">Retour au formulaire</a>
</body>
</html>