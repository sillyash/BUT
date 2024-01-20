// HB/CB november 2022 - version for students
// License class, to handle drivers licenses
// license.cpp file
#include <string>
#include <iostream>
#include <stdexcept>
#include "license.h"

using namespace std;


// constructor
/// 11)
License::License(string name, string firstName, string date, string number, int points)
{
    cout << "Construction of a license" << endl;
    _name = name;
    _firstName = firstName;
    _date = date;
    _number = number;
    _nbpoints = points;
    if (_nbpoints > MAXP || _nbpoints < MINP) {
    throw invalid_argument("Number of points isn't correct for "+_firstName+" "+_name+".");
    }
}

// destructor
License::~License() {
  cout << "Destruction of the license of " << _firstName << " " << _name << endl;
  // this->display();
}

bool License::compare(License l2) const
{
    return (_nbpoints == l2._nbpoints);
}

// display of a license
void License::display() const
{
    cout << "Information concerning the license of " << _firstName << " "<< _name << " :" << endl;
    cout << _number << endl;
    cout << _date << endl;
    cout << "Number of points : "<< _nbpoints << endl;
}

// number of points
int License::getNbpoints() const
{
    return _nbpoints;
}

// withdrawal of points
void License::withdrawPoints(int nbPointsWithdrawn)
{
    if (_nbpoints < MINP + nbPointsWithdrawn)
    {
        _nbpoints = MINP;
    }
    else
    {
        _nbpoints -= nbPointsWithdrawn;
    }
}

// name of the owner
string License::getName() const
{
    return _name;
}

// incrementation of points
void License::incrementPoints()
{
    if (_nbpoints >= MAXP)
    {
        throw invalid_argument("Maximum already reached!");
    }
    else
    {
        _nbpoints++;
    }
}

// decrementation of points
void License::decrementPoints()
{
    if (_nbpoints <= MINP)
    {
        throw invalid_argument("Minimum already reached! License lost...");
    }
    else
    {
        _nbpoints--;
    }
}

void addPoints(License& current_lic, int points) {
    if (current_lic.getNbpoints() + points > MAXP)
    {
        while (current_lic.getNbpoints() < MAXP)
        current_lic.incrementPoints();
    }
    else
    {
        while (points > 0) {
            current_lic.incrementPoints();
            points--;
        }
    }
}


