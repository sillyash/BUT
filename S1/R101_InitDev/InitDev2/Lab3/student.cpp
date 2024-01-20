/// Search Lab, R101-2 2023-2024
/// student.cpp

#include "student.h"

/// Constructor with 4 parameters
Student::Student(int num, const string & name, const string & firstName, const string & group)
{
    if (!checkGroup(group))
        throw invalid_argument("Invalid group number: " + group);

    _name = name;
    _firstName = firstName;
    _num = num;  /// we suppose that student numbers are valid.
    _group = group;
}

/// Display a student
void Student::display() const
{
    cout << _num << " "<< _group  << " " << _name << " "<< _firstName << endl;
}

/// checkGroup : true if numGroup is a valid group number
bool checkGroup(const string & numGroup)
{
    bool found = false;
    int i = 0;
    while (!found && i < NBGROUPS_S1)
    {
        found=numGroup==GROUPS_S1[i];
        i++;
    }
    return(found);
}

