public interface TreeInteger<T>{
    public T data();
    public Tree<T> child(int i);
    public int nbChildren();
    public Tree<T> parent();
    public void addChildren(@SuppressWarnings("unchecked") Tree<T>... childs);
    public void setChild(int i, Tree<T> child);
    public String toString();
    public void display();
}