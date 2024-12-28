<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>TP3 - ex1</title>
		<link rel="stylesheet" href="/~amerie1/R301/global.css"/>
    <link rel="icon" type="image/x-icon" href="/~amerie1/favicon.ico">
  </head>
  <body>
    <h2>Affichage de $_GET</h2>

<?php

if (isset($_GET["password"]))
{
  echo "<p>L'entrée <b><i>password</i></b> existe !</p>";
  $password = $_GET["password"];
  echo '$_GET["password"] = '.$password;
}
else {
  echo "<p>L'entrée <b><i>password</i></b> n'existe pas !</p>";
}

?>