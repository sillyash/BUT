#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

int goThroughArray(
    unsigned char arr[],
    long int begin,
    long int end,
    char numToCheck
    ) {
    for (long int i = begin; i < end; i++) {
        putc('.', stdout); fflush(stdout);
        if (arr[i] == numToCheck) {
            return 1;
        }
    }
    return 0;
}

void printArray(unsigned char arr[], int arrSize) {
    for (int i=0; i<arrSize; i++) {
        printf("%d, ", arr[i]);
    }
    printf("\n");
}

void hnd(int sig) {
    printf("PID %d, exiting!\n", getpid());
    exit(0);
}

int main(int argc, char *argv[])
{
    clock_t begin = clock();

    // parent proc ignores sigterm
    signal(SIGTERM, SIG_IGN);

    int N = 0;
    if (argc > 1) N = atoi(argv[1]);
    if (N < 1 || N > 100) N = 1;

    const long int TABSIZE = 100000;
    int found = 0, procStatus;
    pid_t proc;
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

    printf("PPID : %d\n", getpid());

    for (int i=0; i<N; i++)
    {
        proc = fork();
        int start = i*TABSIZE/N;
        int end = (i+1)*TABSIZE/N;

        if (proc == 0) // child
        {
            signal(SIGTERM, hnd);  // Set signal handler early

            printf("CPID : %d\n", getpid());
            
            // array search
            return goThroughArray(arr, start, end, 0);
        }
    }

    if (proc != 0) // parent
    {
        int endedChildren = 0;
        while (endedChildren < N)
        {
            wait(&procStatus);

            if (WIFEXITED(procStatus) && WEXITSTATUS(procStatus) == 1) {
                found = 1;
                kill(0, SIGTERM); // Kill all children >:3
                break;
            }
            endedChildren++;
        }
    }

    if (found) printf("Got a needle!\n");
    else printf("No needles.\n");
    printf("\n");

    clock_t end = clock();
    double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    time_spent *= 1000; // make it ms instead of sec

    printf(
        "\nTabsize :\t\t%ld\nExecution time :\t%lfms",
        TABSIZE,
        time_spent
    );
    printf("\n");

    return 0;
}
