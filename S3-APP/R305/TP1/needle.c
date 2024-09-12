#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

int goThroughArray(int[]& arr, int begin, int end, int numToCheck)
{
    for (int i = begin; i < end; i++)
        if (arr[i] == numToCheck) return 1;
    
    return 0;
}

int main()
{
    const int TABSIZE = 100;
    const int MIDDLE = TABSIZE / 2;
    int begin, end, inputedNum, found, procStatus, foundChild;

    unsigned char arr[TABSIZE];
    srandom(time(NULL));

    // initialize arr with randome numbers
    for (int i = 0; i < TABSIZE; i++)
        arr[i] = (unsigned char) (random() % 255) + 1;

    // user input
    printf("Enter a number between 0 and %d: ", TABSIZE);
    scanf(" %d", &inputedNum);
    if (i >= 0 && i < TABSIZE) arr[i] = 0;

    int proc_pid = fork();

    if (proc_pid == 0)
    {
        begin = MIDDLE;
        end = TABSIZE;
        found = goThroughArray(arr, begin, end, inputedNum);

        return found;
    } else {
        begin = 0;
        end = MIDDLE;
        found = goThroughArray(arr, begin, end, inputedNum);
    }

    wait(&procStatus)
    if (WIFEXITED(procStatus)) {
        if (WEXITSTATUS(procStatus) == 0)
            found = 0;
    }

    if (found) printf("Got a needle!\n");
    else printf("No needles.\n");

    return 0;
}