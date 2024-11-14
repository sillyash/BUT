import de.vandermeer.asciitable.AsciiTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBC2
{
    public static void main (String[] args) throws SQLException
    {
        Connection maConnexion = JDBCTools.openConnection(
                "oracle.iut-orsay.fr",
                1521,
                "etudom",
                "amerie1"
        );

        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez un nom de table SQL existante :\n>>> ");
        String tableName = sc.next();
        JDBCTools.getTableDesc(tableName, maConnexion);
    }
}
