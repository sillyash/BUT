<?php

echo "<ul>";

foreach ($tab_v as $v) {
	echo "<li class='liDetails'>";
	echo "voiture immatriculée ";
	echo $v->getImmatriculation();
	include(WORKDIR."/vue/voiture/BTNdetails.php");
	echo "</li>";
}

echo "</ul>";
?>