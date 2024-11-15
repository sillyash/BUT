package JDBC2;

import JDBC.JDBCTools;

import java.sql.*;
import java.util.Scanner;

public class TableDesc
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
