import java.util.ArrayList;

public class SquareSum {
    
    /* Le programme suivant doit renvoyer le nombre de carrés.
     * Vous pourrez ensuite l'améliorer pour afficher la liste des carrés choisis.
     */

    public static Integer getMin(List<Integer> objets)
    {
        Integer min = objets.data();
        List<Integer> li = objets;
        while (li.length() > 1)
        {
            li = li.tail();
            if (li.data() < min) min = li.data();
        }
        return min;
    }

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

        for (int i=0; i<=soldats; i++)
        {
            if (isSquare(i))
                possibleSquareSizes.add(i);
        }
        return possibleSquareSizes;
    }


    public static Tree<Integer> fillPossibilityTree(Tree<Integer> tree) {
        if (tree.data() == 0) return new Tree<>(0);

        for (Integer square : possibleSquares(tree.data())) {
            int remainder = tree.data() - square;

            if (remainder >= 0) {
                Tree<Integer> child = new Tree<>(remainder);
                tree.addChildren(fillPossibilityTree(child));
            }
        }

        return tree;
    }

    public static int findMinPhalanges(Tree<Integer> node) {
        if (node.data() == 0) {
            return 0;
        }

        int minPhalanges = -1;

        for (int i = 0; i < node.nbChildren(); i++) {
            if (minPhalanges != -1)
                minPhalanges = Math.min(minPhalanges, findMinPhalanges(node.child(i)));
            else minPhalanges = findMinPhalanges(node.child(i));
        }

        return 1 + minPhalanges;
    }

    public static int nbCarresBruteforce(int soldats)
    {
        Tree<Integer> possibleResults = new Tree<>(soldats);

        possibleResults = fillPossibilityTree(possibleResults);
        //System.out.println("Possibilities :\n" + possibleResults);

        return findMinPhalanges(possibleResults);
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
