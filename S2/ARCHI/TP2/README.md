# R204 · Communication bas niveau · TP2

## 1. Microprocesseur élémentaire

### Définitions

- **Registre** · Zone mémoire dans la zone du processeur (!= RAM)
- **UAL · Unité Arithmétique et Logique (ALU)** · élément du microprocesseur chargé d'effectuer les calculs
- **Multiplexeur** · Permet "d'aiguiller" plusieurs entrées sur une sortie


### Questions

#### Sur combien de bits est codée une instruction ?

>Une instruction est codée sur 24 bits. (cf.mémoire programme)


#### Sur combien de bits est codé le champ DATA ou adresse ?

>Une addresse est codée sur 16 bits. (cf.mémoire programme)


#### Quelles sont les positions, dans le code de l’instruction, des bits du champ DATA ou adresse (0 étant la position du bit de poids le plus faible) ?

>Sur les bits 0 à 15.


#### Sur combien de bits est codé le code opératoire ?

>Le code opératoire est codé sur 8 bits. (bits restants)


#### Quelles sont les positions dans le code de l’instruction, des bits du code opératoire (0 étant la position du bit de poids le plus faible) ?

>Le code opératoire est donc codé sur les bits de 16 à 23.


#### Indiquez le rôle de chacun des bits du code opératoire

|  Position  |   Nom   |                  Rôle                  |
|:----------:|:-------:|----------------------------------------|
|   Bit 0    |   JMP   |Saute vers une instruction              |
|   Bit 1    |  REG B  |Active l'écriture sur le registre B     |
|   Bit 2    |  REG A  |Active l'écriture sur le registre A     |
|   Bit 3    |   MUX   |Redirige le flux (1-> reg A, 0-> addr)  |
|Bits 4,5,6,7|   ALU   |Donne l'opération à effectuer par l'ALU |

Ce microprocesseur supporte (entre autres) les instructions suivantes :


| Instruction<br>(mnémonique assembleur) |                      Signification                      |
|----------------------------------------|---------------------------------------------------------|
| LOAD_A #valeur                         | Copie une valeur dans le registre A                     |
| LOAD_B #valeur                         | Copie une valeur dans le registre B                     |
| LOAD_A_B                               | Copie le registre B dans le registre A                  |
| LOAD_B_A                               | Copie le registre A dans le registre B                  |
| ADD_A_B                                | Additionne les registres A et B et stocke le résultat dans le registre A |
| ADD_B_A                                | Additionne les registres A et B et stocke le résultat dans le registre B |
| NOT_A                                  | Inverse (complément à 1) le contenu du registre A       |
| NOT_B                                  | Inverse (complément à 1) le contenu du registre B       |
| INC_B                                  | Incrémente le registre B                                |
| JMP [label]                            | Saut à l’instruction étiquetée par [label]              |

>**Remarque** : dans toutes les instructions, le premier registre est le registre destination.


#### Sans utiliser la simulation, mais en analysant uniquement le code binaire de chaque instruction, donnez en assembleur le programme contenu dans la mémoire. Que fait ce programme ?

| Adresse<br>mémoire | Contenu<br>mémoire | Code instruction<br>(en binaire) | Instruction<br>(mnémonique assembleur) |
|:----:|:------:|:-----------------------------:|:-------------:|
| 0000 | 140000 | 0001 0100 0000 0000 0000 0000 |   LOAD_A #0   |
| 0001 | 12000a | 0001 0010 0000 0000 0000 1010 |   LOAD_B #10  |
| 0002 | 5c0000 | 0101 1100 0000 0000 0000 0000 |    ADD_A_B    |
| 0003 | 010002 | 0000 0001 0000 0000 0000 0010 |    JMP [2]    |



#### Modifiez le programme contenu dans la mémoire pour avoir le résultat dans B au lieu de A. Cliquez avec le bouton droit sur la mémoire puis choisir Edit Contents. Entrez ensuite le code héxa de chaque instruction dans la bonne case mémoire. La première colonne indique l’adresse de la première case située dans la 2ème colonne (les adresses des cases suivantes dans une même ligne seront déduites mentalement). Testez le bon fonctionnement de votre programme à l’aide d’une simulation.

| Adresse<br>mémoire | Contenu<br>mémoire | Code instruction<br>(en binaire) | Instruction<br>(mnémonique assembleur) |
|:----:|:------:|:-----------------------------:|:-------------:|
| 0000 | 140000 | 0001 0100 0000 0000 0000 0000 |   LOAD_A #0   |
| 0001 | 12000a | 0001 0010 0000 0000 0000 1010 |   LOAD_B #10  |
| 0002 | 5a0000 | 0101 1010 0000 0000 0000 0000 |    ADD_B_A    |
| 0003 | 010002 | 0000 0001 0000 0000 0000 0010 |    JMP [2]    |



### Ecrire un programme qui charge dans le registre A une valeur de votre choix puis calcule dans le registre B son
complément à 2 (complément à 1 + 1)

| Adresse<br>mémoire | Contenu<br>mémoire | Code instruction<br>(en binaire) | Instruction<br>(mnémonique assembleur) |
|:----:|:------:|:-----------------------------:|:-------------:|
| 0000 | 14000a | 0001 0100 0000 0000 0000 1010 |   LOAD_A #10  |
| 0001 | 1a0000 | 0001 1010 0000 0000 0000 0000 |    LOAD_B_A   |
| 0002 | c20000 | 1100 0010 0000 0000 0000 0000 |     NOT_B     |
| 0003 | 320000 | 0011 0010 0000 0000 0000 0000 |     INC_B     |



## 2. Microprocesseur avec sauts conditionnels


#### Sur combien de bits est codée une instruction ?

>Une instruction est codée sur 32 bits. (cf.mémoire programme)


#### Sur combien de bits est codé le code opératoire ?

>Le code opératoire est codé sur 16 bits. (bits restants)


#### Sur combien de bits est codé le champ DATA ou adresse ?

>Une addresse est codée sur 16 bits. (cf.mémoire programme)


#### A quoi servent les 4 bits JMPZ, JMPNZ, JMPN, JMPPZ ?

| Position |   Nom   |                          Rôle                          |
|:--------:|:-------:|--------------------------------------------------------|
|  Bit 11  |  JMPZ   |Saute à l'instruction lorsque le résultat est nul.      |
|  Bit 10  |  JMPNZ  |Saute à l'instruction lorsque le résultat n'est pas nul.|
|  Bit 9   |  JMPN   |Saute à l'instruction lorsque le résultat est négatif.  |
|  Bit 8   |  JMPPZ  |Saute à l'instruction lorsque le résultat est positif.  |



#### Donnez les codes binaires et hexa des instructions suivantes :

|    Instruction    |     Code Binaire    |Code Hexa|
|-------------------|:-------------------:|:-------:|
| NOP               | 0000 0000 0000 0000 |  0000   |
| LOAD_A #valeur    | 0000 0000 0001 0100 |  0014   |
| LOAD_B_A          | 0000 0000 0001 1010 |  001A   |
| MUL_A_B           | 0000 0000 1000 1100 |  008C   |
| DEC_B             | 0000 0000 0100 0010 |  0042   |
| JMP [label]       | 0000 0000 0000 0001 |  0001   |
| JMPZ [label]      | 0000 1000 0000 0000 |  0800   |
| JMPNZ [label]     | 0000 0100 0000 0000 |  0400   |


#### En utilisant les instructions ci-dessus, écrire un programme qui charge dans le registre A la valeur 5 puis calcule sa factorielle. Vous donnerez 2 versions. La première version utilisera l’instruction JMPNZ. La deuxième version utilisera les instructions JMPZ et JMP. Ecrivez les codes hexa trouvés dans la mémoire puis testez à l’aide de simulations.

**1ère version (JMPNZ)**
| Addresse |               Instruction              | Contenu mémoire |
|:--------:|:--------------------------------------:|:---------------:|
|   0000   | LOAD_A #0005                           |    0014 0005    |
|   0001   | LOAD_B_A                               |    001A 0000    |
|   0002   | DEC_B                                  |    0042 0000    |
|   0003   | MUL_A_B                                |    008C 0000    |
|   0004   | DEC_B                                  |    0042 0000    |
|   0005   | JMPNZ #0003                            |    0400 0003    |
|   0006   | NOP                                    |    0000 0000    |

**2ème version (JMPZ et JMP)**
| Addresse |               Instruction              | Contenu mémoire |
|:--------:|:--------------------------------------:|:---------------:|
|   0000   | LOAD_A #0005                           |    0014 0005    |
|   0001   | LOAD_B_A                               |    001A 0000    |
|   0002   | DEC_B                                  |    0042 0000    |
|   0003   | JMPZ #0006                             |    0800 0006    |
|   0004   | MUL_A_B                                |    008C 0000    |
|   0005   | JMP #0002                              |    0001 0002    |
|   0006   | NOP                                    |    0000 0000    |



## 3. Microprocesseur a 3 registres

#### Chargez le circuit micro3.circ. Ce microprocesseur comporte un registre supplémentaire (le registre C) et un multiplexeur supplémentaire (MUX1). Le code opératoire des instructions utilise 2 bits supplémentaires par rapport au précédent :

- Registre C (bit 13 du code opératoire) : permet d’activer l’écriture du résultat dans le registre C
- MUX1 (bit 12 du code opératoire): permet de choisir quoi envoyer à l’entrée I1 de l’UAL :
    - le registre B si MUX1=0
    - le registre C si MUX1=1


#### Donnez les codes binaires et hexa des instructions suivantes :

|    Instruction    |     Code Binaire    |Code Hexa|
|:-----------------:|:-------------------:|:-------:|
| LOAD_A #valeur    | 0000 0000 0001 0100 |  0014   |
| LOAD_B #valeur    | 0000 0000 0001 0010 |  0012   |
| LOAD_A_B          | 0000 0000 0010 0100 |  0024   |
| LOAD_B_C          | 0001 0000 0010 0010 |  1022   |
| LOAD_C_A          | 0010 0000 0001 1000 |  2018   |
| ADD_C_AB          | 0010 0000 0101 1000 |  2058   |
| JMP [label]       | 0000 0000 0000 0001 |  0001   |


#### En utilisant les instructions précédentes, écrire un programme qui charge dans le registre A la valeur 3 et dans le registre B la valeur 5 puis permute le contenu des 2 registres A et B. On utilisera le registre C comme variable temporaire pour effectuer la permutation. Ecrivez les codes hexa trouvés dans la mémoire puis testez à l’aide de simulations.

| Addresse |               Instruction              | Contenu mémoire |
|:--------:|:--------------------------------------:|:---------------:|
|   0000   | LOAD_A #0003                           |    0014 0003    |
|   0001   | LOAD_B #0005                           |    0012 0005    |
|   0002   | LOAD_C_A                               |    2018 0000    |
|   0003   | LOAD_A_B                               |    0024 0000    |
|   0004   | LOAD_B_C                               |    1022 0000    |
|   0005   | NOP                                    |    0000 0000    |
|   0006   | NOP                                    |    0000 0000    |


#### On considère la suite de Fibonacci : 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ... dans laquelle chaque élément est obtenu en faisant la somme des 2 éléments précédents (hormis les 2 premiers). Ecrire un programme qui charge dans le registre A la valeur 0 et dans le registre B la valeur 1 puis calcule dans le registre C les valeurs de la suite de Fibonacci à l’infini. Ecrivez les codes hexa trouvés dans la mémoire puis testez à l’aide de simulations.

| Addresse |               Instruction              | Contenu mémoire |
|:--------:|:--------------------------------------:|:---------------:|
|   0000   | LOAD_A #0000                           |    0014 0000    |
|   0001   | LOAD_B #0001                           |    0012 0001    |
|   0002   | ADD_C_AB                               |    2058 0000    |
|   0003   | LOAD_A_B                               |    0024 0000    |
|   0004   | LOAD_B_C                               |    1022 0000    |
|   0005   | JMP #0002                              |    0001 0002    |
|   0006   | NOP                                    |    0000 0000    |



## Exercices bonus

### Somme des n premiers entiers

#### Charger le circuit micro1.circ. En utilisant le circuit micro1.circ, écrire un programme qui charge dans le registre A une valeur n de votre choix puis affiche dans le registre B la somme des n premiers entiers 1+2+3+...+n en utilisant la formule n*(n+1)/2. Vous pouvez définir les instructions qu’il vous faut. Le programme nécessite 5 instructions. Après avoir écrit le programme en assembleur, trouvez les codes héxa des instructions et rentrez les dans la mémoire programme du circuit puis testez votre programme.

|      Instruction     | Code Hexa |               Explication               |
|:--------------------:|:---------:|:----------------------------------------|
| LOAD_A #0A           |    140A   | On enregistre n dans le registre A      |
| LOAD_B_A             |    1A00   | On copie n dans le registre B           |
| INC_B                |    3200   | On incrémente le registre B (n+1)       |
| MUL_B_A              |    8A00   | On multiple les registres A et B n*(n+1)|
| DIV_B #02            |    9202   | On divise le registre B par 2           |


