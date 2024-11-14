#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

#define NB_SEATS 100

void printArray(int arr[]) {
	int i;
	printf("[");
	for (i=0; i<NB_SEATS-1; i++) {
		printf("%d,", arr[i]);
	}
	printf("%d]\n", arr[i]);
}

int main()
{
	int place[NB_SEATS];

	for (int i=0; i<NB_SEATS; i++) {
		place[i] = 0;
	}

	printArray(place);

	printf("\n");
	return 0;	
}
