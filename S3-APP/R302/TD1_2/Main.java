public class Main {
    
    public static void main(String[] args) {

        String sep = "------------";

        System.out.println(sep + "Test List" + sep);

        List li = new List(1, 2, 3, 4);
        System.out.println("Initial list :\t\t" + li);

        li.addFirstElement(0);
        System.out.println("addFirstElement(0) :\t" + li);

        li.addEndElement(5);
        System.out.println("addEndElement(5) :\t" + li);

        System.out.println();
        System.out.println("\n" + sep + "Test BinaryTree" + sep);

        BinaryTree<Integer> bi = new BinaryTree<Integer>(
            5,
            new BinaryTree<>(6),
            new BinaryTree<>(4)
        );

        bi.right().addChildren(new BinaryTree<Integer>(3));

        System.out.println("Initial tree :\n" + bi);
        System.out.println("Depth of tree :\t" + bi.depth());
        System.out.println("Children :\t" + bi.childrenCount());

        System.out.println();
        System.out.println(sep + "Test Tree" + sep);

        Tree<Integer> tr = new Tree<Integer>(
            8,
            new Tree<>(
                4,
                new Tree<>(-4)
            ),
            new Tree<>(1)
        );

        System.out.println("Initial tree :\n" + tr);
        System.out.println("Depth of tree :\t" + tr.depth());
        System.out.println("Children :\t" + tr.childrenCount());
    }
}
