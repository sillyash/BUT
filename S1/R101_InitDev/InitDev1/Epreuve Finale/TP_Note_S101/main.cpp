#include <iostream>
#include "gestionNotes.h"

using namespace std;

int main()
{
    vector<vector<int>> notes (NBETUD);
    vector<float> moyennes (NBETUD,0.0);
    vector<string> etudiants={"Robert","Johnson","Lucy","Emily","Sean","Clarence","Max",
                            "Vladymmir","Franck","Shaun","Thesee","Carla","Eli","Olga",
                            "Freeman","Benjamin","Baxter","Philipps","Taylor","Kewani"};
    int choix;

    /* génération des notes des étudiants
            NE PAS MODIFIER !
    */
    int C = 314 ;
    int n =  C%117 + 53;
    for(unsigned int i=0; i<NBETUD; i++ ){
        int nbnotes = 12 + (C*(i+7))%53;
        for(int  j=0; j< nbnotes ; j++ ){
            notes[i].push_back(n%21);
            n= (17*n +31)% 367;
        }
    }
    /* FIN de la génération des notes */

    /// on calcule les moyennes des étudiants (procédure)
    calcMoyenne(notes, moyennes);

    do {
        choix = menu();
        switch (choix) {
            case AFFICHENOTES:
                afficheNotes(notes, etudiants);
            case MOY:
                afficheMoy(moyennes, etudiants);
            case MAJOR:
                cout << "Le meilleur étudiant est "<<etudiants[meilleurEtud(moyennes, etudiants)]<<" !"<<endl;
            case QUIT:
                cout << "Au revoir !" << endl;
        }
    }
    while(choix!=QUIT);
    return 0;
}
