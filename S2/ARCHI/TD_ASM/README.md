# TD Assembleur x64

### Ash Merienne · 2C

<br>

## Registres

- Quel est le lien entre le registre RAX et le registre EAX ?

    >Le registre RAX contient les 32 bits de poids faible de EAX.


- Quel est le lien entre le registre EAX et le registre AX ?

    >Le registre AX contient les 16 bits de poids faible de EAX.


- Quel est le lien entre le registre AX et les registres AH et AL ?

    >AH et AL contiennent chacun 1 octet (respectivement de poids fort et de poids faible) qui constituent le registre AX.


- Sachant qu’une case mémoire peut contenir 8 bits, quelle est en octets, la  taille de l'espace mémoire qu'on peut adresser avec une adresse codée sur 16 bits ?

    >2<sup>16</sup> = 2<sup>10</sup> * 2<sup>6</sup> = 2<sup>6</sup> Kib = 64 Kio


- Même question pour une adresse codée sur 20 bits ?

    >2<sup>20</sup> = 1 Mio


- Même question pour une adresse codée sur 32 bits ?

    >2<sup>32</sup> = 2<sup>30</sup> * 2 = 2 Gio


- Même question pour une adresse codée sur 64 bits ?

    >2<sup>64</sup> = 2<sup>60</sup> * 2<sup>4</sup> = 16 Eio


- Si le registre 64 bits RAX contient (ABCDEF0123456789) 16 , quel est le contenu en hexa des registres
suivants :

    >AH = (67)<sub>16</sub> \
    AL = (89)<sub>16</sub> \
    AX = (6789)<sub>16</sub> \
    EAX = (2345 6789)<sub>16</sub>


- Le registre RSP (Stack Pointer) sert à pointer vers la prochaine case libre de la pile. Quelle est le rôle de la pile ?

    >La pile a pour rôle d'ordonner les données éphémères pour l'éxecution d'instructions


- Que contient le registre des indicateurs EFL ? Quand est-il modifié ?

    >Le registre EFL contient les flags, il est modifié quand l'opération de l'UAL rencontre certains critères. 


## Premier programme

main.cpp

    #include <iostream>
    using namespace std;

    extern "C" int somme(int a, int b);

    int main()
    {
        int a, b;
        cout << "Entrez un premier entier : "; cin >> a;
        cout << "Entrez un deuxieme entier : "; cin >> b;
        cout << a << " + " << b << " = " << somme(a,b) << endl;
        return 0;
    }


prog.asm

    .CODE

        somme PROC

            MOV EAX,ECX
            ADD EAX,EDX

            RET
        
        somme ENDP
    
    END


- Expliquez le rôle de la ligne extern "C" int somme(int a, int b);

    >La ligne sert à appeler et compiler la fonction ```somme``` du fichier ```prog.asm``` en utilisant la norme d'appel du langage ```C```.


- Que définissent les lignes .CODE et END ?

    >Les lignes ```.CODE``` et ```END``` 


- Que définissent les lignes somme PROC et somme ENDP ?

    >Ces lignes indiquent le début et la fin de la fonction ```somme```


- Quelle est la taille en bits des paramètres a et b et quels registres sont utilisés pour transmettre leurs valeurs à la fonction somme ?

    >Les paramètres ```a``` et ```b``` sont définis dans le fichier ```C++``` : ```int``` est de longueur ```32 bits```, donc les registres utilisés pour les stocker sont de taille ```32 bits```.

    >Selon la norme d'appel ```FastCall``` de ```Microsoft```, les valeurs sont passées dans les registres ```ECX``` et ```EDX```.


- Quelle est la taille en bits de la valeur retournée par la fonction somme et quel registre est utilisé pour cela ?

    >Selon la norme d'appel ```FastCall``` de ```Microsoft```, le registre de la valeur de retour sera ```EAX```, qui contient ```32 bits```.


- Que fait l’instruction MOV EAX,ECX ?

    >Cette instruction copie le contenu du registre ```ECX``` dans le registre ```EAX```.


- Que fait l’instruction ADD EAX,EDX ?

    >Cette instruction additionne les registres ```EAX``` et ```EDX``` et stocke le résultat dans ```EAX```.

<br>

## Programme avec section de données

    .DATA
            var1    BYTE    5
            var2    WORD    8
            var3    DWORD   7
            var4    QWORD   9
            var5    TBYTE   3
            var6    REAL4   6.25
            var7    REAL8   2.5
            var8    DB      2
            var9    DB      '2'
            var10   DB      -2, 128, -128, 12
            var11   DB      'abc',0
            var12   DB      ?

    .CODE
            fct PROC

                MOV AL,var1
                MOV BL,var8
                ADD AL,BL
                MOV var12,AL

                RET

            fct ENDP
    END

- Quel est le rôle de la ligne .DATA ?

    >La ligne ```.DATA``` a pour rôle de déclarer et initialiser des ```variables```.


| Variable |--- Adresse de début--- | Nombre d'octets |   Contenu des octets en hexa   |
|:--------:|:----------------------:|:---------------:|:-------------------------------|
| var1     | 0x0000 7FF7 5E93 E080  | 1               | 05                             |
| var2     | 0x0000 7FF7 5E93 E081  | 2               | 08 00                          |
| var3     | 0x0000 7FF7 5E93 E083  | 4               | 07 00 00 00                    |
| var4     | 0x0000 7FF7 5E93 E087  | 8               | 09 00 00 00 00 00 00 00        |
| var5     | 0x0000 7FF7 5E93 E08F  | 10              | 03 00 00 00 00 00 00 00 00 00  |
| var6     | 0x0000 7FF7 5E93 E099  | 4               | 00 00 C8 40                    |
| var7     | 0x0000 7FF7 5E93 E09D  | 8               | 00 00 00 00 00 00 04 40        |
| var8     | 0x0000 7FF7 5E93 E0A5  | 1               | 02                             |
| var9     | 0x0000 7FF7 5E93 E0A6  | 1               | 32                             |
| var10    | 0x0000 7FF7 5E93 E0A7  | 4               | FE 80 80 0C                    |
| var11    | 0x0000 7FF7 5E93 E0AB  | 4               | 61 62 63 00                    |
| var12    | 0x0000 7FF7 5E93 E0AF  | 1               | 00                             |


- Que remarquez-vous en général pour toutes les variables ?

    >On remarque que ...


- Que remarquez-vous en particulier pour la variable var10 ?

    >On remarque que ...


- Quel autre type peut-on utiliser à la place respectivement de BYTE, WORD, DWORD, QWORD, TWORD ?

    >On peut également utiliser ...


- Le tableau ci-dessous indique le code machine généré pour les instructions du programme.

|     Adresse mémoire     |    Code machine    |   Instruction   |
|:-----------------------:|:------------------:|:----------------|
| 0x0000 7FF7 5E93 1CD0   | 8A 05 AA C3 00 00  | MOV AL, var1    |
| 0x0000 7FF7 5E93 1CD6   | 8A 1D C9 C3 00 00  | MOV BL, var8    |
| 0x0000 7FF7 5E93 1CDC   | 02 C3              | ADD AL, BL      |
| 0x0000 7FF7 5E93 1CDE   | 88 05 CB C3 00 00  | MOV var12, AL   |

Sachant que :

a) L’adresse de l’instruction ```MOV AL, var1``` est ```0x00007FF75E931CD0``` \
b) L’adresse de la variable ```var1``` est ```0x00007FF75E93E080``` \
c) Le code opératoire de l’instruction ```MOV AL, [variable]``` est ```8A 05```

- Comment pouvez-vous expliquer que le champ DATA ou Adresse de cette instruction est égal à AA C3 00 00 ?

    >On calcule l'écart relatif : \
    0x0000 7FF7 5E93 E080 - 7FF7 5E93 1CD6 = 0x0000 0000 0000 C3AA \
    Or, puisque l'on est en architecture ```little indian```, on renverse le résultat : \
    ```AA C3 00 00```


- Que fait ce programme ?

    >Ce programme renvoie 1 si tu es majeur·e, 0 si tu es mineur·e.

<br>

## Si ... alors ... sinon

main.cpp

    #include <iostream>
    using namespace std;
    
    extern "C" unsigned char majorite(unsigned int age);
    
    int main()
    {
        unsigned int age;
        cout << "Quel est votre age ? : "; cin >> age;
        if (majorite(age)) cout << "Vous etes Majeur." << endl;
        else cout << "Vous etes Mineur." << endl;
        return 0;
    }

prog.asm

    1   .CODE
    2
    3       majorite    PROC
    4
    5           si_supegal18 :  CMP ECX,18
    6                           JAE alors_majeur
    7
    8           sinon_mineur :  MOV AL,0
    9                           JMP fin_si
    10
    11          alors_majeur :  MOV AL,1
    12
    13          fin_si       :  RET
    14
    15      majorite    ENDP
    16
    17  END

a) Comparer les instructions en lignes 6 et 9.

>a


b) Que fait le programme ?

>a

<br>

## Boucles et tableaux

- Comment est déclaré le deuxième tableau tab_dest ?

    >On duplique sur 10 cases une valeur non initialisée.


- Quels sont les registres qui sont utilisés comme indice pour ces tableaux ?

    >a


- Quel est le rôle de la ligne 9 ?

    >a


- Quel est le rôle des lignes 11 et 12 ?

    >a


- Pourquoi les indices ```RSI``` et ```RDI``` sont-ils multipliés par 4 dans les lignes 12, 19 et 20 ?

    >On avance de 4 octets dans le tableau (1 case).


- Expliquez comment fonctionne la division de la ligne 14.

    >```DIV EBX``` : \
    ```EAX``` = Quotient \
    ```EDX``` = Reste


- Quel est le rôle de la ligne 17 ?

    >La ligne 17 sert à incrémenter ```RSI```.


- Est-il possible de remplacer les 2 instructions des lignes 19 et 20 par une seule instruction ```MOV tab_dest[RDI*4], tab_src[RSI*4]``` ?

    >Non, car on ne peut coder qu'un seul décalage à la fois, on est obligé d'utiliser un registre.


- Quel est le rôle de la ligne 25 ?

    >La ligne 25 permet de revenir dans la boucle une fois la case incrémentée, sauf si on est au bout du tableau (CMP RSI,9).


- Quel est le contenu du tableau tab_dest à la fin du programme ?

    >15, 99, 45, 51, 3, 75, 0, 0, 0, 0


- Expliquez ce que fait le programme.

    >Le programme ci-dessus utilise un tableau ```tab_src``` de dix nombres, recherche les multiples de 3 et les copie dans un autre tableau ```tab_dest```.


- Que faudrait-il changer dans le programme si on veut qu’il fonctionne aussi avec des valeurs négatives ?

    >Il faudra utiliser ```IDIV``` au lieu de ```DIV```, et il faudrait initialiser les bits de poids fort à ```F``` ou ```0``` (extension de bits de poids forts), car ils sont interprétés.

<br>

## A vous de jouer

Soit un nombre entier naturel ```n``` sur ```64 bits```. Ecrire un programme en ```ASM x64``` qui, en utilisant une seule boucle, calcule la somme ```S = 1 + 2 +...+ n``` et le produit ```P = 1 x 2 x...x n```.

.DATA

    n       QWORD   10
    somme   QWORD   0
    produit QWORD   1

.CODE

    sommeproduit    PROC

                    MOV RAX,1
                    MOV RBX,0
                    MOV RCX,1

        loop :      CMP RCX,n
                    JA fin
                    MUL RCX
                    ADD RBX,RCX
                    INC RCX
                    JMP loop

        fin :       MOV produit,RAX
                    MOV somme,RBX
                    
                    RET

    sommeproduit    ENDP

