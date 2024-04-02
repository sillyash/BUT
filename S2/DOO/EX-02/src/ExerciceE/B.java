package ExerciceE;

class B {
	int nb ;
		
	void m(A a2)
	{
	    a2.na = 10 ;
	    a2 = new A();
	    System.out.println ("Durant appel :\t" + a2);
	}
}