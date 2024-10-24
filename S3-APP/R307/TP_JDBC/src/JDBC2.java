package TP_JDBC_1;
import java.sql.Connection;
import java.sql.ResultSet;

public class JDBC2
{
    public static ResultSet getTableDesc(String table, Connection co) {

        String req = "DESC " + table;

        ResultSet res = JDBCTools.executeRequest(req, co, 1);

        return res;
    }

    public static void main (String[] args)
    {

    }
}
