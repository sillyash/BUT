# S3 - APP | BD

### Ashley Merienne

## TP JDBC 1

### Question 1 - Soit le code Java suivant

```java
import java.sql.*;

public class OutilsJDBC {
	public static Connection openConnection (String url) {
		Connection co=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co= DriverManager.getConnection(url);
		}
		catch (ClassNotFoundException e){
			System.out.println("il manque le driver oracle");
			System.exit(1);
		}
		catch (SQLException e) {
			System.out.println("impossible de se connecter à l'url : "+url);
			System.exit(1);
		}
		return co;
    }
	
    public static ResultSet exec1Requete (String requete, Connection co, int type){
		ResultSet res=null;
		try {
			Statement st;
			if (type==0){
				st=co.createStatement();}
			else {
				st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					       	ResultSet.CONCUR_READ_ONLY);
				}
			res= st.executeQuery(requete);
		}
		catch (SQLException e){
			System.out.println("Problème lors de l'exécution de la requête : "+requete);
		}
		return res;
	}

	public static void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion fermée!");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}	
    }
}

```

#### 1. Expliciter le rôle de chacune des classes. Préciser en particulier les valeurs des attributs.

>La méthode ```Connection``` permet charger le driver JCBD pour Oracle et de créer un objet ```Connexion``` à partie de l'url donné. Cette méthode renvoie une erreur si le driver ne peut être chargé ou si l'url de connexion est incorrect.

> La méthode ```exec1Requete``` permet de créer un objet ```Statement```, d'envoyer une requête et renvoie le résultat de celle-ci (objet ```ResultSet```). Elle exécute la requête passée en argument, sinon une requête par défaut.

> La méthode ```CloseConnection``` ferme la connection (type ```Connection```) qu'on lui passe en argument.


#### 2. Ecrire le code java pour se connecter à l'aide de jdbc.

>[Voir fichier](./src/JDBC/OutilsJDBC.java)


#### 3. Exécutez une requête permettant de récupérer les 10 premières lignes de la table Film dans un objet ResultSet et affichez le résultat sur la console. Pour cela, vous utiliserez les méthodes permettant d'établir, puis de clore la connexion, en récupérant les éventuelles exceptions levées.

>[Voir fichier](./src/JDBC/OutilsJDBC.java)


### Question 3

>[Voir fichier](./src/JDBC/OutilsJDBC.java)


### Question 4

#### Q4.1

Classe JDBC.OutilsJDBC version personnalisée :
>[Voir fichier](./src/JDBC/JDBCTools.java)


#### Q4.2

>[Voir fichier](./src/JDBC1.java)


## TP JDBC 2

### Question 1

>[Voir fichier (méthode)](./src/JDBC/JDBCTools.java)
> 
>[Voir fichier (main)](./src/JDBC2/TableDesc.java)


### Question 2

>[Voir fichier](./src/JDBC2/Question2.java)


### Question 3

```sql
CREATE OR REPLACE FUNCTION nbreFilms1 (numActeur IN ens2004.Acteur.numIndividu%TYPE)
RETURN NUMBER
IS
    nbFilms NUMBER;
BEGIN
    SELECT COUNT(*) INTO nbFilms
    FROM ENS2004.Acteur A
    INNER JOIN ENS2004.Film F ON F.numFilm = A.numFilm
    WHERE A.NumIndividu = numActeur;
    
    RETURN nbFilms;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Value error occurred');
        RETURN -1;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
        RETURN -1;
END;
/
```

>[Voir fichier](./src/JDBC2/Question3.java)

### Question 4

```sql
CREATE OR REPLACE FUNCTION nbreFilms2 (nomReal IN ens2004.Individu.nomIndividu%TYPE)
RETURN NUMBER
IS
    nbFilms NUMBER;
    numReal ens2004.Individu.numIndividu%TYPE;
BEGIN
    SELECT numIndividu INTO numReal
    FROM ens2004.individu I
    WHERE UPPER(nomIndividu) = UPPER(nomReal);

    SELECT COUNT(*) INTO nbFilms
    FROM ENS2004.Film F
    WHERE F.realisateur = numReal;
    
    RETURN nbFilms;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Value error occurred');
        RETURN -1;
    WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.PUT_LINE('Multiple directors with this name');
        RETURN -1;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
        RETURN -1;
END;
/
```

>[Voir fichier](./src/JDBC2/Question4.java)

### Question 5

```sql
CREATE OR REPLACE PROCEDURE unTitre (
    nomActeur IN ens2004.Individu.nomIndividu%TYPE,
    prenomActeur OUT ens2004.Individu.prenomIndividu%TYPE,
    titreFilm OUT ens2004.Film.titre%TYPE)
IS
BEGIN
    SELECT I.prenomIndividu, F.titre INTO prenomActeur, titreFilm
    FROM ENS2004.Acteur A
    INNER JOIN ENS2004.Individu I ON I.numIndividu = A.numIndividu
    INNER JOIN ENS2004.Film F ON F.numFilm = A.numFilm
    WHERE I.nomIndividu = nomActeur AND ROWNUM = 1;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Pas d"acteur avec ce nom, ou bien cet acteur n"a pas joué dans un film');
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Value error occurred');
        RAISE_APPLICATION_ERROR(-10100, 'Value error');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-10101, 'An unexpected error occurred');
END;
/
```

>[Voir fichier](./src/JDBC2/Question5.java)

### Question 6

#### 1)

```sql
CREATE OR REPLACE FUNCTION uneComedie (
    nomActeur IN ens2004.Individu.nomIndividu%TYPE,
    prenomActeur OUT ens2004.Individu.prenomIndividu%TYPE)
RETURN ens2004.Film.titre%TYPE
IS
    titreComedie ens2004.Film.titre%TYPE;
BEGIN
    SELECT I.prenomIndividu, F.titre INTO prenomActeur, titreComedie
    FROM ENS2004.Acteur A
    INNER JOIN ENS2004.Individu I ON I.numIndividu = A.numIndividu
    INNER JOIN ENS2004.Film F ON F.numFilm = A.numFilm
    INNER JOIN ENS2004.GenreFilm G ON G.numFilm = F.numFilm
    WHERE UPPER(I.nomIndividu) = UPPER(nomActeur)
    AND ROWNUM = 1
    AND G.codeGenre = 'CO';
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Pas d"acteur avec ce nom, ou bien cet acteur n"a pas joué dans un film');
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Value error occurred');
        RAISE_APPLICATION_ERROR(-10100, 'Value error');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-10101, 'An unexpected error occurred');
END;
/
```

#### 2) [Voir_fichier](./src/JDBC2/Question6.java)

#### 3) 

```sql
CREATE OR REPLACE FUNCTION uneComedie2 (
    nomActeur IN ens2004.Individu.nomIndividu%TYPE,
    prenomActeur IN ens2004.Individu.prenomIndividu%TYPE,
    codeGenre IN ens2004.Individu.prenomIndividu%TYPE)
RETURN ens2004.Film.titre%TYPE
IS
    titreComedie ens2004.Film.titre%TYPE;
BEGIN
    SELECT F.titre INTO titreComedie
    FROM ENS2004.Acteur A
    INNER JOIN ENS2004.Individu I ON I.numIndividu = A.numIndividu
    INNER JOIN ENS2004.Film F ON F.numFilm = A.numFilm
    INNER JOIN ENS2004.GenreFilm G ON G.numFilm = F.numFilm
    WHERE UPPER(I.nomIndividu) = UPPER(nomActeur)
    AND UPPER(I.prenomIndividu) = UPPER(prenomActeur)
    AND ROWNUM = 1
    AND G.codeGenre = codeGenre;
    
    RETURN titreComedie;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Pas d"acteur avec ce nom, ou bien cet acteur n"a pas joué dans un film');
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Value error occurred');
        RAISE_APPLICATION_ERROR(-10100, 'Value error');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-10101, 'An unexpected error occurred');
END;
/
```

>[Voir_fichier](./src/JDBC2/Question6.java)

