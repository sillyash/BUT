public class Main {

    public static List<Integer> addElemToSortedList(List<Integer> li, Integer i)
    {
        if (i < li.data()) {
            System.out.println("aaaa");
            List<Integer> newLi = new List<Integer>(i);
            newLi.setTail(li);
            System.out.println(newLi);
            return newLi;
        }

        if (li.length() == 1) {
            li.setTail(new List<Integer>(i));
            return li;
        }

        li.setTail(addElemToSortedList(li.tail(), i));
        return li;
    }

    public static boolean isInTree(Tree<Integer> tree, Integer i)
    {
        if (tree.data().equals(i)) return true;

        if (tree.nbChildren() == 0) return false;

        for (int k=0; k<tree.nbChildren(); k++)
        {
            Tree<Integer> child = tree.child(k);
            if (isInTree(child, i)) return true;
        }

        return false;
    }

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


        System.out.println(sep + "Test TESTETEREGERG" + sep);
        List<Integer> lil = new List<>(4);
        lil.addEndElement(7); lil.addEndElement(8);
        lil.addEndElement(11); lil.addEndElement(11);
        lil.addEndElement(13); lil.addEndElement(14);
        System.out.println("Initial list: " + lil);
        addElemToSortedList(lil, 12);
        System.out.println("Add elem 12 : "+ lil);
        addElemToSortedList(lil, 11);
        System.out.println("Add elem 11 : " + lil);
        addElemToSortedList(lil, 16);
        System.out.println("Add elem 16 : " + lil);
        System.out.println();
        System.out.println("Tree :\n" + tr);
        System.out.println("isInTree(10) : " + isInTree(tr, 10));
        System.out.println("isInTree(-4) : " + isInTree(tr, -4));
        System.out.println("isInTree(1) : " + isInTree(tr, 1));
    }
}
