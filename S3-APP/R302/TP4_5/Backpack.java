import java.util.Collections;

public class Backpack {

    public static int sum(List<Integer> objets) {
        if (objets == null){
            return 0;
        }
        else{
            return objets.data() + sum(objets.tail());
        }
    }


    public static List<Integer> backPackGloutonMin(int contenance, List<Integer> objets)
    {
        System.out.println(objets + "   " + contenance);
        if (objets.length() == 0 || contenance < getMin(objets))
            return null;

        Integer min = getMin(objets);

        if (contenance >= min) {
            objets.remove(min);
            contenance -= min;
        }

        List<Integer> li = new List<Integer>(min);
        li.setTail(backPackGloutonMin(contenance, objets));
        return li;
    }


    public static List<Integer> backPackGloutonMax(int contenance, List<Integer> objets)
    {
        System.out.println(objets + "   " + contenance);
        if (objets.length() == 0 || contenance < getMin(objets))
            return null;

        List<Integer> li;
        Integer max = getMax(objets);

        if (contenance >= max) {
            objets.remove(max);
            contenance -= max;
            li = new List<Integer>(max);
            li.setTail(backPackGloutonMax(contenance, objets));
        } else {
            objets.remove(max);
            li = backPackGloutonMax(contenance, objets);
        }
        return li;
    }


    public static Integer getMax(List<Integer> objets)
    {
        Integer max = objets.data();
        List<Integer> li = objets;
        while (li.length() > 1)
        {
            li = li.tail();

            Integer elem = li.data();
            if (elem > max) max = elem;
        }
        return max;
    }

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


    public static List<Integer> backPackBruteForce(int contenance, List<Integer> objets){

        /* Votre code ici ; il y a deux versions à faire */
        return null;
    }


    public static void main (String... args){
        List<Integer> result = null;

        /*System.out.println("----------------- TEST GLOUTON MIN ------------------");

        result = backPackGloutonMin(20, new List<>(3, 5, 13, 2));
        if (result != null) result.display();
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 20\n", sum(result));

        result = backPackGloutonMin(11, new List<>(3, 3, 3, 2, 2, 2, 2, 2));
        if (result != null) result.display();
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 10\n", sum(result));

*/
        System.out.println("----------------- TEST GLOUTON MAX ------------------");

        result = backPackGloutonMax(20, new List<>(3, 5, 13, 2));
        if (result != null) result.display();
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 20\n", sum(result));

        result = backPackGloutonMax(11, new List<>(3, 3, 3, 2, 2, 2, 2, 2));
        if (result != null) result.display();
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 10\n", sum(result));


        System.out.println("----------------- TEST BRUTE FORCE ------------------");

        result = backPackBruteForce(20, new List<>(3, 5, 13, 2));
        if (result != null) result.display();
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 20\n", sum(result));

        result = backPackBruteForce(11, new List<>(3, 3, 3, 2, 2, 2, 2, 2));
        if (result != null) result.display();
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 10\n", sum(result));

    }

}
