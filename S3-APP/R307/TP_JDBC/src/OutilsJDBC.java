package TP_JDBC_1;
import oracle.jdbc.pool.*;
import java.sql.*;
import java.util.*;

public class OutilsJDBC
{
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
            System.err.println(e);
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

    // ASH MERIENNE
    public static void printResults(ResultSet res) throws SQLException {
        System.out.println();
        ResultSetMetaData metaData = res.getMetaData();

        System.out.print("ROW, ");
        for(int i = 1; i<=metaData.getColumnCount(); i++) {
            System.out.print(metaData.getColumnName(i));
            if (!(i==metaData.getColumnCount()))
                System.out.print(", ");
        }
        System.out.println();

        while (res.next()) {
            System.out.print(res.getRow());
            System.out.print(",\t ");

            for (int i=0; i<metaData.getColumnCount(); i++) {
                System.out.print(res.getObject(i+1));
                if (!(i==metaData.getColumnCount()-1))
                    System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws SQLException
    {
        // Q1.2
        OracleDataSource ds = new OracleDataSource();
        ds.setDriverType("thin");
        ds.setServerName("oracle.iut-orsay.fr");
        ds.setPortNumber(1521);
        ds.setDatabaseName("etudom");
        ds.setUser("amerie1");

        String password;
        Scanner sc = new Scanner(System.in);
        System.out.print("Input OracleDB password : ");
        password = sc.next();
        ds.setPassword(password);

        Connection maConnection;
        try {
            maConnection = ds.getConnection();
            System.out.println("Successfully connected to the database !");
        } catch (SQLException err) {
            System.out.print("Error connecting to the database : ");
            System.err.println(err);
            return;
        }


        // Q1.3
        String requete = "SELECT * FROM ENS2004.FILM WHERE ROWNUM <= 10";
        ResultSet res = exec1Requete(requete, maConnection, 0);
        printResults(res);
        res.close();


        // Q2
        requete = "SELECT * FROM ENS2004.INDIVIDU WHERE NOMINDIVIDU = 'FONDA'";
        res = exec1Requete(requete, maConnection, 0);
        printResults(res);
        res.close();


        // Q3
        requete = "INSERT INTO CLIENT (nomClient, prenomClient, motPasse, adrClient, CPClient) VALUES" +
                "('MERIENNE', 'Ashley', 'visiblePassword123', '12 rue Paul Fort', 75014)";
        res = exec1Requete(requete, maConnection, 0);

        requete = "INSERT INTO CLIENT (nomClient, prenomClient, motPasse, adrClient, CPClient) VALUES" +
                "('ESCOFFIER', 'Emma', 'visiblePassword321', 'somewhere in Arpajon', 91000)";
        res = exec1Requete(requete, maConnection, 0);

        requete = "INSERT INTO LOCATION (numExemplaire, dateLocation, numClient, " +
                "dateEnvoi, dateRetour) VALUES (20096, SYSDATE, 1, SYSDATE, null)";
        res = exec1Requete(requete, maConnection, 0);

        requete = "INSERT INTO LOCATION (numExemplaire, dateLocation, numClient, " +
                "dateEnvoi, dateRetour) VALUES (20096, SYSDATE-3, 2, SYSDATE-1, SYSDATE)";
        res = exec1Requete(requete, maConnection, 0);

        requete = "DELETE FROM CLIENT";
        res = exec1Requete(requete, maConnection, 0);

        requete = "DELETE FROM LOCATION";
        res = exec1Requete(requete, maConnection, 0);


        closeConnection(maConnection);
    }
}
