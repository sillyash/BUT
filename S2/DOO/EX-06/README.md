# EX-06

#### Voici un extrait de la javadoc: 

    public abstract class AbstractAction
    extends Object
    implements Action, Cloneable, Serializable

    This class provides default implementations for the JFC Action interface. Standard behaviors like the get and set methods for Action object properties (icon, text, and enabled) are defined here. The developer needs only subclass this abstract class and define the actionPerformed method. 

#### Vous n’avez pas à écrire de programme qui utilise cette classe.

<br>

## Vous devez seulement répondre aux questions ci-dessous :

### Est-ce que AbstractAction est une classe concrète ?

```AbstractAction``` n'est pas une classe concrète.


### Est-ce que AbstractAction est une classe abstraite ?

```AbstractAction``` est une classe abstraite : on utilise ```extends``` dans sa déclaration.


### Est-ce que AbstractAction est une interface ?

Non, c'est une classe abstraite.

<br>

## Qu’est-ce que cela signifie pour le développeur qui veut utiliser AbstractAction : 

### Qu’est-ce qu’il ne peut pas faire ?

Le développeur ne opeut pas instancier la classe ```AbstractAction``` car elle est abstraite.


### Qu’est-ce qu’il doit faire pour utiliser cette classe abstraite ? 

Le développeur doit utiliser ou créer une sous-classe instanciable de ```AbstractAction``` pour l'utiliser.

<br>

## On vous donne la classe suivante :

	class Personnel {
		private Employe[] staff;
		private int nbreEmploye;
		private final static int MAXEMPLOYE = 200;
		
		public Personnel() {
			staff = new Employe[MAXEMPLOYE];
			nbreEmploye = 0;    
		}
		
		public void ajouterEmploye(Employe e) {
			if (nbreEmploye <= MAXEMPLOYE) {
				++nbreEmploye;
				staff[nbreEmploye - 1] = e;
			} else {
				System.out.println("Pas plus de " + MAXEMPLOYE + " employés");
			}
		}
		
		public double salaireMoyen() {
			double somme = 0.0;
			for (int i = 0; i < nbreEmploye; i++) {
				somme += staff[i].calculerSalaire();
			}
			return somme / nbreEmploye;
		}
		
		public void afficherSalaires() {
			for (int i = 0; i < nbreEmploye; i++) {
				System.out.println(staff[i].getNom() + " gagne "+ staff[i].calculerSalaire());
			}
		}
	}

### Parmi les méthodes qui sont définies et celles qui sont appelées dans cette classe Personnel, quelle est (ou quelles sont) la (ou les) méthodes polymorphes ?

Les méthodes ```salaireMoyen()``` et ```afficherSalaire()``` sont polymorphes.


