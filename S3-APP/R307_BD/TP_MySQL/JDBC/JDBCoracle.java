package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCoracle extends JDBCTools implements JDBCConnect {
    public static Connection openConnection(String url) {
        Connection co = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
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
    }
}
