<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>TP2 - ex2b</title>
		<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
    <link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
  </head>
  <body>
    <h2>Tableau de capitaux successifs</h2>

<?php

$capital = array(10000);
$taux = 0.05;
$n = 10;

echo "<table>";
echo "
<tr>
  <th>Année N</th>
  <th>Capital en début d'année</th>
</tr>";

for ($i = 1; $i < $n+2; $i++)
{
	$capital[$i] = floor($capital[$i-1] + $capital[$i-1] * $taux);
	echo "
  <tr>
    <td>", $i-1, "</td>
    <td>", $capital[$i-1], "€</td>
  </tr>";
}

echo "</table>";

?>
</body>
</html>
