#include <iostream>
#include <stdlib.h>     // rand, srand
#include <stdexcept>    // invalid_argument

#include "chocolate.h"
using namespace std;


// ---------- CHOCOLATE CLASS ----------

// empty constructor
Chocolate::Chocolate()
{
    _type = 0;
    _weight = 30;
}


// regular constructor
Chocolate::Chocolate(const string &type, const int &weight)
{
    _type = _stringToType(type);
    _weight = weight;
}


// type only constructor (random type)
Chocolate::Chocolate(const string& type)
{
    _type = _stringToType(type);
    _weight = 30;
}


// weight only constructor (random type)
Chocolate::Chocolate(const int &weight)
{
    _type = rand() % NBTYPES;
    _weight = weight;
}


// theWeight
int Chocolate::theWeight()const
{
    return _weight;
}


// theType
string Chocolate::theType()const
{
    return _typeToString(_type);
}


// typeToString
string Chocolate::_typeToString(int n)const
{
    // returns the string equivalent to the type
    if (n!=0 && n!=1 && n!=2) throw invalid_argument ("String is of incorrect type.\n\t\t\tTypes are "+BLACK+", "+WHITE+", "+MILK+".");
    string s;

    switch(n){
        case 0: s="black";break;
        case 1: s="white";break;
        case 2: s="milk";break;
    }
    return s;
}


// stringToType
int Chocolate::_stringToType(string n)const
{
    // returns the type equivalent to the string
    if (n != BLACK && n != WHITE && n != MILK) throw invalid_argument ("String is of incorrect type.\n\t\t\tTypes are "+BLACK+", "+WHITE+", "+MILK+".");
    int type;
    if (n==BLACK) type = CBLACK;
    else if (n==WHITE) type = CWHITE;
    else type = CMILK;
    return type;
}


// operator overloading
ostream& operator <<(ostream& os, const Chocolate& choco)
{
    os << choco.theType() << ", " << choco.theWeight() << "g";
    return os;
}

bool operator <(const Chocolate& c1, const Chocolate& c2)
{
    return c1.theWeight() < c2.theWeight();
}

bool operator >(const Chocolate& c1, const Chocolate& c2)
{
    return c1.theWeight() > c2.theWeight();
}

bool operator ==(const Chocolate& c1, const Chocolate& c2)
{
    return (c1.theType() == c2.theType() && c1.theWeight() == c2.theWeight());
}

// ---------- END OF CHOCOLATE CLASS ----------



// ---------- CHOCOLATEBOX CLASS ----------

ChocolateBox::ChocolateBox()
{
    _name = "classic";
    _content = {};
}


ChocolateBox::ChocolateBox(const string& name, const int& nbChoc)
{
    _name = name;
    for (int i=0; i<nbChoc; i++)
    {
        _content.push_back(Chocolate(20));
    }
}


void ChocolateBox::addChocolate(const Chocolate& choco)
{
    _content.push_back(choco);
}

void ChocolateBox::eatChocolate()
{
    _content.pop_back();
}


// operator overloading
ostream& operator <<(ostream& os, const ChocolateBox& cbox)
{
    os << cbox.theName() << ":\n";
    vector<Chocolate> chocos = cbox.theContent();
    for (int i=0; i<chocos.size(); i++)
    {
        os << "\t" << chocos[i] << endl;
    }
    return os;
}

void operator +=(ChocolateBox& cbox, const Chocolate& choco)
{
    cbox.addChocolate(choco);
}

ChocolateBox operator +(ChocolateBox& cbox, const Chocolate& choco)
{
    cbox.addChocolate(choco);
    return cbox;
}


// ---------- END OF CHOCOLATEBOX CLASS ----------
