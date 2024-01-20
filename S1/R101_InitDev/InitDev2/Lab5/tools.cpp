/// Lab5 Sorting and complexity
/// 2022-2023
/// tools.cpp file

#include <iostream>
#include "vectorSort.h"
#include "tools.h"

/// return a positive integer entered by the user
int inputInteger()
{
    int integer;
    cin >> integer ;
    while(integer <= 0)
    {
        cout << "A positive number please:\n >>> ";
        cin >> integer ;
    }
    return integer;
}

/// return the initial order of the vector chosen by the user
Order inputOrder()
{
    Order myOrder;
    int number;
    do
    {
        cout<<"Choose the initial ordering of your values:" << endl;
        cout<<"\t1. random" << endl;
        cout<<"\t2. ascending order" << endl;
        cout<<"\t3. descending order\n>>> ";
        cin >> number;
    } while(number< 1 || number > 3);

    switch(number)
    {
    case 1: myOrder=RANDOM; break;
    case 2: myOrder=ASCENDING; break;
    case 3: myOrder=DECREASING; break;
    default: break;
    }
    return myOrder;
}

/// return the sorting algorithm chosen by the user
Algorithm inputAlgorithm()
{
    Algorithm myAlgorithm;
    int number;
    do
    {
        cout<<"Choose the sorting algorithm:" << endl;
        cout<<"\t1. bubble sort" << endl;
        cout<<"\t2. optimized bubble sort " << endl;
        cout<<"\t3. selection sort " << endl;
        cout<<"\t4. quick sort\n>>> ";
        cin >> number ;
    } while(number < 1 || number > 4);

    switch(number)
    {
    case 1: myAlgorithm=BUBBLE; break;
    case 2: myAlgorithm=BUBBLE_OPT; break;
    case 3: myAlgorithm=SELECTION; break;
    case 4: myAlgorithm=QUICK; break;
    default: break;
    }
    return myAlgorithm;
}

/// Input the various parameters needed to sort :
/// number of values, maximum value, number of executions, initial order, sorting algorithm.
void sortParameters(int & nbElts, int & maxVal, int & nbExec, Order & myOrder, Algorithm & myAlgorithm)
{
    cout<<"How many integers to sort?\n>>> ";
    nbElts=inputInteger();
    cout<<"\nMaximum value of integers?\n>>> ";
    maxVal=inputInteger();
    cout<<"\nHow many executions?\n>>> ";
    nbExec=inputInteger(); cout << endl;
    myOrder=inputOrder(); cout << endl;
    myAlgorithm=inputAlgorithm(); cout << endl;
}

/// Create a vector containing nbVal random integers ranging between theMin and theMax
void randomVector(vector<int> &v, int nbVal, int theMin, int theMax)
{
    if (theMax < theMin) /// swap
    {
        int temp = theMax;
        theMax=theMin;
        theMin=temp;
    }
    for (int i=0; i<nbVal; i++)
    {
        v.push_back(theMin + rand()%(theMax-theMin+1)); /// random number between theMin and theMax included
    }
}

/// Ask the user if she wants to start over
bool again()
{
    char answer;
    cout << "\nDo you want to start over? (y/n)\n>>> " ;
    cin >> answer; cout << endl;
    return (answer == 'y');
}


