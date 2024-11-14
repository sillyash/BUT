#include <stdio.h>

int main() {
    double valeur = 10.0;
    double *pv = &valeur;
    int nombre = 1, *pn;
    pn = &nombre;
    valeur = *pv + *pn;
    printf(" valeur = %f\n", valeur);
    printf("&valeur = %p\n", &valeur);
    return 0;
}