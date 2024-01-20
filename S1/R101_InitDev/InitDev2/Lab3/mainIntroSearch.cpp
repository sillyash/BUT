/// Search Lab, exercise 2, december 2023, CB
/// Student version
#include <iostream>
#include <iostream>
#include <vector>
#include <stdexcept>
#include <cmath>

using namespace std;

/// ============== Prototypes and constants
const int STOP = 999;

/// display: displays vector content followed by indices
void display(const vector<int> & v);

/// binarySearch: returns an index at which val is stored in vector v, or -1 if val is not in v
int binarySearch(const vector<int> & v, int val);

/// binarySearchMin: returns the smallest index at which val is in vector v, or -1 if val is not in v
int binarySearchMin (const vector<int> & v, int val);

/// ============== Main program

int main()
{
    vector<int> v1 = {0,1,2,2,2,5,7,7,7,9};
    int val; /// the value that the user wants to search for
    int ind; /// the index returned by the search function

    cout << "Content of the vector : " << endl;
    display(v1);

    cout << "\nWhat value are you looking for? (" << STOP << " to finish) >>> ";
    cin >> val;

    while (val!=STOP)
    {
        cout << "Result of binary search:";
        ind = binarySearch(v1, val);
        if (ind != -1)
            cout << "\t" << val << " is in the vector at index " << ind << endl;
        else
            cout << "\t" << val << " is not in the vector." << endl;

        cout << "Result of binary search min:";
        ind = binarySearchMin(v1, val);
        if (ind != -1)
            cout << "\t" << "The first occurrence of " << val << " is at index "<< ind << endl;
        else
            cout << "\t" << val << " is not in the vector." << endl;

        cout << "\nWhat value are you looking for? >>> ";
        cin >> val;
    }

    return 0;
}

/// ============== Subprogram definitions

/// display: displays vector content followed by indices
void display(const vector<int> & v)
{
    cout << "values : ";
    for (int i=0 ; i<v.size() ; i++)
        cout << v[i] << " " ;
    cout << endl;
    cout << "indices: ";
    for (int i=0 ; i<v.size() ; i++)
        cout << i << " " ;
    cout << endl;
}

/// binarySearch: returns an index at which val is stored in vector v, or -1 if val is not in v
int binarySearch(const vector<int> & v, int val)
{
    int index;
    int min_bound = 0;
    int max_bound = v.size();

    // throw exception if vector is empty
    if (v.size() == 0) {
        throw invalid_argument("Your vector is empty.");
    }

    do {
        index = min_bound + floor((max_bound - min_bound)/2);
        if (v[index] == val) {
            return index;
        }
        else if (v[index] > val) {
            max_bound = index;
        }
        else if (v[index] < val) {
            min_bound = index+1;
        }
    }
    while(min_bound != max_bound);
    return -1;
}

/// binarySearchMin: returns the smallest index at which val is in vector v, or -1 if val is not in v
int binarySearchMin (const vector<int> & v, int val)
{
    int index;
    int min_bound = 0;
    int max_bound = v.size();

    // throw exception if vector is empty
    if (v.size() == 0) {
        throw invalid_argument("Your vector is empty.");
    }

    do {
        index = min_bound + floor((max_bound - min_bound)/2);
        if (v[index] == val) {
            // check if index-1 is still a correct index
            if (index-1 >= 0 && v[index-1] == val) {
                max_bound = index;
            }
            else return index;
        }
        else if (v[index] > val) {
            max_bound = index;
        }
        else if (v[index] < val) {
            min_bound = index+1;
        }
    }
    while(min_bound != max_bound);
    return -1;
}
