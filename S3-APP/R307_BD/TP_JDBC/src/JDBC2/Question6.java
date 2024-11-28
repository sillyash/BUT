package JDBC2;

import JDBC.JDBCTools;

import java.sql.*;
import java.util.Scanner;

public class Question6
{
    public static void uneComedie(String nomActeur, Connection co) throws SQLException
    {
        CallableStatement cst = co.prepareCall("{? = call uneComedie(?,?)}");
        cst.registerOutParameter(1, Types.VARCHAR);
        cst.setString(2, nomActeur);
        cst.registerOutParameter(3, Types.VARCHAR);

        boolean erreur = cst.execute();

        if (erreur) {
            System.err.println("Error while executing unTitre(" + nomActeur + ").");
            return;
        }

        String titre = cst.getNString(1);
        String prenom = cst.getString(3);

        System.out.println("Prénom de l'acteur : " + prenom);
        System.out.println("Titre de la comedie : " + titre);
    }

    public static String getActorSurname(String nomActeur, Connection co) throws SQLException {
        String req =
                "SELECT I.prenomIndividu\n" +
                        "FROM ENS2004.Individu I\n" +
                        "INNER JOIN ENS2004.Acteur A ON A.numIndividu = I.numIndividu\n" +
                        "WHERE UPPER(I.nomIndividu) = UPPER('%s')\n" +
                        "AND ROWNUM = 1";
        req = String.format(req, nomActeur);

        ResultSet res = JDBCTools.executeRequest(req, co ,1);

        if (res == null) {
            System.out.println("Pas d'acteur avec ce nom.");
            return null;
        }

        res.next();

        return res.getString(1);
    }

    public static ResultSet genresActeur(String nomActeur, Connection co) throws SQLException {
        String prenomActeur = getActorSurname(nomActeur, co);
        String req =
                "SELECT G.CodeGenre, G.LibelleGenre\n" +
                "FROM ENS2004.Genre G\n" +
                "INNER JOIN ENS2004.GenreFilm GF ON GF.codeGenre = G.codeGenre\n" +
                "INNER JOIN ENS2004.Film F ON F.numFilm = GF.numFilm\n" +
                "INNER JOIN ENS2004.Acteur A ON A.numFilm = F.numFilm\n" +
                "INNER JOIN ENS2004.Individu I ON I.numIndividu = A.numIndividu\n" +
                "WHERE UPPER(I.nomIndividu) = UPPER('%s')\n" +
                "AND UPPER(I.prenomIndividu) = UPPER('%s')";
        req = String.format(req, nomActeur, prenomActeur);

        return JDBCTools.executeRequest(req, co, 1);
    }

    public static void uneComedie(String nomActeur, String codeGenre, Connection co) throws SQLException
    {
        String prenomActeur = getActorSurname(nomActeur, co);

        CallableStatement cst = co.prepareCall("{? = call uneComedie2(?,?,?)}");
        cst.registerOutParameter(1, Types.VARCHAR);
        cst.setString(2, nomActeur);
        cst.setString(3, prenomActeur);
        cst.setString(4, codeGenre);

        boolean erreur = cst.execute();

        if (erreur) {
            System.err.println("Error while executing uneComedie2(" +
                    nomActeur + ", " + prenomActeur + ", " + codeGenre + ").");
            return;
        }

        String titre = cst.getString(1);

        System.out.println("Prénom de l'acteur : " + prenomActeur);
        System.out.println("Titre de la comedie : " + titre);
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
        String nomActeur;

        System.out.print("Entrez un nom de acteur.ice :\n>>> ");
        nomActeur = sc.next();
        System.out.println();

        System.out.println("Genres acteur.ice : ");
        ResultSet res = genresActeur(nomActeur, maConnexion);
        JDBCTools.printResults(res);

        System.out.print("Entrez un code de genre :\n>>> ");
        String genre = sc.next();

        uneComedie(nomActeur, genre, maConnexion);

        sc.close();
    }
}
