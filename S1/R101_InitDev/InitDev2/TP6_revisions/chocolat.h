#ifndef CHOCOLAT_H
#define CHOCOLAT_H
using namespace std;

const int POIDS = 30; // poids par défaut (en gramme)

const string NOIR = "noir";
const int CNOIR = 0;

const string BLANC = "blanc";
const int CBLANC = 1;

const string LAIT = "lait";
const int CLAIT = 2;

class Chocolat {
private:
    int _nature;                                // 0 noir, 1 blanc, 2 lait
    int _poids;                                 // poids du chocolat
    string _natureVersString(int n)const;       // retourne la String équivalente à la nature
    int _stringVersNature(const string&)const;  // retourne la nature équivalente à la string
public:
    Chocolat();
    Chocolat(const string&, const int& poids = POIDS);
    Chocolat(const int&);
    int lePoids() const {return _poids;};
    string laNature() const {return _natureVersString(_nature);};
};

ostream& operator << (ostream&, const Chocolat&);
bool operator < (const Chocolat&, const Chocolat&);

#endif // CHOCOLAT_H
