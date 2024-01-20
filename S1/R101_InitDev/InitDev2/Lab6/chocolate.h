#include <iostream>
#include <vector>

using namespace std;

// Constants
const int       NBTYPES= 3; // number of chocolate types
const float     WEIGHT=30;  // default weight (in grams)
const string    BLACK = "black";
const int       CBLACK = 0;
const string    WHITE = "white";
const int       CWHITE = 1;
const string    MILK = "milk";
const int       CMILK = 2;


class Chocolate{
private:
    int _type;      // 0 dark, 1 white, 2 milk
    int _weight;    // weight of the chocolate
    string _typeToString(int n)const;   // returns the string equivalent to the type
    int _stringToType(string)const;     // returns the type equivalent to the string

public:
    // constructors
    Chocolate();    // empty constructor
    Chocolate(const string&, const int&); // regular constructor
    Chocolate(const int&);   // weight only constructor (random type)
    Chocolate(const string& type); // type only constructor (30g weight)

    // methods
    int theWeight()const;
    string theType()const;
};

// operator overloading
ostream& operator <<(ostream&, const Chocolate&);
bool operator <(const Chocolate&, const Chocolate&);
bool operator >(const Chocolate&, const Chocolate&);
bool operator ==(const Chocolate&, const Chocolate&);



class ChocolateBox{
private:
        string _name; // name of the box
        vector<Chocolate> _content;   //the Chocolates contained in the box
public:
        ChocolateBox();     // empty constructor
        ChocolateBox(const string&, const int&);     // regular constructor
        string theName()const{return _name;};
        vector<Chocolate> theContent()const{return _content;};
        void addChocolate(const Chocolate& choco);
        void eatChocolate(); // yummy!
};

ostream& operator <<(ostream&, const ChocolateBox&);
void operator +=(ChocolateBox&, const Chocolate&);
ChocolateBox operator +(ChocolateBox&, const Chocolate&);


