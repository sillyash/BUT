public class TestDupont{
	public static void main (String args[]) {
	    // Dupont cree un rectangle
		Rec rec = new Rec(1.12, 2.45, 5.63, 7.25);
		
	    // Dupont appelle les methodes developpees par Pasquier 
	    // Il est *oblige* de passer a la methode afficherStats un objet instance d'une
		// classe implementant la classe abstraite FormeGeometrique
	    Editeur ed = new Editeur ();
	    ed.afficherStats(rec);
	    
		// Afficher dans la console en appelant la méthode afficher sur cette forme
	    rec.afficher();
	    
	    // Changer les coordonnées de la forme en appelant la méthode déplacer
	    rec.deplacer(2.74, 5.03);
	    
		// Afficher dans la console en appelant la méthode afficher sur cette forme
	    rec.afficher();
	    
	    // Demander de nouveau d’afficher des statistiques sur cette forme
	    ed.afficherStats(rec);
	    
	    // Idem avec un cercle
	    Cercle circ = new Cercle(8.23, 4.01, 1.78);
	    
	    ed.afficherStats(circ);
	    circ.afficher();
	    
	    circ.deplacer(2.82, 1.14);
	    
	    circ.afficher();
	    ed.afficherStats(circ);
	}
}
