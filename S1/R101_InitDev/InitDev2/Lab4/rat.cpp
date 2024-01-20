#include <iostream>
#include <stdexcept>
#include <ostream>
#include "rat.h"
using namespace std;

Rational::Rational(int num, int den)
{
    if (den==0) throw invalid_argument("Null denominator");
    if (den > 0)
    {
        _num = num;
        _den = den;
    }
    else
    {
        _num = -num;
        _den = -den;
    }
    _reduce();
}

void Rational::_reduce()
{
    int factor = gcd(_num, _den); // common factor
    _num /= factor;
    _den /= factor;
}

void Rational::display() const
{
    cout << _num << '/' << _den;
}

int Rational::getNum() const
{
    return _num;
}

int Rational::getDen() const
{
    return _den;
}

int gcd(int a, int b)      // uses recursiveGCD
{
    int a2 = (a > 0) ? a : -a;
    if (a2 < b)
    {
        int temp = a2;
        a2 = b;
        b = temp;
    }
    return recursiveGCD(a2, b);
}

int recursiveGCD(int a, int b)      // uses Euclide's algorithm
{
    return (b==0) ? a : recursiveGCD(b, a%b);
}

// Test functions
bool testEmptyConstructorAndGetters()
{
    bool ok = true;
    Rational r;
    if (!(r.getNum() == 0))
    {
        ok = false;
        cout << "Error in the empty constructor or getNum : the numerator is " << r.getNum()
            << " (expected : 0)" << endl;
    }
    if (!(r.getDen() == 1))
    {
        ok = false;
        cout << "Error in the empty constructor or getDen : the denominator is " << r.getDen()
            << " (expected : 1)" << endl;
    }
    return ok;
}

bool testConstructorParamAndGetters(int n, int d, int nres, int dres, bool exc)
{
    bool ok = true;
    if (exc)
    {
        try
        {
            Rational r(n, d);
            ok = false;
            cout << "Error in the constructor with parameters : an exception should have been thrown" << endl;
        }
        catch (const invalid_argument &) { }
    }
    else
    {
        try
        {
            Rational r(n,d);
            if (!(r.getNum() == nres))
            {
                ok = false;
                cout << "Error in the constructor with parameters or getNum : the numerator is "
                    << r.getNum() << " (expected : " << nres << ")" << endl;
            }
            if (!(r.getDen() == dres))
            {
                ok = false;
                cout << "Error in the constructor with parameters or getDen : the denominator is "
                    << r.getDen() << " (expected : " << dres << ")" << endl;
            }
        }
        catch (const invalid_argument &e)
        {
            ok = false;
            cout << "Error in the constructor with parameters: the exception  " << e.what()
                << " has been thrown" << endl;
        }
    }
    return ok;
}

bool testConstructorParamAndGetters()
{
    bool ok = true;
    ok = ok && testConstructorParamAndGetters(0, 1, 0, 1, false);
    ok = ok && testConstructorParamAndGetters(1, 2, 1, 2, false);
    ok = ok && testConstructorParamAndGetters(-1, 2, -1, 2, false);
    ok = ok && testConstructorParamAndGetters(1, -2, -1, 2, false);
    ok = ok && testConstructorParamAndGetters(-1, -2, 1, 2, false);
    ok = ok && testConstructorParamAndGetters(2, 4, 1, 2, false);
    ok = ok && testConstructorParamAndGetters(1, 0, 1, 0, true);
    return ok;
}


// ----------------------------------------------------------------

Rational Rational::operator*(const Rational& a) const   // Part 1 - Q2
{
    return Rational((a.getNum()*getNum()), (a.getDen()*getDen()));
}

bool Rational::operator==(const Rational& a) const      // Part 1 - Q3
{
    return ((_num*a.getDen()) == (a.getNum()*_den));
}

ostream& operator<<(ostream& o, const Rational& r)      // Part 1 - Q4
{
    o << r.getNum() << "/" << r.getDen();
    return o;
}

Rational Rational::operator-() const                    // Part 1 - Q5
{
    return Rational(Rational(-_num, _den));
}

Rational Rational::operator+(const Rational& a) const   // Part 1 - Q6
{
    return Rational((_num*a.getDen() + _den*a.getNum()), _den*a.getDen());
}

bool Rational::operator<(const Rational& a) const       // Part 1 - Q7
{
    return ((_num*a.getDen()) < (a.getNum()*_den));
}

void Rational::input(istream & in)                      // Part 2 - Q1
{
    cout << "numerator ?";
    in >> _num;

    do{
        cout << "denominator (non null value) ?";
        in >> _den;
    }
    while(_den == 0);

    if (_den < 0) {
        _num = -_num;
        _den = -_den;
    }
    _reduce();
}

istream& operator>>(istream& in, Rational& r)           // Part 2 - Q2
{
    int num, den;
    in >> num; in >> den;
    r = Rational(num, den);
    return in;
}

Rational operator*(int i, const Rational& r)            // Part 2 - Q5
{
    return Rational(i*r.getNum(), r.getDen());
}

bool Rational::operator!=(const Rational& a) const      // Part 3 - Q1
{
    return ((_num*a.getDen()) != (a.getNum()*_den));
}

bool Rational::operator<=(const Rational& a) const      // Part 3 - Q2
{
    return ((_num*a.getDen()) <= (a.getNum()*_den));
}

