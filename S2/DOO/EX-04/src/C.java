public class C {
	int i ; 
	
	public static void main(String[] args) {
		C o = new C1();
		((C1)o).i = 1 ; 
		((C)o).i = 2;
		System.out.println(o.i);
		System.out.println(((C)o).i);
		System.out.println(((C1)o).i);
	}
}
	
class C1 extends C {
	int i ; 
}
