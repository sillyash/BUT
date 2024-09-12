# TD1 - Processes and signals

## Exercise 1 - Who am I?

```c
int main ()
{
    pid_t child = fork();
    if (child == 1) {
        perror("fork() error");
        exit(1);
    }

    // à ajouter pour la question (b)
    // printf("My PID is %d.\n", getpid());
    
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

>This program outputs the child and parent PID, like so:
>```
>Child process: my PID is 4321.
>Now my PID is 1234.
>```
>The order of the prints being arbitrary.


#### b. What is the output, if we add line 10 and remove line 16 ?

>This program outputs the child PID 3 times and the parent PID twice, because the child process doesn't terminate until the last line, like so:
>```
>My PID is 4321.
>My PID is 1234.
>Child process: my PID is 4321.
>Now my PID is 4321.
>Now my PID is 1234.
>```
>The order of the prints being arbitrary.


## Exercise 2 - Bob

```c
int sunny_days_in_London(int year) { /* 6 heures de calcul */ }
int second_year_presence(int day) { /* 6 heures de calcul */ }

int main ()
{
    int captains_age = 0;
    pid_t cpid = fork();

    if (cpid != 0)
        captains_age += sunny_days_in_London(1305);
    else 
        captains_age += second_year_presence(1305);

    printf(
        "Le capitaine a %d ans et mon PID est %d\n",
        captains_age,
        (int) getpid()
    );

    return 0;
}
```

#### Explain Bob’s mistake and propose a way to solve the problem using exit() and wait(). Detail the actions of both processes.

>Bob didn't make the child process exit after doing the calculation, so there are going to be 2 differents prints.

>Also, Bob forgot to wait for the child process to end before printing out the result, and to check for an exit code to check for errors.

>This program is going to output two lines, containing the 2 different calculations.

>To solve the problem, we could add ```exit(age);``` in the ```if```, and a ```wait()``` before the ```printf``` statement.

#### Corrected program

```c
int sunny_days_in_London(int year) { /* 6 heures de calcul */ }
int second_year_presence(int day) { /* 6 heures de calcul */ }

int main ()
{
    int captains_age = 0;
    int status;

    pid_t cpid = fork();

    if (cpid != 0) {
        captains_age += sunny_days_in_London(1305);
        status = 0;
        exit(captains_age); // or return age;
    } else 
        captains_age += second_year_presence(1305);

    wait(&status);
    WIFEXITED(&status) {
        captains_age += WEXITSTATUS(&status);
    }

    printf(
        "Le capitaine a %d ans et mon PID est %d\n",
        captains_age,
        (int) getpid()
    );

    return 0;
}
```

## Exercise 3 - Firefork()

```c
int main(int argc, char ** argv)
{
    pid_t id;
    int i, N = 0;

    if (argc > 1) N = atoi(argv[1]);
    
    for (i = 0; i < N; i++) {
        id = fork();
        
        if (id == 1) {
            perror("fork() error"); exit(1);
        }
        
        printf(
            "I am %d, son of %d.\n",
            getpid(),
            getppid()
        );
    }
    
    printf("%d out.\n", getpid());
    
    return 0;
}
```

#### Give a generic formula for the number of processes depending on N.

>This program doesn't check if you're the parent or a child process : so this is going to fork from the parent and the childs. Now let's make an example :

>Let's say we execute ```./fforks 3``` :

>```atoi('3') = 3``` so the loop is going to go 3 times.

>1st iteration : \
>fork 1 child

>2nd iteration : \
>Both the parent and the child fork a process. \
>We now have 4 total processes.

>3rd iteration : \
>Each 4 of the processes fork a child. \
>We now have a total of 8 processes.

>You've probably noticed the pattern : this program makes ```O(N)``` child processes.

>This type of program is called a <b style="font-style: italic">fork bomb</b>.

## Exercise 4: world! Hello,

```c
int main ()
{
    pid_t id = fork();
    
    if (id == 0) {
        printf("Hello, ");
        exit(0);
    }

    // <-- TROU
    
    printf("world!");
    return 0;
}
```

We want to complete this program to make sure that the words are always printed in the right order, independently of scheduling decisions.


#### The system call int sleep(int n) puts the program in the waiting state for n seconds. Can we complete the program by calling sleep(1)? sleep(100)? Explain your answer.

```c

```


