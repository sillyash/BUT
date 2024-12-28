<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>TP2 - ex2c</title>
		<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
    <link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
  </head>
  <body>
    <h2>Tableau de capitaux successifs</h2>
    <h3>Jusqu'à dépassement du seuil</h3>

<?php

$capital = array(10000);
$taux = 0.05;
$seuil = 17000;

echo "
<ul>
<li>capital initial = $capital[0]</li>
<li>taux = ", $taux*100, "%</li>
<li>seuil = $seuil €</li>
</ul>
";

echo "<table>";
echo "
<tr>
  <th>Année N</th>
  <th>Capital en début d'année</th>
</tr>";

$i = 1;
while ($capital[$i-1] < $seuil)
{
	$capital[$i] = floor($capital[$i-1] + $capital[$i-1] * $taux);
	echo "
  <tr>
    <td>", $i-1, "</td>
    <td>", $capital[$i-1], "€</td>
  </tr>";

  $i++;
}

$capital[$i] = floor($capital[$i-1] + $capital[$i-1] * $taux);
echo "
<tr>
  <td>", $i-1, "</td>
  <td class='seuilAtteint'>", $capital[$i-1], "€</td>
</tr>
</table>";

?>
</body>
</html>
