import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class Backpack {

    public static Integer getMin(ArrayList<Integer> objets) {
        return Collections.min(objets);
    }


    public static Integer getMax(ArrayList<Integer> objets) {
        return Collections.max(objets);
    }


    public static int sum(ArrayList<Integer> list) {
        int total = 0;
        for (Integer value : list) {
            total += value;
        }
        return total;
    }


    public static ArrayList<Integer> backPackGloutonMin(int contenance, ArrayList<Integer> objets) {

        if (objets.isEmpty()) return new ArrayList<>();

        Integer min = getMin(objets);
        ArrayList<Integer> li = new ArrayList<>();
        li.add(min);

        if (objets.size() == 1) {
            if (contenance >= min) {
                return li;
            } else {
                return new ArrayList<>();
            }
        }

        objets.remove(min);
        if (contenance >= min) {
            contenance -= min;
        }

        li.addAll(backPackGloutonMin(contenance, objets));
        return li;
    }


    public static ArrayList<Integer> backPackGloutonMax(int contenance, ArrayList<Integer> objets) {

        if (objets.isEmpty()) return new ArrayList<>();

        Integer max = getMax(objets);
        ArrayList<Integer> li = new ArrayList<>();
        li.add(max);

        if (objets.size() == 1) {
            if (contenance >= max) {
                return li;
            } else {
                return new ArrayList<>();
            }
        }

        objets.remove(max);
        if (contenance >= max) {
            contenance -= max;
        }

        li.addAll(backPackGloutonMax(contenance, objets));
        return li;
    }


    public static ArrayList<Integer> backPackBruteForce(int contenance, ArrayList<Integer> objets){

        /* Votre code ici ; il y a deux versions à faire */
        return null;
    }


    public static void main(String... args) {
        ArrayList<Integer> result;
        ArrayList<Integer> objets;

        System.out.println("----------------- TEST GLOUTON MIN ------------------");

        objets = new ArrayList<>(Arrays.asList(7, 4, 3, 3, 2));
        result = backPackGloutonMin(19, objets);
        System.out.println(result);
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 19\n", sum(result));

        objets = new ArrayList<>(Arrays.asList(7, 4, 3, 3, 2));
        result = backPackGloutonMin(20, objets);
        System.out.println(result);
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 19\n", sum(result));

        System.out.println("\n----------------- TEST GLOUTON MAX ------------------");

        objets = new ArrayList<>(Arrays.asList(7, 4, 3, 3, 2));
        result = backPackGloutonMax(19, objets);
        System.out.println(result);
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 19\n", sum(result));

        objets = new ArrayList<>(Arrays.asList(7, 4, 3, 3, 2));
        result = backPackGloutonMax(20, objets);
        System.out.println(result);
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 19\n", sum(result));

        System.out.println("\n----------------- TEST BRUTE FORCE ------------------");

        objets = new ArrayList<>(Arrays.asList(7, 4, 3, 3, 2));
        result = backPackBruteForce(19, objets);
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 19\n", sum(result));

        objets = new ArrayList<>(Arrays.asList(7, 4, 5, 3, 3));
        result = backPackBruteForce(20, objets);
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 19\n", sum(result));
    }
}
