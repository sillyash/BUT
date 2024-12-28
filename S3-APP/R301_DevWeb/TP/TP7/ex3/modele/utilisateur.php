<?php

class Utilisateur
{
	private string $login;
	private string $mdp;
	private string $nom;
	private string $prenom;
	private string $email;

	public function __construct(
		string $login = NULL,
		string $mdp = NULL,
		string $nom = NULL,
		string $prenom = NULL,
		string $email = NULL
		) {

		if (!is_null($login))
		{
			$this->login = $login;
			$this->mdp = $mdp;
			$this->nom = $nom;
			$this->prenom = $prenom;
			$this->email = $email;
		}
	}

	public function getLogin() { return $this->login; }
	public function getNom() { return $this->nom; }
	public function getPrenom() { return $this->prenom; }
	public function getEmail() { return $this->email; }

	public static function getAllUsers() {
		$req = "SELECT * FROM Utilisateur;";
		$res = Connexion::pdo()->query($req);
		$res->setFetchmode(PDO::FETCH_CLASS, "utilisateur");
		$ppl = $res->fetchAll();

		return $ppl;
	}

	public static function getUtilisateurByLogin(string $l) {
		$req = "SELECT * FROM Utilisateur U 
						WHERE U.login = :login;";
		$prep = Connexion::pdo()->prepare($req);

		$args = array("login" => $l);
		try {
			$prep->execute($args);
		} catch (PDOException $e) {
			$e->getMessage();
		}

		$prep->setFetchmode(PDO::FETCH_CLASS, "utilisateur");
		$res = $prep->fetchAll();

		if (sizeof($res) >= 1) {
			$user = $res[0];
		} else {
			$user = null;
		}

		return $user;
	}

	public function setLogin(string $s) { $this->login = $s; }
	public function setNom(string $s) { $this->nom = $s; }
	public function setPrenom(string $s) { $this->prenom = $s; }
	public function setEmail(string $s) { $this->email = $s; }

	public function afficher() {
		$s = "Utilisateur <b>"
		. $this->getLogin()
		. "</b> ("
		. $this->getPrenom()
		. " "
		. $this->getNom()
		. "), email = <b>"
		. $this->getEmail()
		. "</b>";
		echo "$s";
	}

	public function __toString() {
		$s = "Utilisateur <b>"
		. $this->getLogin()
		. "</b> ("
		. $this->getPrenom()
		. " "
		. $this->getNom()
		. "), email = <a href='mailto:"
		. $this->getEmail()
		. "'>"
		. $this->getEmail()
		. "</a>";
		return $s;
	}
}

?>