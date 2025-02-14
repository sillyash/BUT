import java.util.ArrayList;

public class BinaryTree<T> extends Tree<T> implements BinaryTreeInterface<T>{

    private T data;
    private BinaryTree<T> left;
    private BinaryTree<T> right;
    private BinaryTree<T> parent;

    public BinaryTree(){}

    @SafeVarargs
    public BinaryTree(T data, BinaryTree<T>... child) {
        this.data = data;
        if (child.length > 0){
            this.setLeft(child[0]);
        }
        if (child.length > 1){
            this.setRight(child[1]);
        }
        this.parent = null;
    }

    public T data(){
        return this.data;
    }

    public BinaryTree<T> parent(){
        return this.parent;
    }

    public BinaryTree<T> left() {
        return this.left;
    }

    public void setLeft(BinaryTree<T> t) {
        this.left = t;
        this.left.parent = this;
    }

    public BinaryTree<T> right() {
        return this.right;
    }

    public void setRight(BinaryTree<T> t) {
        this.right = t;
        this.right.parent = this;
    }

     /* Adapted from VasiliNovikov@StackOverflow */

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.data());
        buffer.append('\n');
        if (this.left() != null && this.right() != null){
            this.left().print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
        }
        if (this.left() != null && this.right() == null){
            this.left().print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
        }
        if (this.right != null){
            this.right().print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
        }
        
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    public void display() {
        System.out.println(this.toString());
    }

    /* Pour la compatibilité avec Tree<T> */

    private ArrayList<BinaryTree<T>> children(){
        ArrayList<BinaryTree<T>> result = new ArrayList<>();
        if (this.left() != null){result.add (this.left());}
        if (this.right() != null){result.add (this.right());}
        return result;
    }

    public BinaryTree<T> child(int n){
        try{
            return this.children().get(n);
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public void setChild(int i, BinaryTree<T> child){
        if (i == 0){
            this.setLeft(child);
            return;
        }
        if (i == 1){
            this.setRight(child);
            return;
        }
        throw new IndexOutOfBoundsException();
    }
    
    @SafeVarargs
    public final void addChildren(BinaryTree<T>... childs){
        if (childs.length > 0){
            this.setLeft(childs[0]);
            return;
        }
        if (childs.length > 1){
            this.setRight(childs[1]);
            return;
        }
    }

    public int nbChildren(){
        return this.children().size();
    }

    // Custom stuff below

    public int depth() {
        if (this.left == null && this.right == null) {
            return 0;
        } else if (this.left == null) {
            return 1 + this.right.depth();
        } else if (this.right == null) {
            return 1 + this.left.depth();
        }
        return 1 + Math.max(this.left.depth(), this.right.depth());
    }

    public int nodeCount() {
        if (this.left == null && this.right == null) {
            return 1;
        } else if (this.left == null) {
            return 1 + this.right.nodeCount();
        } else if (this.right == null) {
            return 1 + this.left.nodeCount();
        }
        return 1 + this.left.nodeCount() + this.right.nodeCount();
    }

    public T max() {
        return null;
    }
}
