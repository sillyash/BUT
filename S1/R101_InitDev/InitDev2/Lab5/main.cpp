/// Lab5 Sorting and complexity
/// 2022-2023
/// file main.cpp

#include <cstdlib>
#include <ctime>
#include <stdexcept>
#include <iostream>
#include "vectorSort.h"
#include "tools.h"

/// NOTE: put the 2 display statements in comments when executing sorts with a large
/// number of elements or when executing a large number of sorts

int main()
{
    VectorSort myData; /// the vector of date
    Algorithm myAlgorithm; /// the sorting algorithm
    Order myOrder; /// the order of the data when the vector is initialized
    int nbElts, nbExec, maxVal; /// other sorting parameters
    int nbWrite, nbCompare; /// comparison and writing counters
    int nbWriteTotal, nbCompareTotal;

    do
    {
        nbWriteTotal=0, nbCompareTotal=0;

        /// definition of the sorting parameters
        srand(time(NULL)); /// initialize rand
        sortParameters(nbElts, maxVal, nbExec, myOrder, myAlgorithm);

        try
        {
            /// execute nbExec times the desired sorting algorithm
            for(int i = 0; i<nbExec; i++)
            {
                cout<<"************************************************************"<<endl;
                /// the counters are reset to 0 for each execution
                nbWrite=0;
                nbCompare=0;

                /// initialize the vector according to the user's choices
                myData.initVector(nbElts, myOrder, maxVal);
                cout << "Vector before sorting:\n\t";
                myData.display();

                /// sort the vector
                myData.mySort(myAlgorithm, nbWrite, nbCompare);
                cout << "\nVector after sorting:\n\t";
                myData.display();
                cout <<"Number of write operations:\t" << nbWrite << endl
                     <<"Number of compare operations:\t" << nbCompare << endl;

                /// update the total number of write and compare operations
                nbWriteTotal = nbWriteTotal + nbWrite;
                nbCompareTotal = nbCompareTotal + nbCompare;
            }
            cout<<"************************************************************"<<endl;

            ///Calculate and display the average values of the 2 counters
            cout <<"\nAverage number of write operations:\t"<<(float)nbWriteTotal/nbExec<<endl
                 <<"Average number of compare operations:\t"<<(float)nbCompareTotal/nbExec<<endl;
        }
        catch (string mesg)
        {
            cout << mesg << endl;
        }
        catch(out_of_range e)
        {
            cerr << "ERROR: " << e.what() << endl;
        }
    } while (again()); /// let the user start again if she wants to

    return 0;
}


