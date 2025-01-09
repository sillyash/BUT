<?php

class Modele
{
	public function __construct(array | object $donnees = NULL) {
		if (!is_null($donnees)) {
			foreach ($donnees as $attribut => $valeur) {
				$this->set($attribut, $valeur);
			}
		}
	}

	public function get(string $attribut) {
		return $this->$attribut;
	}

	public function set(string $attribut, $valeur) {
		$this->$attribut = $valeur;
	}

	public static function getAll() {
		$table = ucfirst(static::$objet);
		$req = "SELECT * FROM $table;";

		$res = Connexion::pdo()->query($req);
		$res->setFetchmode(PDO::FETCH_CLASS, $table);
		$ppl = $res->fetchAll();

		return $ppl;
	}

	public static function getObjetById(string $id) {
		$table = ucfirst(static::$objet);
		$k = static::$cle;

		$req = "SELECT * FROM $table 
						WHERE $k = :$k;";
		$prep = Connexion::pdo()->prepare($req);

		$args = array($k => $id);
		try {
			$prep->execute($args);
		} catch (PDOException $e) {
			$e->getMessage();
		}

		$prep->setFetchmode(PDO::FETCH_CLASS, $table);
		$res = $prep->fetchAll();

		if (sizeof($res) >= 1) {
			$obj = $res[0];
		} else {
			$obj = null;
		}

		return $obj;
	}
}

?>