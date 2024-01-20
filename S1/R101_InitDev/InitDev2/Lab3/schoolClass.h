/// Search Lab, R101-2 2023-2024
/// schoolClass.h

#ifndef SCHOOLCLASS_H_INCLUDED
#define SCHOOLCLASS_H_INCLUDED_H_INCLUDED

#include <iostream>
#include <vector>
#include "student.h"
using namespace std;

class SchoolClass
{
private :
    vector<Student> _students;
    string _name;

public:
    SchoolClass() {}; /// empty constructor defined inline
    void init(const string & studentFile); /// read file
    void display()const; /// display whole school class
    void displayWithBounds(int first, int last) const; /// Display school class between indices first and last
    Student getStudent(int ind) const; /// Return student at position ind of vector
    int searchStudent(const string& group, const string& student) const;
};

#endif // PROMOTION_H_INCLUDED

