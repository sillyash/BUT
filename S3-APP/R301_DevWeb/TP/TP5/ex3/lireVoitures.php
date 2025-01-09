<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<title>TP5 - ex3</title>
	<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
	<link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
</head>

<body>
<h1>Lire voitures</h1>

<?php

require_once("connexion.php");
require_once("voiture.php");
Connexion::connect();

$req = "SELECT * FROM Voiture;";
$res = Connexion::pdo()->query($req);
$res->setFetchmode(PDO::FETCH_CLASS, "voiture");

$garage = $res->fetchAll();

echo "<pre>";
print_r($garage);
echo "</pre>";

echo "<h2>Les voitures de mon garage</h2>";

echo "<ul>";
foreach ($garage as $car) {
	echo "<li>$car</li>";
}
echo "</ul>";

?>
</body>
</html>