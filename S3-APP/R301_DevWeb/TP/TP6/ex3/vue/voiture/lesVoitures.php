<?php

echo "<ul>";

foreach ($tab_v as $v) {
	echo "<li>";
	$v->afficher();
	echo "</li>";
}

echo "</ul>";

?>