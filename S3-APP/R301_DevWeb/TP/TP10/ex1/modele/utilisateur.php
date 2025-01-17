<?php

require_once("modele.php");

class Utilisateur extends Modele
{
	protected static $objet = "utilisateur";
	protected static $cle = "login";

	protected string $login;
	protected string $mdp;
	protected string $nom;
	protected string $prenom;
	protected string $email;

	public function afficher() {
		$s = "Utilisateur <b>"
		. $this->get("login")
		. "</b> ("
		. $this->get("prenom")
		. " "
		. $this->get("nom")
		. "), email = <b>"
		. $this->get("email")
		. "</b>";
		echo "$s";
	}

	public function __toString() {
		$s = "Utilisateur "
		. $this->get("login")
		. " ("
		. $this->get("prenom")
		. " "
		. $this->get("nom")
		. "), email = "
		. $this->get("email");
		return $s;
	}
}

?>