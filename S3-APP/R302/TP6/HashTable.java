import java.util.Arrays;

public class HashTable
{
    private String[] table;
    private int size;

    // Constructeur

    public HashTable(int size) {
        this.size = size;
        this.table = new String[size];
    }

    // Getters

    public String[] getTable() { return this.table; }
    public int getSize() { return this.size; }

    // Méthodes

    // Provient du cours
    public int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash + key.charAt(i)) % this.size;
        }
        return hash % this.size;
    }

    public boolean caseDisponible(int index, String key) {
        // Check que la case n'est pas remplie ou égale à la clé
        boolean dispo = this.table[index] != null;
        boolean doublon;

        if (dispo) doublon = table[index].equals(key);
        else doublon = false;

        return dispo && !doublon;
    }

    public void insert(String key) {
        // on calcule l'index initial
        int index = hash(key);
        int originalIndex = index;

        while (caseDisponible(index, key)) {
            index = (index + 1) % this.size;
            if (index == originalIndex) {
                // table pleine
                System.err.println("Cannot insert : Array is full.");
                Thread.dumpStack();
                return;
            }
        }
        table[index] = key;
    }

    public boolean check(String key) {
        int index = hash(key);
        int originalIndex = index;

        while (table[index] != null) {
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + 1) % this.size;
            if (index == originalIndex) {
                break;
            }
        }
        return false;
    }

    public void remove(String key) {
        int index = hash(key);
        int originalIndex = index;

        while (table[index] != null)
        {
            if (table[index].equals(key)) {
                table[index] = null;
                // Réorganiser les éléments après la suppression
                index = (index + 1) % this.size;
                while (table[index] != null) {
                    String temp = table[index];
                    table[index] = null;
                    insert(temp);
                    index = (index + 1) % this.size;
                }
                return;
            }

            // on parcourt à partir de i jusqu'à i-1
            // en passant par la fin du tableau
            index = (index + 1) % this.size;

            if (index == originalIndex) {
                break;
            }
        }
    }

    public String toString() { return Arrays.toString(this.table); }

    public static void main (String... args)
    {
        HashTable T = new HashTable(10);

        T.insert("Hello"); T.insert("World");
        System.out.printf(
                "Test insert / check : vous renvoyez %b (true attendu) %b (false attendu) %b (false attendu) \n",
                T.check("Hello"),
                T.check("Wrold"),
                T.check("test")
        );

        System.out.println(T);
        System.out.println();

        T.insert("Wolrd"); T.insert("Hello");
        System.out.printf(
                "Test insert / check avec collision : vous renvoyez %b (true attendu) %b (true attendu) %b (false attendu) \n",
                T.check("World"),
                T.check("Wolrd"),
                T.check("Wrold")
        );

        System.out.println(T);
        System.out.println();

        T.insert("test"); T.remove("Wolrd"); T.remove("Uh ?");
        T.remove("Hello");T.remove("test"); T.insert("Wolrd");
        System.out.printf(
                "Test remove / check avec collision : vous renvoyez %b (false attendu) %b (true attendu) %b (false attendu) \n",
                T.check("test"),
                T.check("Wolrd"),
                T.check("Hello")
        );

        System.out.println(T);
        System.out.println();

        T.insert("Test"); T.insert("Test"); T.remove("Test");
        System.out.printf(
                "Test doublon : vous revoyez %b (false attendu) \n",
                T.check("Test")
        );

        T = new HashTable(10);
        for (int i=0; i<T.getSize()+1; i++)
        {
            T.insert(String.valueOf(i));
        }

        System.out.print("Test table pleine : ");
        System.out.println(T);
        System.out.println();


        System.out.println("Si l'instruction précedente a renvoyé une erreur \"Array is full\" alors le test a réussi.");


    }
}