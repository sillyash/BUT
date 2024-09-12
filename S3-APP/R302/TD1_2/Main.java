public class Main {
    
    public static void main(String[] args) {

        String sep = "--------";

        System.out.println(sep + "Test List" + sep);

        List li = new List(1, 2, 3, 4);
        System.out.println("Initial list :\t\t" + li);

        li.addFirstElement(0);
        System.out.println("addFirstElement(0) :\t" + li);

        li.addEndElement(5);
        System.out.println("addEndElement(5) :\t" + li);

        System.out.println("\n" + sep + "Test BinaryTree" + sep);

        BinaryTree bi = new BinaryTree<>(
            5,
            new BinaryTree<>(6),
            new BinaryTree<>(4)
        );

        bi.right().addChildren(new BinaryTree(3));

        System.out.println("Initial tree :\n" + bi);

        System.out.println("Depth of tree :\t" + bi.depth());
    }
}
