#include <iostream>
#include <cstdlib> // for srand and rand
#include <ctime> // for time

using namespace std;

bool is_multiple(int num1, int num2) {
    // True if num1 is a multiple of num2
    if (num1%num2 == 0) {
        return true;
    }
    return false;
}

bool is_divisor(int num1, int num2) {
    // True if num1 is a divisor of num2
    if (num2%num1 == 0) {
        return true;
    }
    return false;
}

int main() {
    srand(time(NULL)); // initialize generator using the current time
    int alea = rand()%100 + 1; // random number between 1 and 100
    int num;
    int guesses = 10;

    //Init
    cout << "You have " << guesses << " to find my number (between 1 and 100 only)" << endl;

    do {
        // Check if user has guesses keft
        if (guesses == 0) {
            cout << "Too bad, you have lost, the secret number was " << alea << "." << endl;
            break;
        }

        // Guess
        cout << "Enter your guess:" << endl;
        cin >> num;

        // Check if guess is correct input
        while (num<1 || num>100) {
            // If not, guess again until guess is correct input
            cout << "Error, enter a number between 1 and 100." << endl;
            cin >> num;
        }
        // If user has found the secret number
        if (num == alea) {
            cout << "Congratulations! The secret number was " << alea << "!" << endl;
            break;
        }
        // Check if guess is a multiple or divisor of alea
        if (is_multiple(num, alea)) {
            cout << "Your number is a multiple of the secret number, free guess!" << endl;
            }
            else if (is_divisor(num, alea)) {
            cout << "Your number is a divisor of the secret number, free guess!" << endl;
            }
            else {
                cout << "Your number is not related to the secret number." << endl;
                --guesses;
            }
        // Display remaining guesses
        cout << "You have " << guesses << " guesses left." << endl << endl;
    }
    // Execute the do loop as long as you have not guessed
    // the number and you have guesses left.
    while (num != alea && guesses <= 10);

    return 0;
}

/*
TESTS

You have 10 to find my number (between 1 and 100 only)
Enter your guess:
10
Your number is not related to the secret number.
You have 9 guesses left.

Enter your guess:
-6
Error, enter a number between 1 and 100.
5
Your number is not related to the secret number.
You have 8 guesses left.

Enter your guess:
2
Your number is a divisor of the secret number, free guess!
You have 8 guesses left.

Enter your guess:
4
Your number is not related to the secret number.
You have 7 guesses left.

Enter your guess:
6
Your number is a divisor of the secret number, free guess!
You have 7 guesses left.

Enter your guess:
12
Your number is not related to the secret number.
You have 6 guesses left.

Enter your guess:
18
Your number is not related to the secret number.
You have 5 guesses left.

Enter your guess:
30
Your number is not related to the secret number.
You have 4 guesses left.

Enter your guess:
36
Your number is not related to the secret number.
You have 3 guesses left.

Enter your guess:
42
Congratulations! The secret number was 42!
*/

