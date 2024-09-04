import RecursiveList.java;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("TD1 et 2");

        RecursiveList rList = new RecursiveList(3);
        rList.addElement(new RecursiveList(5));
        rList.addElement(5);

        System.out.println("Recursive List : ", rList);
    }
}
