import java.util.ArrayList;
import java.util.Collections;

public class Test {

	public static void main (String args[])
	{
		LivreAudio a = new LivreAudio(
			"Toby Lolness",
			"blablabla",
			"Timothée de Fombelle", 
			13, 
			"http://mabiblio/romans/toby-lolness.wav"
		);
		
		LivreAudio mouton = new LivreAudio(
			"La course au mouton sauvage",
			"blablabla",
			"Haruki Murakami", 
			16, 
			"http://mabiblio/difficile/romans/course-au-mouton-sauvage.wav"
		);
		
		Periodique hebdo = new Periodique("L'Hebdo", "bla", Periodique.HEBDOMADAIRE);
		LivreEcrit vango = new LivreEcrit("Vango", "bla", "Timothée de Fombelle", 564);
		DVD matrix = new DVD("Matrix", "bla", DVD.FILM);
		
		System.out.println("ToString()...\n");
		System.out.println(mouton);
		System.out.println(hebdo);
		System.out.println(vango);
		System.out.println(matrix);
		System.out.println();
		
		
		System.out.println("Test de CompareTo()...");
		ArrayList<LivreAudio> audios = new ArrayList<>();
		audios.add(mouton);
		audios.add(a);
		Collections.sort(audios);
		System.out.println(audios);
		if (audios.indexOf(a) == 0 && audios.indexOf(mouton) == 1)
			System.out.println("Tri réussi !\n");
		else System.out.println("Tri raté...\n");
		
		
		System.out.print("Test de estDifficile()... ");
		LivreEcrit tomEtnana = new LivreEcrit ("Tom et Nana", "bla", "John Doe", 26);
		if (vango.estDifficile() && mouton.estDifficile() && !tomEtnana.estDifficile()) System.out.println("Réussi !\n");
		else System.out.println("Raté.\n");
		
		
		System.out.println("Test de LivreAudio.setNote()... ");
		int OldNote = mouton.getNote();
		mouton.setNote(-1);
		mouton.setNote(21);
		if (mouton.getNote() != OldNote) System.out.println("Raté...\n");
		else {
			mouton.setNote(19);
			if (mouton.getNote() == 19) System.out.println("Réussi !\n");
			else System.out.println("raté...\n");
		}
		
		System.out.print("Test de dureeEmprunt()... ");
		if (vango.dureeEmprunt() == 30 && tomEtnana.dureeEmprunt() == 7 && matrix.dureeEmprunt() == 10) {
			tomEtnana.setNbPages(151);
			if (tomEtnana.dureeEmprunt() == 15) System.out.println("Réussi !\n");
		}
		else System.out.println("Raté...\n");
	}
}
