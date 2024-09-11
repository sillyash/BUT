public interface BinaryTreeInteger<T> extends TreeInteger<T>{ 
    public T data();
    public BinaryTree<T> parent();
    public BinaryTree<T> left();
    public void setLeft(BinaryTree<T> t);
    public BinaryTree<T> right();
    public void setRight(BinaryTree<T> t);
    public String toString();
    public void display();

    /* Les méthodes suivantes sont ajoutées pour que les arbres
     * binaires BinaryTree fonctionnent pour les fonctions acceptant des 
     * arbres Tree mais ne sont pas utiles si l'arbre est binaire.
     */
    public Tree<T> child(int i);
    public int nbChildren();
    public void addChildren(@SuppressWarnings("unchecked") BinaryTree<T>... childs);
    public void setChild(int i, BinaryTree<T> child);
}