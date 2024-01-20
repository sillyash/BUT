#include <iostream>
using namespace std;

// Ash MERIENNE, 1B

// Initialize constants (outside of main to use in functions)
const int SSC = 6, SSA = 15, SNC = 8, SNA = 18, LSC = 10, LSA = 18, LNC = 12, LNA = 22;


bool is_sub(char subscriber) {
    switch (subscriber) {
        case 'y':
            return true;
        default:
            return false;
    }
}


bool is_long(char typeShow) {
    switch (typeShow) {
        case 'l':
            return true;
        default:
            return false;
    }
}

int calcPrice(int price) {
    /* Returns the price corresponding to
    the parameters of it's constant.
    S/2 = "Short", L/1 = "Long"
    S/1 = "Subscribed", N/2 = "Normal"
    C/1 = "Child", A/2 = "Adult" */
    switch (price) {
        case 211:
            return SSC;
        case 212:
            return SSA;
        case 221:
            return SNC;
        case 222:
            return SNA;
        case 111:
            return LSC;
        case 112:
            return LSA;
        case 121:
            return LNC;
        case 122:
            return LNA;
        default:
            return 0;
    }
}

int main() {
    // Initialize variables
    int age, price;
    string strPrice;
    char typeShow, subscriber;

    // Ask for the type of show, age and length
    cout << "Enter the type of concert you wish to book." << endl;
    cout << "Enter 'l' for long and 's' for short." << endl;
    cin >> typeShow;
    cout << "Enter your age." << endl;
    cin >> age;
    cout << "Are you a subscriber? Enter 'y' for yes or 'n' for no." << endl;
    cin >> subscriber;

    // Set chars to lowercase
    typeShow = tolower(typeShow);
    subscriber = tolower(subscriber);

    // Check all parameters and add '1' or '2' depending on the answer
    if (is_long(typeShow)) {
        strPrice += '1';
    }else {
        strPrice += '2';
    }
    if (is_sub(subscriber)) {
        strPrice += '1';
    }else {
        strPrice += '2';
    }
    if (age < 18) {
        strPrice += '1';
    }else {
        strPrice += '2';
    }

    // Convert the string (containing 3 digits)
    // to an int with the same digits, because
    // C++ has trouble with strings in switch
    price = stoi(strPrice);
    price = calcPrice(price);

    // Print output
    cout << "You have to pay " << price << "$." << endl;
    return 0;
}

/*
TESTS

l, 19, y -> 121 -> 18$
l, 17, n -> 121 -> 12$
l, 17, y -> 121 -> 10$
s, 19, n -> 121 -> 18$
*/
