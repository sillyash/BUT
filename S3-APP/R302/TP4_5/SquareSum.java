import java.util.ArrayList;

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


    public static ArrayList<Integer> possibleSquares(int soldats)
    {
        ArrayList<Integer> possibleSquareSizes = new ArrayList<>();

        for (int i=0; i<soldats; i++)
        {
            if (isSquare(i))
                possibleSquareSizes.add(i);
        }
        return possibleSquareSizes;
    }


    public static Tree<Integer> fillPossibilityTree(Tree<Integer> tree)
    {
        ArrayList<Integer> possibleSquareSizes = possibleSquares(1);

        for (Integer square : possibleSquareSizes)
        {
            Tree<Integer> child = new Tree<Integer>(tree.data() - square);
            tree.addChildren(child);
        }
        return null;
    }


    public static int nbCarresBruteforce(int soldats)
    {
        Tree<Integer> possibleResults = new Tree<>(soldats);

        fillPossibilityTree(possibleResults);
        System.out.println("Possibilities : " + possibleResults);

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
