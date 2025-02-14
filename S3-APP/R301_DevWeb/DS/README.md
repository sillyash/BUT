# DS Développement Web

Ash MERIENNE

## Exercice 1

### Question 1 et 2

Voici la liste des fichiers nécéssaires :
- models/
  - Modele.php
    - classe abstraite Modele
    - attributs statiques $table et $cle
    - constructeur générique avec en paramètre un tableau
    - méthode getObjetById() -> récupère un objet de la BDD
    - méthode getAllObjets() -> récupère une table (ou une portion) de la BDD
  - Livre.php
    - afficher() pour un affichage dans les vues
    - __toString() pour un affichage simplifié
  - Utilisateur.php
    - afficher() pour un affichage dans les vues
    - __toString() pour un affichage simplifié
  - Emprunt.php
    - afficher() pour un affichage dans les vues
    - __toString() pour un affichage simplifié
  - Categorie.php
    - afficher() pour un affichage dans les vues
    - __toString() pour un affichage simplifié
  - Reservation.php
    - __toString() pour un affichage simplifié
- controllers/
  - Controleur.php
    - attribut statique $objet (nom de la classe Modèle)
    - lireUnObjet() pour récupérer un objet via le modèle puis appeler sa vue
    - lireObjets() pour récupérer une table via le modèle puis appeler sa vue
  - ControleurLivre.php
  - ControleurUtilisateur.php
  - ControleurEmprunt.php
  - ControleurCategorie.php
  - ControleurReservation.php
  - *Dans les autres fichiers, on définit simplement l'attribut $objet et on hérite de la classe Controleur*
- views/
  - debut.php
    - Gestion de la session
    - Header
  - fin.php
    - Fermeture des balises main
    - Footer
    - Fermeture du reste des balises
  - unObjet.php
    - Appel de afficher() sur un modèle
  - lesObjets.php
    - Appel de afficher() sur un tableau de modèles
- connexion.php
  - classe Connexion
  - connect() pour initialiser la connexion à la BDD
  - pdo() ou getPdo() pour récupérer la connexion
- routeur.php (ou index.php)
  - importer la classe Connexion et initialiser la connexion
  - gérer le $_GET pour connaître le controleur et l'action demandé.e, sinon demander le contrôleur et/ou l'action par défaut
  - inclure le controleur en question
  - appeler l'action sur le contrôleur

### Question 3

#### Comment un contrôleur récupère-t-il les données d’un modèle ?

Le contrôleur inclue la classe modèle correspondante (via `static::$objet` le nom de la classe) et appelle la méthode `getObjetById()` ou `getAll()`.


#### Comment un contrôleur envoie-t-il des données à une vue ?

Le contrôleur créée la ou les variables nécéssaires (par exemple `$lesObjets` pour la vue `lesObjets.php`) et inclue la vue, qui s'occuppe, elle, de l'affichage.


#### Comment la vue affiche-t-elle les données récupérées ?

La vue, donc, fait un/des appel.s (par exemple `$objet->afficher()`) sur la variable correspondante (par exemple une boucle sur `$lesObjets`).


#### Comment une action utilisateur déclenche-t-il un traitement via le contrôleur ?

Une action utilisateur (par exemple un clic sur un bouton "afficher les détails") appelle le routeur, qui lui appelle le contrôleur et l'action demandée par le bouton (`ControleurLivre::lireUnObjet()`), qui déclenche le traitement.


## Exercice 2

### Fonctionnalités

#### La recherche d’un livre par son titre.

[Voir fichier](./rechercheLivre.php)

#### L’emprunt d’un livre s’il est disponible.

[Voir fichier](./empruntLivre.php)

#### La réservation d’un livre s’il est indisponible.

[Voir fichier](./reserverLivre.php)
