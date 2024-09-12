# TP 1 - Signaux et processus

## Exercice 0 : Hello world

>Créez un fichier hello.c contenant le code ci-dessus, compilez-le et exécutez-le.

[See file](./hello.c)

#### Output

    hello, world


## Rappels pointeurs

[See file](./ptr.c)


#### Output

    valeur = 11.000000
    &valeur = 0x7ffc2fd38ab8


#### Que représente valeur ? Qu’affiche le premier printf() ?

>```valeur``` est un double. Le premier printf affiche ```11.000000```


#### Que représente &valeur ? Qu’affiche le second printf() ?

>```&valeur``` est l'addresse à laquelle se trouve la valeur de ```valeur```


#### Que représente pv ? Que représente &pv ? Que représente *pv ?

>```pv``` est un pointeur de type double qui pointe à l'adresse de valeur


#### Quelle est la taille de la zone mémoire réservée pour valeur ? Pour pv ? Pour nombre ? Pour pn ?

>```valeur``` a une la taille d'un double, donc en C 8 octets. ```pv``` a la taille d'une addresse mémoire, donc 8 octets. ```nombre``` a la taille d'un int, donc 4/8 octets, et ```pn``` fait 8 octets.


#### Pourquoi les tailles sont-elles différentes pour valeur et nombre, mais identiques pour pv et pn ?

>cf. réponse précédente

### Passage en paramètre

[See file](./)


#### Output

    Avant echange : pi = 2.718280, e = 3.141590.
    Apres echange : pi = 2.718280, e = 3.141590.


#### Qu’affiche ce programme ? Expliquez.

>cf. output \
>le programme affiche 2 fois la même chose car les variables sont copiées et donc ne sont pas réellement échangées.

