import java.util.ArrayList;
import java.util.Collections;

public class SquareSum {
    
    /* Le programme suivant doit renvoyer le nombre de carrés.
     * Vous pourrez ensuite l'améliorer pour afficher la liste des carrés choisis.
     */

    public static int nbCarresGlouton (int soldats) {
        int nbSquares = 0;
        while (soldats > 0) {
            int squareSize = (int) Math.floor(Math.sqrt(soldats)); // size of phalange
            nbSquares++; // add a phalange
            soldats -= squareSize*squareSize; // substract soldiers that are in the phalange
        }

        return nbSquares;
    }


    public static boolean isSquare(int nb) {
        int sqrt = (int)Math.sqrt(nb);
        return (sqrt*sqrt == nb) && (nb > 0);
    }


    public static int nbCarresBruteforce(int soldats) {

        Tree<Integer> squares = new Tree<>(soldats);
        int fatherValue = soldats;

        while (fatherValue > 1)
        {
            for (int i = fatherValue; i > 0; i--) {
                if (isSquare(i)) squares.addChildren(new Tree<>(i));
            }


        }

        System.out.println(squares);

        return -1;

    }


    public static void main(String... args){
        System.out.println("-------- Test Glouton --------");
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 21, nbCarresGlouton(21), 3);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 27, nbCarresGlouton(27), 3);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 33, nbCarresGlouton(33), 3);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 9, nbCarresGlouton(9), 1);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 3, nbCarresGlouton(3), 3);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 0, nbCarresGlouton(0), 0);

        System.out.println("\n\n------- Test Bruteforce -------");
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 21, nbCarresBruteforce(21), 3);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 27, nbCarresBruteforce(27), 3);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 33, nbCarresBruteforce(33), 3);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 9, nbCarresBruteforce(9), 1);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 3, nbCarresBruteforce(3), 3);
        System.out.printf("Pour %d soldats, vous trouvez %d phalanges (le minimum est %d)\n", 0, nbCarresBruteforce(0), 0);
    }

}
