/// Lab5 Sorting and complexity
/// 2022-2023
/// tris.cpp file

#include <iostream>
#include "vectorSort.h"
#include "tools.h"

using namespace std;

/// Initialization according to the parameters chosen by the user
void VectorSort::initVector(int nbVal, Order myOrder, int maxVal)
{
    /// delete the elements of the vector used for the previous sort
    _v.clear(); /// or _v.resize(0);

    /// generate a vector of nvVal random integers between 0 and maxVal
    randomVector(_v, nbVal, 0, maxVal);

    /// put it in the desired order
    int nbWriteTmp=0, nbCompareTmp=0; /// necessary for the call of the sorting functions
    switch (myOrder)
    {
    case RANDOM : break;
    case ASCENDING: selectionSort(nbWriteTmp, nbCompareTmp); break;
    case DECREASING: selectionSortReverse(nbWriteTmp, nbCompareTmp); break;
    default : throw(string("initVector : ERROR unknown sorting criterion")); break;
    }
}

/// Display a vector of integers.
void VectorSort::display()const
{
    for (int i=0; i<_v.size(); i++)
    {
        cout << _v[i] << " ";
        /// method at() not needed because no erroneous access possible here
    }
    cout << endl;
}

/// Apply the sorting algorithm chosen by the user.
/// Count the number of write (to the vector) and compare (between elements of the vector) operations.
void VectorSort::mySort(Algorithm myAlgorithm, int & nbWrite, int & nbCompare)
{
    switch (myAlgorithm)
    {
    case SELECTION :
        cout << "\n... SELECTION Sort... " << endl;
        selectionSort(nbWrite, nbCompare);
        break;
    case BUBBLE :
        cout << "\n... BUBBLE Sort ... " << endl;
        bubbleSort(nbWrite, nbCompare);
        break;
    case BUBBLE_OPT :
        cout << "\n... Optimized BUBBLE Sort ... " << endl;
        bubbleSortOpt(nbWrite, nbCompare);
        break;
    case QUICK:
        cout << "\n... QUICK sort ... " << endl;
        quickSort(nbWrite, nbCompare);
        break;
    default :
        break;
    }
}

/// Exchange two positions in a vector.
void VectorSort::exchange(int ind1, int ind2, int & nbWrite)
{
    int tmp = _v.at(ind1);
    _v.at(ind1) = _v.at(ind2);
    _v.at(ind2) = tmp ;
    /// 1 exchange counts for 2 write operations (we count the write operations on the vector)
    nbWrite+=2;
}

/****** SELECTION SORT  ******/

/// For each position, search for the element which must be assigned to it and place it there.
/// Order : from the smallest to the biggest.
void VectorSort::selectionSort(int & nbWrite, int & nbCompare)
{
    int minInd ;
    for (int pos=0; pos<_v.size()-1; pos++)
    {
        minInd = selection(pos, _v.size()-1, nbWrite, nbCompare);
        if(minInd != pos)
        {
            exchange(pos, minInd, nbWrite);
        }
    }
}

/// For each position, search for the element which must be assigned to it and place it there.
/// Order : from the biggest to the smallest.
void VectorSort::selectionSortReverse(int & nbWrite, int & nbCompare)
{
    int maxInd ;
    for (int pos=0; pos<_v.size()-1; pos++)
    {
        maxInd = selectionMax(pos, _v.size()-1, nbWrite, nbCompare);
        if(maxInd != pos)
        {
            exchange(pos, maxInd, nbWrite);
        }
    }
}

///Search for the index of the smallest element between indStart and indEnd.
int VectorSort::selection(int indStart, int indEnd, int & nbWrite, int & nbCompare)
{
    int minInd = indStart;
    for (int ind=indStart+1; ind<=indEnd; ind++)
    {
        nbCompare++;
        if (_v.at(minInd) > _v.at(ind))
        {
            minInd=ind;
        }
    }
    return(minInd);
}

///Search the index of the largest element between indStart and indEnd
int VectorSort::selectionMax(int indStart, int indEnd, int & nbWrite, int & nbCompare)
{
    int maxInd = indStart;
    for (int ind=indStart+1; ind<=indEnd; ind++)
    {
        nbCompare++;
        if (_v.at(maxInd) < _v.at(ind))
        {
            maxInd=ind;
        }
    }
    return(maxInd);
}

/****** QUICK SORT ******/

/// Execution of the quick sort
void VectorSort::quickSort(int & nbWrite, int & nbCompare)
{
    quickSortAux(0, _v.size()-1, nbWrite, nbCompare);
}

/// Partition the vector and recursively call quickSortAux on the left and right parts of the vector.
void VectorSort::quickSortAux(int start, int finish, int & nbWrite, int & nbCompare)
{
    if (start<finish) /// stop case for recursion
    {
        int pivot = myPartition(start, finish, nbWrite, nbCompare); /// division of the array
        quickSortAux(start, pivot-1, nbWrite, nbCompare); /// sort part 1
        quickSortAux(pivot+1, finish, nbWrite, nbCompare); /// sort part 2
    }
}

/// Partition the vector (elements below a pivot to its left and elements above to its right).
/// Return the index of the pivot.
int VectorSort::myPartition(int start, int finish, int & nbWrite,int & nbCompare)
{
    int counter = start;
    int pivot = _v.at(start);
    int i;

    for (i=start+1; i<=finish; i++)
    {
        nbCompare++;
        if (_v.at(i) < pivot) /// if element less than pivot
        {
            counter++; /// increments counter to the final place of the pivot
            exchange(counter, i, nbWrite); /// element positioned
        }
    }
    exchange(counter, start, nbWrite); /// the pivot is placed
    return counter; /// and its position is returned
}

/****** BUBBLE SORT  ==> to do ******/

void VectorSort::bubbleSort(int& nbWrite, int& nbCompare)
{
    int temp;

    for (int n=1; n<_v.size(); n++)
    {
        for (int i=0; i<_v.size()-n; i++) {
            // if not in right order exchange
            nbCompare++;
            if (_v[i] > _v[i+1]) exchange(i, i+1, nbWrite);
        }
    }
}


/****** OPTIMIZED BUBBLE SORT ==> to you ******/
void VectorSort::bubbleSortOpt(int & nbWrite, int & nbCompare)
{
    int temp, nbExchangeCurrent;

    for (int n=1; n<_v.size(); n++)
    {
        nbExchangeCurrent = 0;
        for (int i=0; i<_v.size()-n; i++) {
            nbCompare++;
            // if not in right order exchange
            if (_v[i] > _v[i+1]) {
                nbExchangeCurrent++;
                exchange(i, i+1, nbWrite);
            }
        }
        // if no changes made, then vector is sorted
        if (nbExchangeCurrent == 0) break;
    }
}

