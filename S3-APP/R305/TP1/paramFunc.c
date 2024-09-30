#include <stdio.h>

void echange1(float a, float b) {
    float temp = a;
    a = b;
    b = temp;
}

void echange2(float* a, float* b) {
    float temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    float pi = 2.71828, e = 3.14159;
    const int precision = 2;

    printf("Avant echange : pi = %.*f, e = %.*f.\n", precision, pi, precision, e);
    echange2(&pi, &e);
    printf("Apres echange : pi = %.*f, e = %.*f.\n", precision, pi, precision, e);

    return 0;
}
