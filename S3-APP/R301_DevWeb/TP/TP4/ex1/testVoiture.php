<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>TP4 - ex1</title>
	<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
	<link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
</head>

<body>
<h1>Test de construction d'objets Voiture</h1>

<?php

require_once("voiture.php");

$v1 = new Voiture("Toyota", "noire", "AB123DF");
$v2 = new Voiture("Hyundai", "rose", "AC161AB");
$v3 = new Voiture("Mazda", "rouge", "GG000EZ");

$v1->afficher();
echo '<br>';
$v2->afficher();
echo '<br>';
$v3->afficher();

?>
</body>
</html>