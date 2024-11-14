#include <unistd.h>
#include <stdio.h>
#include <signal.h>
#include <time.h>

volatile unsigned int count = 0;
volatile int step = 1;

void hnd1(int sig) { // signal handler
    count = 1;
}

void hnd2(int sig) {
    step = -step;
}

void hnd3(int sig) {
    printf("Count : %d\n", count);
    alarm(1);
}

int main() {
    clock_t begin = clock();
    
    // bind SIGUSR1 to handler hnd1
    signal(SIGUSR1, &hnd1);
    signal(SIGUSR2, &hnd2);
    signal(SIGALRM, &hnd3);
    
    alarm(1);

    for (count = 1; count > 0; count += step);

    clock_t end = clock();
    double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    time_spent *= 1000; // make it ms instead of sec

    printf("Execution time : %lfms\n", time_spent);
    return 0;
}