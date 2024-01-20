/// Lab5 Sorting and complexity
/// 2022-2023
/// tools.h file.h file

#ifndef VECTEURTRIE_H_INCLUDED
#define VECTEURTRIE_H_INCLUDED

#include <vector>
#include "tools.h"

using namespace std;

class VectorSort
{
    vector<int> _v;

public :

    VectorSort(){} /// empty vector at creation

    /// Initialize the vector according to the parameters chosen by the user
    void initVector(int nbVal, Order myOrder, int maxVal);

    /// Apply the sorting algorithm chosen by the user.
    /// Count the number of write (to the vector) and compare (between elements of the vector) operations.
    void mySort(Algorithm myAlgorithm, int & nbWrite, int & nbCompare);

    /// Exchange two positions in a vector.
    void exchange(int ind1, int ind2, int & nbWrite) ;

    /// Display a vector of integers.
    void display()const;

     /****** BUBBLE SORT ******/
    void bubbleSort(int & nbWrite, int & nbCompare) ;
    void bubbleSortOpt(int & nbWrite, int & nbCompare) ;

    /****** SELECTION SORT  ******/
    void selectionSort(int & nbWrite, int & nbCompare); /// data order: ascending
    void selectionSortReverse(int & nbWrite, int & nbCompare); /// data order : decreasing
    int selection(int first, int last, int & nbWrite, int & nbCompare); /// min search between indDebut and indFin
    int selectionMax(int first, int last, int & nbWrite, int & nbCompare) ; /// search max between indDebut and indFin

    /****** QUICK SORT ******/

    void quickSort(int & nbWrite, int & nbCompare);

    /// Partition the vector and recursively call quickSortAux on the left and right part of the vector.
    void quickSortAux(int first, int last, int & nbWrite, int & nbCompare);

    /// Partition the vector (elements lower than a pivot to its left and elements higher than it to its right).
    /// Return the index of the pivot.
    int myPartition(int first, int last, int & nbWrite,int & nbCompare);

};

#endif // VECTEURTRIE_H_INCLUDED
