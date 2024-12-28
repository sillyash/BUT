<h1>Lire voitures</h1>

<?php

$titre = "toutes les voitures";

include("vue/debut.php");
include("vue/menu.html");
require_once("config/connexion.php");
require_once("modele/voiture.php");

$tab_v = Voiture::getAllVoitures();

include("vue/voiture/lesVoitures.php");
include("vue/fin.html");

?>
</body>