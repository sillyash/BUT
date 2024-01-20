#include <iostream>
#include <vector>
#include "gestionNotes.h"

using namespace std;

/// calcule les moyennes des étudiants et les enregistre dans la case correspondante du vecteur moy
void calcMoyenne(const vector<vector<int>> &notes, vector<float> &moy) {
    float somme;
    for (int i =0;i<notes.size();i++) {
        for (int j=0;j<notes[i].size();j++) {
            moy[i] += notes[i][j];
            moy[i] /= notes[i].size();
        }
    }
}


/// renvoie vrai si cet entier n’est pas égal à une des constantes NBETUD,
/// AFFICHEETUD, AFFICHENOTES, MOY, MAJOR ou QUIT
bool invalide(int c) {
    return (c!=NBETUD && c!=AFFICHENOTES && c!=MOY && c!=MAJOR && c!=QUIT);
}


/// demande une valeur à l'utilisateur et renvoie la valeur du choix.
int menu() {
    int choix;
    do{
        // affichage du menu
        cout<< "\n Que voulez-vous faire  ?" << endl;
        cout << AFFICHENOTES<<"  pour afficher toutes les notes de chaque etudiant."<< endl;
        cout << MOY <<"  pour afficher toutes les moyennes de chaque etudiant."<< endl;
        cout << MAJOR <<"  pour afficher l'etudiant ayant la meilleure moyenne."<< endl;
        cout << QUIT <<"  pour quitter le programme."<< endl;

        //choix de l'utilisateur
        cin >> choix;
        while (invalide(choix)){
            cout << "Choix invalide" << endl;
            cin >> choix;
        }
    }
    while(invalide(choix));
    return choix;
}


/// affiche les notes de chaque étudiant
void afficheNotes(const vector<vector<int>> &notes, const vector<string> &etudiants) {
    for(int i=0; i< NBETUD ; i++){
        cout<<"Les "<<notes[i].size() <<" notes de "<<etudiants[i]<<" :"<<endl;
        for(int j=0; j<notes[i].size(); j++){
            cout << notes[i][j]<<" ";}
            cout << endl << endl;
    }
}


/// affiche la moyenne de chaque étudiant
void afficheMoy(const vector<float> &moy, const vector<string> &etudiants) {
    for(int i=0; i< NBETUD ; i++){
        cout<<"La moyenne de "<<etudiants[i]<<":\t";
        cout << moy[i]<<endl;
    }
}


/// renvoie l'indice de l'étudiant ayant la meilleure moyenne
int meilleurEtud(const vector<float> &moy, const vector<string> &etudiants) {
    int imax=0;
    for(int i=1;i<NBETUD; i++) {
        imax = moy[imax]< moy[i] ? i : imax;
    }
    return imax;
}

