#include <iostream>
#include "isInterval.h"

using namespace std;

/// returns 'positive' if n is positive, 'negative' if n is negative, else returns 'null'
std::string interval(int n) {
    if(n>0) {return "positive";}
    if(n<0) {return "negative";}
    else {return "null";}
}

void tests() {
    cout << POS << " is " << interval(POS) << endl;
    cout << NEG << " is " << interval(NEG) << endl;
    cout << ZERO << " is " << interval(ZERO) << endl;
}
