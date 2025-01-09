<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>TP5 - ex6</title>
	<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
	<link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
</head>

<body>
<h1>Lire utilisateurs</h1>

<div id="btns">
	<a href="./lireVoitures.php">
		<div class="lire">
			<p>Lire voitures</p>
		</div>
	</a>
	<a href="./lireUtilisateurs.php">
		<div class="lire">
			<p>Lire utilisateurs</p>
		</div>
	</a>
</div>

<br>

<?php

require_once("utilisateur.php");

Utilisateur::getAllUsers();

?>
</body>

<style>

#btns {
	display: flex;
	flex-direction: row;
	width: 600px;
	justify-items: space-evenly;
	justify-content: space-evenly;
}

#btns > a {
	display: inline-block;
	height: 100%;
}

.lire {
	width: 200px;
	height: 70px;
	align-content: center;
	align-items: center;
	border: 2px solid lightpink;
	border-radius: 5px;
	background-color: darkslategrey;
	text-align: center;
	vertical-align: middle;
}

</style>
</html>