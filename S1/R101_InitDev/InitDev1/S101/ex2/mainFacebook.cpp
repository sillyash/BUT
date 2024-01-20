#include <iostream>
#include <vector>
#include <string>
#include "functionsFacebook.h"

using namespace std;

int main()
{
    // create and initialize the network and name vectors
    vector<vector<bool> > friends = initialization();
    vector<string> friendsNames = {"ALBERT","JEANNE","ROMAIN","FRED"};

    int choice;
    string memb1, memb2, memb3;


    ///-----TESTS-----

    // normalization
    cout << "Test of normalization" << endl;
    cout << "General case (lowercase string): ";
    string testNorm = "test";
    normalization(testNorm);
    if (testNorm == "TEST") {cout << "Test passed!" << endl;}
    else {cout << "Test failed: normalization(\"test\") = " << testNorm << endl;}

    cout << "Special case (uppercase characters): ";
    testNorm = "TeSt";
    normalization(testNorm);
    if (testNorm == "TEST") {cout << "Test passed!" << endl;}
    else {cout << "Test failed: normalization(\"test\") = " << testNorm << endl;}


    // nameToIndex
    cout << endl << "Test of nameToIndex" << endl;
    cout << "General case (member is in the network): ";
    string membTest = "JEANNE";
    if (nameToIndex(friendsNames, membTest) == 1) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: nameToIndex(\"JEANNE\") = " << nameToIndex(friendsNames, membTest) << endl;}

    cout << "Special case (member is in lowercase): ";
    membTest = "jeanne";
    if (nameToIndex(friendsNames, membTest) == 1) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: nameToIndex(\"jeanne\") = " << nameToIndex(friendsNames, membTest) << endl;}

    cout << "Error case (member isn't in the network): ";
    membTest = "ZGLORB";
    if (nameToIndex(friendsNames, membTest) == -1) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: nameToIndex(\"ZGLORB\") = " << nameToIndex(friendsNames, membTest) << endl;}


    // checkMemb
    cout << endl << "Test of checkMemb" << endl;
    cout << "General case (member is in the network): ";
    membTest = "JEANNE";
    if (checkMemb(friendsNames, membTest) == true) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: checkMemb(\"JEANNE\") = " << checkMemb(friendsNames, membTest) << endl;}

    cout << "Error case (member isn't in the network): ";
    membTest = "ZGLORB";
    if (checkMemb(friendsNames, membTest) == false) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: checkMemb(\"ZGLORB\") = " << checkMemb(friendsNames, membTest) << endl;}


    // addFriends
    cout << endl << "Test of addFriends" << endl;
    cout << "General case (members are in the network): ";
    membTest = "JEANNE"; string membTest2 = "FRED";
    addFriends(friends, friendsNames, membTest, membTest2);
    if (friends[1][3] == friends[3][1] == true) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: friends[1][3] = " << friends[1][3] << endl << "friends[3][1] = " << friends[3][1];}

    cout << "Error case (members aren't in the network): ";
    membTest = "JEANNE"; membTest2 = "ZGLORB";
    vector<vector<bool>> friendsCopy = friends;
    addFriends(friends, friendsNames, membTest, membTest2);
    if (friendsCopy == friends) {cout << "Test passed!" << endl;}
    else {cout << "Test failed." << endl;}


    // commonFriends
    cout << endl << "Test of commonFriends" << endl;
    cout << "Special case (members are friends but have no friends in common): ";
    membTest = "JEANNE"; membTest2 = "FRED";
    addFriends(friends, friendsNames, membTest, membTest2);
    if (commonFriends(friends, friendsNames, membTest, membTest2) == 0) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: commonFriends(\"JEANNE\",\"FRED\") = " << commonFriends(friends, friendsNames, membTest, membTest2) << endl;}

    cout << "General case (members have friends in common): ";
    membTest = "JEANNE"; membTest2 = "FRED";
    addFriends(friends, friendsNames, "JEANNE", "ALBERT");
    addFriends(friends, friendsNames, "FRED", "ALBERT");
    if (commonFriends(friends, friendsNames, membTest, membTest2) == 1) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: commonFriends(\"JEANNE\",\"FRED\") = " << commonFriends(friends, friendsNames, membTest, membTest2) << endl;}


    // groupFriends
    cout << endl << "Test of groupFriends" << endl;
    cout << "General case (members are a group of friends): ";
    if (groupFriends(friends, friendsNames, "FRED", "JEANNE", "ALBERT") == 1) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: groupFriends(\"FRED\",\"JEANNE\",\"ALBERT\") = " << groupFriends(friends, friendsNames, "FRED", "JEANNE", "ALBERT") << endl;}

    cout << "Special case (members aren't a group of friends): ";
    if (!(groupFriends(friends, friendsNames, "FRED", "JEANNE", "ROMAIN") == 1)) {cout << "Test passed!" << endl;}
    else {cout << "Test failed: groupFriends(\"FRED\",\"JEANNE\",\"ROMAIN\") = " << groupFriends(friends, friendsNames, "FRED", "JEANNE", "ROMAIN") << endl;}


    // reset network after tests
    friends = initialization();
    ///-----END OF TESTS-----


    // input/test loop in the form of a menu
    do {
        choice = testModif();
        switch (choice) {

            case ADDFRIENDS:
                // input member names and check them
                cout << endl << "Choose two members of the network to add a frienship between them." << endl << ">>> ";
                cin >> memb1;
                while (!(checkMemb(friendsNames, memb1))) {
                    // remove input in case input is not an int
                    fflush(stdin);
                    cin.clear();
                    cout << "The member you have entered is not part of the network. Try again." << endl << ">>> ";
                    cin >> memb1;
                }
                cin >> memb2;
                while (!(checkMemb(friendsNames, memb2))) {
                    // remove input in case input is not an int
                    fflush(stdin);
                    cin.clear();
                    cout << "The member you have entered is not part of the network. Try again." << endl << ">>> ";
                    cin >> memb2;
                }
                // execute
                addFriends(friends, friendsNames, memb1, memb2);
                cout << "Done!" << endl << endl;
                break;

            case DISPLAY:
                cout << endl;
                // execute
                display(friends, friendsNames);
                cout << endl << endl;
                break;

            case COMMONFRIENDS:
                // input member names and check them
                cout << endl << "Choose two members of the network to check the number of friends they have in common." << endl << ">>> ";
                cin >> memb1;
                while (!(checkMemb(friendsNames, memb1))) {
                    // remove input in case input is not an int
                    fflush(stdin);
                    cin.clear();
                    cout << "The member you have entered is not part of the network. Try again." << endl;
                    cin >> memb1;
                }
                cin >> memb2;
                while (!(checkMemb(friendsNames, memb2))) {
                    // remove input in case input is not an int
                    fflush(stdin);
                    cin.clear();
                    cout << "The member you have entered is not part of the network. Try again." << endl;
                    cin >> memb2;
                }
                // execute and display results
                normalization(memb1); normalization(memb2);
                cout << memb1 << " and " << memb2 << " have " << commonFriends(friends, friendsNames, memb1, memb2) << " common friends."<< endl << endl;
                break;

            case GROUPFRIENDS:
                // input member names and check them
                cout << endl << "Choose two members of the network to check the number of friends they have in common." << endl << ">>> ";
                cin >> memb1;
                while (!(checkMemb(friendsNames, memb1))) {
                    // remove input in case input is not an int
                    fflush(stdin);
                    cin.clear();
                    cout << "The member you have entered is not part of the network. Try again." << endl;
                    cin >> memb1;
                }
                cin >> memb2;
                while (!(checkMemb(friendsNames, memb2))) {
                    // remove input in case input is not an int
                    fflush(stdin);
                    cin.clear();
                    cout << "The member you have entered is not part of the network. Try again." << endl;
                    cin >> memb2;
                }
                cin >> memb3;
                while (!(checkMemb(friendsNames, memb3))) {
                    // remove input in case input is not an int
                    fflush(stdin);
                    cin.clear();
                    cout << "The member you have entered is not part of the network. Try again." << endl;
                    cin >> memb3;
                }
                // execute and display results
                normalization(memb1); normalization(memb2); normalization(memb3);
                if (groupFriends(friends, friendsNames, memb1, memb2, memb3)) {
                    cout <<memb1<<", "<<memb2<<" and "<<memb3<<" are a group of friends." << endl;
                } else {
                    cout <<memb1<<", "<<memb2<<" and "<<memb3<<" aren't a group of friends." << endl;
                }
                break;

            case QUIT:
                // quits immediately
                return 0;
        }
    }
    // Infinite loop to allow user to reiterate the
    // process, as "case QUIT" allows him to quit.
    while(1);
}
