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

define("NB_MATIERES_MAX", 4);

$notes = array();
$matieres = array();
$somme = 0;
$len = 0;

echo "<ul>"; // Pour un meilleur affichage

// On peut faire les calculs quoiqu'il arrive car le formulaire
// a une matière et une note obligatoires à remplir

for ($i=0; $i<NB_MATIERES_MAX; $i++) {
	if (isset($_GET["matiere$i"]) && isset($_GET["note$i"])) { // On vérifie la présence des 2 variables
		$matieres[$i] = $_GET["matiere$i"];
		$notes[$i] = $_GET["note$i"];
		$len++;
		$somme += $notes[$i];
	}

	// On affiche ici pour éviter un second parcours
	echo "<li>Matière « " . $matieres[$i] . " » : ";
	echo "Note = " . $notes[$i] . "</li>";
}

echo "</ul>";

$moyenne = $somme / $len;
echo "Moyenne pondérée : $moyenne";

?>
</body></html>