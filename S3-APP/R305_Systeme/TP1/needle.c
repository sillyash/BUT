#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>

int goThroughArray(
    unsigned char arr[],
    long int begin,
    long int end,
    char numToCheck
    ) {
    for (int i = begin; i < end; i++) {
        if (arr[i] == numToCheck) return 1;
    }
    return 0;
}

void printArray(unsigned char arr[], int arrSize) {
    for (int i=0; i<arrSize; i++) {
        printf("%d, ", arr[i]);
    }
    printf("\n");
}


int main()
{
    clock_t begin = clock();

    const long int TABSIZE = 100000;
    const long int MIDDLE = TABSIZE / 2;
    int found, procStatus;
    long int inputedNum;
    int inputIsCorrect = 0;

    unsigned char arr[TABSIZE];
    srandom(time(NULL));

    // initialize arr with random numbers
    for (int i = 0; i < TABSIZE; i++)
        arr[i] = (unsigned char) (random() % 255) + 1;

    // user input
    while (!inputIsCorrect) {
        printf("Enter a number between 0 and %ld: ", TABSIZE);
        scanf(" %ld", &inputedNum);

        if (inputedNum >= 0 && inputedNum < TABSIZE) {
            arr[inputedNum] = 0;
            inputIsCorrect = 1;
        } else {
            printf("Not a correct input. Try again.\n");
        }  
    }

    int proc_pid = fork();

    if (proc_pid == 0)
    {
        return goThroughArray(
            arr,
            MIDDLE,
            TABSIZE,
            0
        );
    } else {
        found = goThroughArray(
            arr,
            0,
            MIDDLE,
            0
        );
    }

    wait(&procStatus);

    if (WIFEXITED(procStatus)) {
        if (WEXITSTATUS(procStatus) == 1)
            found = 1;
    }

    if (found) printf("Got a needle!\n");
    else printf("No needles.\n");

    printf("\n");

    clock_t end = clock();
    double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    time_spent *= 1000; // make it ms instead of sec

    printf(
        "Tabsize :\t\t%ld\nExecution time :\t%lfms",
        TABSIZE,
        time_spent
    );
    printf("\n");

    return 0;
}