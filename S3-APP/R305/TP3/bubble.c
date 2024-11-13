#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <stdbool.h>

#define SIZE 20
int tab[SIZE];

void swap(int* xp, int* yp){
	int temp = *xp;
	*xp = *yp;
	*yp = temp;
}

void * bulle(void * arg) {
	int i,k,n;
	for (k=SIZE-1; k>0; k=n) {
		for (n=i=0; i<k; i++) {
			if (tab[i] > tab[i+1]) {
				int tmp = tab[i];
				tab[i] = tab[i+1];
				tab[i+1] = tmp;
				n = i;
			}
		}
	}
	return NULL;
}

void * plomb(void * arg) {
	int i,k,n;
	for (k=0; k>SIZE-1; k=n) {
		for (n=i=SIZE-1; i>k; i++) {
			if (tab[i-1] > tab[i]) {
				int tmp = tab[i];
				tab[i] = tab[i-1];
				tab[i-1] = tmp;
				n = i;
			}
		}
	}
	return NULL;
}

void shuffle() {
	int i, j, temp;
	for (i=SIZE-1; i>0; i--) {
		j = random() % (i + 1);
		temp = tab[i];
		tab[i] = tab[j];
		tab[j] = temp;
	}
}

void parray(int tab[], int size) {
	int j;
	printf("|");
	for (j=0; j<size-1; j++) {
		if (tab[j] >= 0) printf(" ");
		printf("%d,", tab[j]);
	}
	if (tab[j] >= 0) printf(" ");
	printf("%d |\n", tab[j]);
}

int main()
{
	int i;
	pthread_t bulle_id, plomb_id;

	srandom(time(NULL));

	for (i = 0; i < SIZE; i++) tab[i] = i + 1;

	for (i = 0; i < 3000; i++) {
		shuffle();
		if (i==1) parray(tab, SIZE);
		if (pthread_create(&bulle_id, NULL, bulle, NULL) != 0) exit(1);
		if (pthread_create(&plomb_id, NULL, plomb, NULL) != 0) exit(1);
		pthread_join(bulle_id, NULL);
		pthread_join(plomb_id, NULL);
	}

	parray(tab, SIZE);

	putchar('\n');
	return 0;
}
