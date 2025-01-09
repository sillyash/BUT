<?php

echo "<ul>";

foreach ($tab_o as $obj) {
	echo "<li class='liDetails'>";
	echo $obj;
	$id = $obj->get($cle);
	include("BTNdetails.php");
	echo "</li>";
}

echo "</ul>";
?>
