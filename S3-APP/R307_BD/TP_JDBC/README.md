# S3 - APP | BD

### Ashley Merienne

## TP JDBC 1

### Question 1 - Soit le code Java suivant

```java
import java.sql.*;

public class TP_JDBC_1.OutilsJDBC {
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

>[Voir fichier](./src/OutilsJDBC.java)


#### 3. Exécutez une requête permettant de récupérer les 10 premières lignes de la table Film dans un objet ResultSet et affichez le résultat sur la console. Pour cela, vous utiliserez les méthodes permettant d'établir, puis de clore la connexion, en récupérant les éventuelles exceptions levées.

>[Voir fichier](./src/OutilsJDBC.java)


### Question 3

>[Voir fichier](./src/OutilsJDBC.java)


### Question 4

#### Q4.1

Classe OutilsJDBC version personnalisée :
>[Voir fichier](./src/JDBCTools.java)


#### Q4.2

>[Voir fichier](./src/JDBC1.java)


## TP JDBC 2

### Question 1

>[Voir fichier (méthode)](./src/JDBCTools.java)
> 
>[Voir fichier (main)](./src/TableDesc.java)


### Question 2


