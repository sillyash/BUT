<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>TP2 - ex1e</title>
		<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
    <link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
  </head>
  <body>
    <h2>Les nombre de titres par vainqueur de la coupe du monde de football depuis 1930</h2>
    <?php
      $palmares = array(
        2022 => "Argentine",
        2018 => "France",
        2014 => "Allemagne",
        2010 => "Espagne",
        2006 => "Italie",
        2002 => "Brésil",
        1930 => "Uruguay",
        1950 => "Uruguay",
        1934 => "Italie",
        1938 => "Italie",
        1982 => "Italie",
        1954 => "Allemagne",
        1974 => "Allemagne",
        1990 => "Allemagne",
        1958 => "Brésil",
        1962 => "Brésil",
        1970 => "Brésil",
        1994 => "Brésil",
        1966 => "Angleterre",
        1978 => "Argentine",
        1986 => "Argentine",
        1998 => "France"
      );

      ksort($palmares);

      $countries = array_unique($palmares);

      $nbTitres = array();
      $nbTitres = array_combine(
        $countries,
        array_fill(0, count($countries), 0)
      );

			foreach ($palmares as $year => $country)
			{
          $nbTitres[$country] += 1;
			}

      echo"Par ordre décroissant du nombre de titres :";

      asort($nbTitres);

      echo "<pre>";
      print_r(array_reverse($nbTitres));
      echo "</pre>";

      echo"Par ordre alphabétique du pays :";

      ksort($nbTitres);

      echo "<pre>";
      print_r($nbTitres);
      echo "</pre>";
    ?>

  </body>
</html>

