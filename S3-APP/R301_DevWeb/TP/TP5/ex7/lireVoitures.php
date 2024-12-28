<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>TP5 - ex7</title>
	<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
	<link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
</head>

<body>
<h1>Lire voitures</h1>

<?php

include("menu.html");
require_once("voiture.php");

Voiture::getAllVoitures();

?>
</body>