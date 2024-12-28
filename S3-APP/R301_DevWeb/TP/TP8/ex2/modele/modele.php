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
}

?>