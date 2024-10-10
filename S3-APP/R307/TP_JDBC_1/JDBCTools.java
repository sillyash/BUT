package TP_JDBC_1;
import java.sql.*;
import java.util.Scanner;
import oracle.jdbc.pool.*;

public class JDBCTools
{
    public static Connection openConnection (String server, int port, String database, String user) {
        Connection co = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            OracleDataSource ds = new OracleDataSource();
            ds.setDriverType("thin");
            ds.setServerName(server);
            ds.setPortNumber(port);
            ds.setDatabaseName(database);
            ds.setUser(user);
            String password = "";
            Scanner sc = new Scanner(System.in);
            System.out.print("Input OracleDB password : ");
            password = sc.next();
            ds.setPassword(password);
            co = ds.getConnection();
        }
        catch (ClassNotFoundException e){
            System.out.println("il manque le driver oracle");
            System.exit(1);
        }
        catch (SQLException e) {
            System.out.println("impossible de se connecter !");
            System.err.println(e);
            System.exit(1);
        }
        return co;
    }

    public static ResultSet executeRequest (String request, Connection co, int type){
        ResultSet res=null;
        try {
            Statement st;
            if (type==0){
                st=co.createStatement();}
            else {
                st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            }
            res= st.executeQuery(request);
        }
        catch (SQLException e) {
            System.out.println("Problème lors de l'exécution de la requête : "+request);
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
}
