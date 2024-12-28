<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf8">
		<meta lang="fr">
		<meta title="Formulaire notes">
		<link rel="stylesheet" href="./styles.css">
	</head>
	<body>
<?php

$notes = array();
$matieres = array();
$coefs = array();
$somme = 0;
$len = 0;

// Tableau html avec les titres des colonnes
echo "<table>";
echo "<tr><th>Matières</th>
			<th>Coefficient</th>
			<th>Note</th></tr>";

for ($i=0; $i<$nbMatieres; $i++) {
	$matieres[$i] = $_GET["matiere$i"];
	$notes[$i] = $_GET["note$i"];
	if ($pondere) $coefs[$i] = $_GET["coef$i"];

	if ($pondere) {
		$somme += $notes[$i] * $coefs[$i];
		$len += $coefs[$i];
	} else {
		$somme += $notes[$i];
		$len++;
	}

	// On affiche ici pour éviter un second parcourss
	echo "<tr><td>" . $matieres[$i] . "</td>";
	if ($pondere) echo "<td>" . $coefs[$i] . "</td>";
	echo "<td>" . $notes[$i] . "</td></tr>";
}

$moyenne = $somme / $len;

// Choix de la couleur de la moyenne
if ($moyenne < 8) $color = "red";
elseif ($moyenne <= 10) $color = "orange";
else $color = "green";

// Affichage de la moyenne
echo "<tr><td>Moyenne</td><td color='$color'>$moyenne</td></tr>";
echo "</table>";

?>
</body></html>