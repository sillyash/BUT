#include <stdio.h>
#include <time.h>
#include <unistd.h>
#include <pthread.h>

#define WIDTH 5
#define HEIGHT 5

int A[HEIGHT][WIDTH] = {
	{1, 2, 5, 4, 9},
	{5, 2, 0,-4,-3},
	{8, 0, 5, 2, 0},
	{1,-2, 6,-5, 9},
	{4, 2, 1, 7, 7},
};
int x[HEIGHT] = {1, 0, 0, 1, 0};
int y[WIDTH] = {0};

void printArray(int arr[]) {
	int j;
	printf("|");
	for (j=0; j<WIDTH-1; j++) {
		if (arr[j] >= 0) printf(" ");
		printf("%d,", arr[j]);
	}
	if (arr[j] >= 0) printf(" ");
	printf("%d |\n", arr[j]);
}

void printMatrix(int matrix[HEIGHT][WIDTH]) {
	int i;

	for (i=0; i<HEIGHT; i++) {
		printArray(matrix[i]);
	}
	printf("\n");
}

void* multline(void* arg) {
	int i = (int)arg;

	for (int j=0; j<WIDTH; j++) {
		y[i] += A[i][j] * x[j];
	}
	return NULL;
}


int main(int argc, char* argv[])
{
	int i;
	pthread_t tid[HEIGHT];

	printf("x : "); printArray(x);
	printf("y : "); printArray(y);

	printf("A :\n"); printMatrix(A);

	for (i=0; i<HEIGHT; i++) {
		pthread_create(&tid[i], NULL, multline, (void *)i);
	}

	for (i=0; i<HEIGHT; i++) {
		pthread_join(tid[i], NULL);
	}	

	printf("\n");
	return 0;
}
