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


