<?php

class Utilisateur
{
	private string $login;
	private string $password;
	private string $name;
	private string $surname;
	private string $email;


	public function __construct(
		string $login,
		string $mdp,
		string $nom,
		string $prenom,
		string $mail
		) {
		$this->login = $login;
		$this->password = $mdp;
		$this->name = $nom;
		$this->surname = $prenom;
		$this->email = $mail;
	}

	public function getLogin() { return $this->login; }
	public function getName() { return $this->name; }
	public function getSurname() { return $this->surname; }
	public function getEmail() { return $this->email; }

	public function setLogin(string $s) { $this->login = $s; }
	public function setName(string $s) { $this->name = $s; }
	public function setSurname(string $s) { $this->surname = $s; }
	public function setEmail(string $s) { $this->email; }

	public function afficher() {
		$s = "Utilisateur "
		. $this->getLogin()
		. " ("
		. $this->getSurname()
		. $this->getName()
		. "), email = "
		. $this->getEmail();
		echo "$s";
	}
}

?>