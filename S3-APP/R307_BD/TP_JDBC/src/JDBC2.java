import de.vandermeer.asciitable.AsciiTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBC2
{
    public static ResultSet getTableDesc(String tableName, Connection co) throws SQLException
    {

        String req = "SELECT * FROM %s WHERE ROWNUM = 1";
        req = String.format(req, tableName);

        ResultSet res = JDBCTools.executeRequest(req, co, 1);

        ArrayList<ArrayList<String>> table = new ArrayList<>();
        table.add(new ArrayList<>());
        table.get(0).add("Name");
        table.get(0).add("Null?");
        table.get(0).add("Type");

        ResultSetMetaData metaData = res.getMetaData();

        for (int i=1; i<=metaData.getColumnCount(); i++)
        {
            table.add(new ArrayList<>());
            table.get(i).add(metaData.getColumnName(i));
            String nullable = metaData.isNullable(i) == ResultSetMetaData.columnNullable ? "" : "NOT NULL";
            table.get(i).add(nullable);
            table.get(i).add(metaData.getColumnTypeName(i));

        }

        AsciiTable at = new AsciiTable();
        at.addRule();
        for (ArrayList<String> row : table) {
            at.addRow(row);
            at.addRule();
            if (table.indexOf(row) == 0) at.addRule();
        }
        System.out.println(at.render());

        return res;
    }

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
        getTableDesc(tableName, maConnexion);
    }
}
