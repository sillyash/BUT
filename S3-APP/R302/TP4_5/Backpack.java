public class Backpack {

    public static int sum(List<Integer> objets){
        if (objets == null){
            return 0;
        }
        else{
            return objets.data() + sum(objets.tail());
        }
    }

    public static List<Integer> backPack(int contenance, List<Integer> objets){

        /* Votre code ici ; il y a deux versions à faire */
        return null;
    }

    public static void main (String... args){
        List<Integer> result = null;

        result = backPack(20, new List<>(3, 5, 13, 2));
        if (result != null) result.display();
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 20\n", sum(result));

        result = backPack(11, new List<>(3, 3, 3, 2, 2, 2, 2, 2));
        if (result != null) result.display();
        System.out.printf("Votre fonction choisit des objets de volume total %d, l'optimal est 10\n", sum(result));

    }

}
