/** Rational class
 * HB & CB
 *
 *  Class allowing to represent rational numbers
 * The encapsulation guarantees that :
 * - the nominator is always a strictly positive integer
 * - the fraction is always reduced.
 * Thus, the representation of rational numbers is unique.
 **/

#ifndef _RAT_H_
#define _RAT_H_

#include <iostream>
#include <ostream>
#include <cstdlib>

using namespace std;

class Rational
{
    private:
        int _num; // Numerator, positive or negative
        int _den; // Denominator, strictly positive
        // reduction of a fraction: _num / _den becomes irreducible
        void _reduce();

    public:
        // constructor with default values for numerator and denominator
        // check and put in irreducible form
        Rational(int=0, int=1);
        //  Display method, for example 1/2
        void display() const;
        //Returns the numerator
        int getNum() const;
        // Returns the denominator
        int getDen() const;

        Rational operator*(const Rational& a) const;    // Part 1 - Q2
        bool operator==(const Rational& a) const;       // Part 1 - Q3
        Rational operator-() const;                     // Part 1 - Q5
        Rational operator+(const Rational& a) const;    // Part 1 - Q6
        bool operator<(const Rational& a) const;        // Part 1 - Q7

        void input(istream & in);                       // Part 2 - Q1

        bool operator!=(const Rational& a) const;       // Part 3 - Q1
        bool operator<=(const Rational& a) const;       // Part 3 - Q2
};

// Compute the gcd (greatest common divisor) of any two integers
int gcd(int a, int b);

// Compute the gcd (greatest common divisor) of two integers a and b such that a >= b >= 0
int recursiveGCD(int a, int b);

 // Test functions
bool testEmptyConstructorAndGetters();
bool testConstructorParamAndGetters(int n, int d, int nres, int dres, bool exc);
bool testConstructorParamAndGetters();


// Part 1 - Q4
ostream& operator<<(ostream& o, const Rational& r);

// Part 2 - Q2
istream& operator>>(istream& in, Rational& r);

// Part 2 - Q5
Rational operator*(int i, const Rational& r);

#endif
