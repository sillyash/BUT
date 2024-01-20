/// Lab6, menu.cpp
/// HBM, JL, CTB sept. 2023

#include <iostream>
#include "computations.h"
#include <vector>

using namespace std;

/// returns true if n is positive, else false
bool isPositive(int n) {
    return n>0;
}

int sumInt(int n) {
    // mathematical formula to avoid using a loop ()
    int sum=n*(n+1)/2;
    return sum;
}

int factorial(int n) {
    int fact=1;
    for(int i=1; i<=n; i++) {
        fact *= i;
    }
    return fact;
}

void listDiv(int n) {
    cout << "[";
    // stops at i<=int(n/2) because there is no divider between n/2 and n
    for(int i=1; i<=int(n/2); i++) {
        if((n%i)==0) {cout << i << ",  ";}
    }
    // last divider is n itsalf
    cout << n << "]";
}
