#include <iostream>
using namespace std;

const float PI=3.14;

int main(){
    float radius;
    float area;
    cout << "What is the radius of the circle (in cm)? " << endl;
    cin >> radius;
    area = PI*radius*radius;
    cout << "Here is the area of your circle: " << area;
    return 0;
}

