package JDBC;

import java.sql.*;
import java.util.ArrayList;

public class JDBCTools
{
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


    public static void printResults(ResultSet res) throws SQLException
    {
        if (res == null) {
            System.out.println("Nothing to display : ResultSet empty.");
            return;
        }

        ArrayList<ArrayList<String>> table = new ArrayList<>();
        table.add(new ArrayList<>());
        table.get(0).add("ROW");

        ResultSetMetaData metaData = res.getMetaData();
        for(int i=1; i<=metaData.getColumnCount(); i++) {
            table.get(0).add(metaData.getColumnName(i));
        }

        int k = 1;
        while (res.next())
        {
            table.add(new ArrayList<>());
            table.get(k).add(String.valueOf(res.getRow()));

            for (int i=1; i<=metaData.getColumnCount(); i++) {
                table.get(k).add(res.getObject(i).toString());
            }
            k++;
        }

        AsciiTable at = new AsciiTable();
        at.addRule();
        for (ArrayList<String> row : table) {
            at.addRow(row);
            at.addRule();
            if (table.indexOf(row) == 0) at.addRule();
        }
        System.out.println(at.render());
    }


    public static void printResults(ResultSet res, String[] cols) throws SQLException
    {
        if (res == null) {
            System.out.println("Nothing to display : ResultSet empty.");
            return;
        }

        ArrayList<ArrayList<String>> table = new ArrayList<>();
        table.add(new ArrayList<>());
        table.get(0).add("ROW");

        for (String col : cols) {
            table.get(0).add(col);
        }

        int k = 1;
        while (res.next()) {
            table.add(new ArrayList<>());
            table.get(k).add(String.valueOf(res.getRow()));

            for (String col : cols) {
                try {
                    table.get(k).add(res.getObject(col).toString());
                } catch (SQLException e) {
                    System.err.println("Wrong column name given : " + col);
                    return;
                }
            }
            k++;
        }

        AsciiTable at = new AsciiTable();
        at.addRule();
        for (ArrayList<String> row : table) {
            at.addRow(row);
            at.addRule();
            if (table.indexOf(row) == 0) at.addRule();
        }
        System.out.println(at.render());
    }

    pu