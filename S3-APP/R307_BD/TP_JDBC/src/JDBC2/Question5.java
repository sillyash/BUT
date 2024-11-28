package JDBC2;

import JDBC.JDBCTools;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Question5
{
    public static void titreFilmActeur(String nomActeur, Connection co) throws SQLException
    {
        CallableStatement cst = co.prepareCall("{call unTitre(?,?,?)}");
        cst.setString(1, nomActeur);
        cst.registerOutParameter(2, Types.VARCHAR);
        cst.registerOutParameter(3, Types.VARCHAR);

        boolean erreur = cst.execute();

        if (erreur) {
            System.err.println("Error while executing unTitre(" + nomActeur + ").");
            return;
        }

        String prenom = cst.getString(2);
        String titre = cst.getString(3);
        System.out.println("Prénom de l'acteur : " + prenom);
        System.out.println("Titre du film : " + titre);
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

        titreFilmActeur(nomActeur, maConnexion);

        sc.close();
    }
}
