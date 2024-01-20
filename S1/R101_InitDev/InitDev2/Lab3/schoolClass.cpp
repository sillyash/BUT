/// Search Lab, R101-2 2023-2024
/// schoolClass.cpp

#include <iostream>
#include <vector>
#include <fstream>
#include <cmath>
#include "student.h"
#include "schoolClass.h"

using namespace std;

/// Display whole school class
void SchoolClass::display()const
{
    int nbStudents = _students.size();
    cout << "School class " << _name << " of " << nbStudents << " students : " << endl;
    for (int i=0 ; i<nbStudents ; i++)
    {
        cout << "("<< i << ")  ";
        _students[i].display();
    }
    cout << endl;
}

/// Display school class between indices first and last
/// Propagates exception thrown by at() if invalid index
void SchoolClass::displayWithBounds(int first, int last)const
{
    for (int i=first ; i<= last ; i++)
    {
        cout << "("<< i << ")  ";
        _students.at(i).display(); /// at(i) throws an exception if invalid index
    }
    cout << endl;
}

/// Return student at position ind of vector.
/// Propagates exception thrown by at() if invalid index.
Student SchoolClass::getStudent(int ind) const
{
    return(_students.at(ind)); /// at(i) throws an exception if invalid index
}

/// Initialization of a SchoolClass with data from a file
/// Exception is thrown if error opening file.
/// Content of file : 1st information : name of student classs, then : name, first name, group, num of each student
void SchoolClass::init(const string & studentFile)
{
    /// data in the file
    string name;
    string firstName;
    string group;
    int num;

    /// reading the file
    ifstream ific(studentFile.c_str(), ios::in); /// open the file for reading purposes
    if(!ific.is_open())   /// if problem opening file
        throw string ("Failure while opening file : " + studentFile);
    ific >> _name; /// first information read is the name of the schoolClass, for instance S1
    while(!ific.eof()) /// while not at end of file
    {
        /// read information for each student
        ific >> name;
        ific >> firstName;
        ific >> group;
        ific >> num;
        /// create student and add it to the end of the vector
        _students.push_back(Student(num, name, firstName, group));
    }
    ific.close(); /// close the file
}


int SchoolClass::searchStudent(const string& group, const string& student) const
{
    int index;
    int max_bound = _students.size();
    int min_bound = 0;

    if (_students.size() == 0) {
        throw invalid_argument("Your vector is empty.");
    }

    do {
        index = min_bound + floor((max_bound - min_bound)/2);
        // if we're in the right group
        if (_students[index].getGroup() == group) {
            // if the student is found
            if (_students[index].getName() == student) {
                return index;
            }
            // if the name is too high
            else if (_students[index].getName() > student) {
                max_bound = index;
            }
            // if the name is too low
            else if (_students[index].getName() < student) {
                min_bound = index+1;
            }
        }
        // if the group is too high
        else if (_students[index].getGroup() > group) {
            max_bound = index;
        }
        // if the group is too low
        else if (_students[index].getGroup() < group) {
            min_bound = index+1;
        }
    }
    while(min_bound != max_bound);
    return -1;
}
