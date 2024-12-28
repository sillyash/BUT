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

echo "<ul>"; // Pour un meilleur affichage

for ($i=0; $i<$nbMatieres; $i++) {
	// On ne vérifie plus la présence des variables car la vérification est déjà faire côté formulaire
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

	// On affiche ici pour éviter un second parcours
	echo "<li>Matière « " . $matieres[$i] . " » : ";
	echo "Note = " . $notes[$i];
	if ($pondere) echo ", Coefficient = " . $coefs[$i];
	echo "</li>";
}

echo "</ul>";

$moyenne = $somme / $len;
echo "Moyenne pondérée : $moyenne";

?>
</body></html>
