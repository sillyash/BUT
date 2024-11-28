package JDBC2;

import JDBC.JDBCTools;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Question4
{
    public static void FilmsWithDirector(String sonNom, Connection co) throws SQLException
    {
        CallableStatement cst = co.prepareCall("{? = call nbreFilms2(?)}");
        cst.registerOutParameter(1, java.sql.Types.INTEGER);
        cst.setString(2, sonNom);
        boolean error = cst.execute();

        if (error) {
            System.err.println("Error while executing nbFilms2(" + sonNom + ").");
            return;
        }

        int nbFilms = cst.getInt(1);
        cst.close();

        System.out.print("Films réalisés par " + sonNom.toUpperCase() + " : \t");
        System.out.println(nbFilms);
    }

    public static void main(String[] args) throws SQLException
    {
        Connection maConnexion = JDBCTools.openConnection(
                "oracle.iut-orsay.fr",
                1521,
                "etudom",
                "amerie1"
        );

        Scanner sc = new Scanner(System.in);
        String directorName = "";

        System.out.print("Entrez un nom de réalisateur.ice :\n>>> ");
        directorName = sc.next();

        FilmsWithDirector(directorName, maConnexion);

        sc.close();
    }
}
