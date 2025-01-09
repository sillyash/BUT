<?php

$titre = "toutes les voitures";

include(WORKDIR."/vue/debut.php");
include(WORKDIR."/vue/menu.html");
require_once(WORKDIR."/modele/voiture.php");

$tab_v = Voiture::getAllVoitures();

include(WORKDIR."/vue/voiture/lesVoitures.php");
include(WORKDIR."/vue/fin.html");

?>