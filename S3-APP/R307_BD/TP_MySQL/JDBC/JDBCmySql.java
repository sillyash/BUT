package JDBC;

import java.sql.*;

public class JDBCmySql extends JDBCTools implements JDBCConnect {
    public static Connection openConnection(String url) {
        Connection co = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            co = DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException e){
            System.out.println("il manque le driver MySQL");
            System.exit(1);
        }
        catch (SQLException e) {
            System.out.println("impossible de se connecter à l'url : "+url);
            System.exit(1);
        }
        return co;
    };
}