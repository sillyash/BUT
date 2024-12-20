import JDBC.*;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://projets.iut-orsay.fr:3306/LOGIN?\n"
                    + "user=prj-mmorich&password=vZnmFpECUAvJTwCf";
        Connection connection = JDBCmySql.openConnection(url);
    }
}
