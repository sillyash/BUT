<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>TP4 - ex3</title>
	<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
	<link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
</head>

<body>
<h1>Affichage de l'utilisateur créé</h1>

<?php

require_once("utilisateur.php");

$name = $_GET["nom"];
$mail = $_GET["email"];
$pass = $_GET["password"];
$login = $_GET["login"];
$surname = $_GET["prenom"];

$u = new Utilisateur(
	$login,
	$pass,
	$name,
	$surname,
	$mail
);

$u->afficher();
?>
<a style="margin-top: 2rem;" href="formulaireUtilisateur.html">Retour au formulaire</a>
</body>
</html>