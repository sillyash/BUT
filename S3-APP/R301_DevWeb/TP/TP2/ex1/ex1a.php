<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>TP2 - ex1a</title>
    <link rel="stylesheet" href="/~amerie1/R301/global.css"/>
    <link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
  </head>
  <body>
    <h2>Palmarès de la coupe du monde de football depuis 2002</h2>
    <?php
      $palmares = array(
        2022 => "Argentine",
        2018 => "France",
        2014 => "Allemagne",
        2010 => "Espagne",
        2006 => "Italie",
        2002 => "Brésil"
      );
      echo "<pre>";
      print_r($palmares);
      echo "</pre>";
    ?>

  </body>
</html>
