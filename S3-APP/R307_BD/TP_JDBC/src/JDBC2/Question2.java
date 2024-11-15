package JDBC2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import JDBC.JDBCTools;

public class Question2 {

    public static ResultSet FilmsWithActor(int numActor, Connection co)
    {
        String req =
            "SELECT F.Titre " +
            "FROM ENS2004.ACTEUR A " +
            "INNER JOIN ENS2004.FILM F ON F.NumFilm = A.NumFilm " +
            "WHERE A.NumIndividu = %d";

        req = String.format(req, numActor);
        return JDBCTools.executeRequest(req, co ,1);
    }

    public static ResultSet ActorsWithName(String name, Connection co) throws SQLException
    {
        String req =
                "SELECT DISTINCT I.NumIndividu, I.PrenomIndividu, I.NomIndividu " +
                "FROM ENS2004.INDIVIDU I " +
                "INNER JOIN ENS2004.ACTEUR A ON A.NumIndividu = I.NumIndividu " +
                "WHERE I.NomIndividu = '%s'";

        req = String.format(req, name.toUpperCase());
        return JDBCTools.executeRequest(req, co, 1);
    }

    public static void FilmsWithActor(String nameActor, Connection co) throws SQLException
    {
        ResultSet actors = ActorsWithName(nameActor, co);
        PreparedStatement psm = co.prepareStatement(
            "SELECT F.Titre " +
            "FROM ENS2004.ACTEUR A " +
            "INNER JOIN ENS2004.FILM F ON F.NumFilm = A.NumFilm " +
            "WHERE A.NumIndividu = ?"
        );

        while (actors.next())
        {
            int numActeur = actors.getInt("NUMINDIVIDU");
            psm.setInt(1, numActeur);

            System.out.println("Films avec " + actors.getString("PrenomIndividu") + " " + actors.getString("NomIndividu") + " :");
            ResultSet res = psm.executeQuery();
            while (res.next()) {
                System.out.println("- " + res.getString("TITRE"));
            }
            System.out.println();
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
        ResultSet res = null;
        String actorName = "";

        while (!reqOK) {
            System.out.print("Entrez un nom d'acteur.ice :\n>>> ");
            actorName = sc.next();
            res = ActorsWithName(actorName, maConnexion);

            if (res == null) System.out.println("Le résultat semble vide. Rééssayez avec un autre nom !");
            else reqOK = true;
        }

        FilmsWithActor(actorName, maConnexion);

        sc.close();
    }
}
