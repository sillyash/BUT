# TD1 - Processes and signals

## Exercise 1 - Who am I?

```c
int main () {
    pid_t child = fork();
    if (child == 1) {
        perror("fork() error");
        exit(1);
    }

    // à ajouter pour la question (b)
    printf("My PID is %d.\n", getpid());
    
    if (child == 0) {
        printf("Child process: my PID is %d.\n", getpid());

        // à enlever pour la question (b)
        exit(0); 
    }

    printf("Now my PID is %d.\n", getpid());
    exit(0);
}
```

#### a. What is the output of this program ?

>This program outputs 

