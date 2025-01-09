<?php

echo "<ul>";

foreach ($tab_u as $u) {
	echo "<li>";
	$u->afficher();
	echo "</li>";
}

echo "</ul>";

?>