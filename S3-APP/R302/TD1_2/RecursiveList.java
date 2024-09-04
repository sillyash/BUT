public class RecursiveList
{
    private Integer head;
    private RecursiveList tail;

    public RecursiveList (
        Integer head,
        RecursiveList tail
    ) {
        this.head = head;
        this.tail = tail;
    }

    public RecursiveList (
        Integer head
    ) {
        this.head = head;
        this.tail = null;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public RecursiveList getTail() {
        return tail;
    }

    public void setTail(RecursiveList tail) {
        this.tail = tail;
    }

    public int listLength() {
        if (this.empty()) return 1;
        return this.tail.listLength() + 1;
    }

    public boolean empty() {
        return this.tail == null;
    }

    public String toString() {
        String s = "";

        s += this.head;

        if (!this.empty()) {
            s += ", ";
            s += this.tail.toString();
        }

        return s;
    }

    public void addElementLast(Integer i) {
        if (this.empty()) this.setTail(new RecursiveList(i));
        else this.tail.addElementLast(i);
    }

    public void addElementLast(RecursiveList rList) {
        if (this.empty()) this.setTail(rList);
        else this.tail.addElementLast(rList);
    }

    public int sum() {
        if (this.empty()) return this.head;
        return this.head + this.tail.sum();
    }

    public void addElementFirst(Integer i) {
        // basically a copy of this
        RecursiveList temp = new RecursiveList(this.head, this.tail);

        this.setHead(i);
        this.setTail(temp);
    }

    public static void main(String args[]) {

        RecursiveList emptyList = new RecursiveList(1);

        System.out.println("Recursive List : \t" + emptyList);
        System.out.println("List is empty : \t" + emptyList.empty());
        System.out.println("Size of list :  \t" + emptyList.listLength());
        System.out.println("Sum of elements : \t" + emptyList.sum());

        RecursiveList rList = new RecursiveList(-2, null);
        rList.addElementLast(new RecursiveList(3));
        rList.addElementLast(2);
        rList.addElementLast(new RecursiveList(-8, new RecursiveList(2)));
        rList.addElementFirst(9);
        rList.addElementFirst(-4);

        System.out.println();
        System.out.println("Recursive list : \t" + rList);
        System.out.println("List is empty : \t" + rList.empty());
        System.out.println("Size of list :  \t" + rList.listLength());
        System.out.println("Sum of elements : \t" + rList.sum());
    }
}


