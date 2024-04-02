package ExerciceE;

public class A {
	int na ;
	
	public static void main (String args [])
	{
	        B b = new B();
	        A a1 = new A();
	        a1.na = 2 ;
	        
	        System.out.println ("Avant appel :\t" + a1.na);
	        System.out.println ("Avant appel :\t" + a1);
	        
	        b.m(a1);
	        
	        System.out.println ("Après appel :\t" + a1.na);
	        System.out.println ("Après appel :\t" + a1);
	        
	        // On a réussi à modifier la valeur a1.na On remarque aussi
	        // que l'addresse de A a changé pendant et après l'exécution : 
	        // on a créé une copie de A à la modification, mais elle n'a
	        // pas été prise en compte : on ne peut pas modifier une référence
	        // mais eulement son contenu.
	}
}
