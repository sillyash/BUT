package JDBC2;

import JDBC.JDBCTools;

import java.sql.*;
import java.util.Scanner;

public class Question3
{
    public static void FilmsWithActor(String nameActor, Connection co) throws SQLException
    {
        ResultSet actors = Question2.ActorsWithName(nameActor, co);
        PreparedStatement psm = co.prepareStatement(
                "SELECT F.Titre " +
                        "FROM ENS2004.ACTEUR A " +
                        "INNER JOIN ENS2004.FILM F ON F.NumFilm = A.NumFilm " +
                        "WHERE A.NumIndividu = ?"
        );

        while (actors.next())
        {
            int numActeur = actors.getInt("NUMINDIVIDU");

            CallableStatement cst = co.prepareCall("{? = call nbreFilms1(?)}");
            cst.setInt(2, numActeur);
            cst.registerOutParameter(1, java.sql.Types.INTEGER);
            boolean error = cst.execute();

            if (error) {
                System.err.println("Error while executing nbFilms1(" + numActeur + ").");
                return;
            }

            int nbFilms = cst.getInt(1);
            cst.close();

            System.out.print("Films avec " + actors.getString("PrenomIndividu") + " ");
            System.out.print(actors.getString("NomIndividu") + " : \t");
            System.out.println(nbFilms);
        }
    }

    public static void main(String[] args) throws SQLException
    {
        Connection maConnexion = JDBCTools.openConnection(
                "oracle.iut-orsay.fr",
                1521,
                "etudom",
                "amerie1"
        );

        boolean reqOK = false;
        Scanner sc = new Scanner(System.in);
        String actorName = "";
        ResultSet res;

        while (!reqOK) {
            System.out.print("Entrez un nom d'acteur.ice :\n>>> ");
            actorName = sc.next();
            res = Question2.ActorsWithName(actorName, maConnexion);

            if (res == null) System.out.println("Le résultat semble vide. Rééssayez avec un autre nom !");
            else reqOK = true;
        }

        FilmsWithActor(actorName, maConnexion);

        sc.close();
    }
}
