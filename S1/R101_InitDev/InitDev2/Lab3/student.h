/// Search Lab, R101-2 2023-2024
/// student.h

#ifndef STUDENT_H_INCLUDED
#define STUDENT_H_INCLUDED

#include <iostream>
using namespace std;

/// possible groups
/// (checkGroup function returns true if a string is a valid group name)
const string GROUPS_S1[]= {"1A", "1B", "1C", "1D","1E", "1F", "1G", "1H", "1I", "1J", "1K", "1L", "1M" };
const int NBGROUPS_S1 = 13;

class Student
{
private :
    string _name, _firstName;
    int _num;
    string _group;

public :
    Student() {}; /// empty constructor defined inline
    Student(int num, const string & name, const string & firstName, const string & group);
    void display()const;
    string getName()const{return _name;};
    string getGroup()const{return _group;};
};

/// checkGroup: true if a string is a valid group name
bool checkGroup(const string & numGroup);

#endif
