<?php

class Modele
{
	public function get(string $attribut) {
		return $this->$attribut;
	}

	public function set(string $attribut, $valeur) {
		$this->$attribut = $valeur;
	}
}

?>