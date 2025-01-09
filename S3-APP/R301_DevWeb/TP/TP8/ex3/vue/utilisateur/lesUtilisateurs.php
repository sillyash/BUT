<?php

echo "<ul>";

foreach ($tab_u as $u) {
	echo "<li class='liDetails'>";
	echo "utilisateur.ice de login ";
	echo $u->get("login");
	include(WORKDIR."/vue/utilisateur/BTNdetails.php");
	echo "</li>";
}

echo "</ul>";

?>