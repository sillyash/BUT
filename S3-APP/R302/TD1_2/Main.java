public class Main {
    
    public static void main(String[] args) {

        String sep = "------------";

        System.out.println(sep + "Test List" + sep);

        List li = new List(1, 2, 3, 4);
        System.out.println("Initial list :\t\t\t" + li);

        li.addFirstElement(0);
        System.out.println("addFirstElement(0) :\t" + li);

        li.addEndElement(5);
        System.out.println("addEndElement(5) :\t\t" + li);

        System.out.println();
        System.out.println("\n" + sep + "Test BinaryTree" + sep);

        BinaryTree<Integer> bi = new BinaryTree<Integer>(
            5,
            new BinaryTree<>(6),
            new BinaryTree<>(4)
        );

        BinaryTree<Integer> emptyBi = new BinaryTree<>(3);

        bi.right().addChildren(new BinaryTree<Integer>(3));

        System.out.println("Initial tree :\n" + bi);
        System.out.println("Depth of tree :\t\t" + bi.depth());
        System.out.println("Number of nodes :\t" + bi.nodeCount());

        System.out.println();
        System.out.println("Initial tree :\n" + emptyBi);
        System.out.println("Depth of tree :\t\t" + emptyBi.depth());
        System.out.println("Number of nodes :\t" + emptyBi.nodeCount());

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

        Tree<Integer> emptyTr = new Tree<>(-6);

        System.out.println("Initial tree :\n" + tr);
        System.out.println("Depth of tree :\t\t" + tr.depth());
        System.out.println("Number of nodes :\t" + tr.nodeCount());

        System.out.println();
        System.out.println("Initial tree :\n" + emptyTr);
        System.out.println("Depth of tree :\t\t" + emptyTr.depth());
        System.out.println("Number of nodes :\t" + emptyTr.nodeCount());
    }
}
