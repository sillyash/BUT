/// Lab6, menu.cpp
/// HBM, JL, CTB sept. 2023

#include <iostream>
#include "computations.h"
#include "isInterval.h"

using namespace std;

int main() {
    tests();
    cout << "POS > 0: " << isPositive(POS) << endl;
    cout << "NEG > 0: " << isPositive(NEG) << endl;
    cout << "ZERO > 0: " << isPositive(ZERO) << endl;

    return 0;
}

/*
TESTS

expected: 1 (POS is positive), 0 (NEG isn't positive), 0 (O isn't positive)

*/
