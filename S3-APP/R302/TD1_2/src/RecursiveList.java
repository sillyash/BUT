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

    public boolean empty() {
        return this.tail == null;
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

    public void addElement(Integer i) {
        if (this.empty()) this.setTail(new RecursiveList(i));
        this.addElement(i);
    }

    public void addElement(RecursiveList rList) {
        if (this.empty()) this.setTail(rList);
        this.addElement(rList);
    }

    public int ListLength() {
        if (this.empty()) return 1;
        return this.tail.ListLength() + 1;
    }

    public String toString() {
        String s = "";

        s += "[";
        s += this.head;

        if (this.empty()) s += "]";
        else s += this.tail.toString();

        return s;
    }
}

