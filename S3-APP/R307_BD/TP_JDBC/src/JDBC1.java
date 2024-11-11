import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC1
{
    public static ResultSet ActorsWithName(String name, Connection co) throws SQLException
    {
        String req =
            "SELECT DISTINCT I.NumIndividu, I.PrenomIndividu, I.NomIndividu " +
            "FROM ENS2004.INDIVIDU I " +
            "INNER JOIN ENS2004.ACTEUR A ON A.NumIndividu = I.NumIndividu " +
            "WHERE I.NomIndividu = '%s'";

        req = String.format(req, name.toUpperCase());
        ResultSet res = JDBCTools.executeRequest(req, co, 1);
        return res;
    }


    public static ResultSet FilmsWithActor(int numActor, Connection co)
    {
        String req =
            "SELECT F.Titre " +
            "FROM ENS2004.ACTEUR A " +
            "INNER JOIN ENS2004.FILM F ON F.NumFilm = A.NumFilm " +
            "WHERE A.NumIndividu = %d";

        req = String.format(req, numActor);
        ResultSet res = JDBCTools.executeRequest(req, co ,1);
        return res;
    }


    public static void main(String[] args) throws SQLException {
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
        String[] cols = {"PRENOMINDIVIDU", "NOMINDIVIDU"};

        while (!reqOK) {
            System.out.print("Entrez un nom d'acteur.ice :\n>>> ");
            actorName = sc.next();
            res = ActorsWithName(actorName, maConnexion);

            if (res == null) System.out.println("Le résultat semble vide. Rééssayez avec un autre nom !");
            else reqOK = true;
        }

        reqOK = false;
        while (!reqOK) {
            System.out.println("\nChoisissez parmi les acteurs suivants :");
            JDBCTools.printResults(res, cols);
            System.out.print(">>> ");
            int rowID = sc.nextInt();

            if (res.absolute(rowID)) reqOK = true;
        }

        String actor = "";
        actor += res.getString("PRENOMINDIVIDU");
        actor += " ";
        actor += res.getString("NOMINDIVIDU");
        int actorID = res.getInt(1);

        System.out.println("\nVous avez choisi : " + actor + ", ID : " + actorID);
        System.out.println("\nIel a joué dans :");

        res = FilmsWithActor(actorID, maConnexion);
        JDBCTools.printResults(res);

        sc.close();

    }
}
