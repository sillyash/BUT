# TD3 : Threads

Objectives: know how to create, use, and terminate threads. Learn to recognize shared data, and be able to organize synchronization between threads with the help of termination, joining, mutual exclusion, and condition variables.

## Threads

Threads are execution units that operate in the context of a process. One process can contain several threads that execute the same program in the shared memory.

More precisely, the code
segment (the program itself), the data segment (the global variables) and the heap (the dynamic allocations) are shared among different threads of the same process.

Each thread has its own stack, which allows them to follow different execution paths. However, since those stacks are allocated within the common memory, a local variable on a stack of one thread can be read and modified by another thread, provided it knows the variable’s address.

Thanks to the shared memory, different threads of a process can exchange data without using inter-process communication utilities such as pipes and sockets.

Creation of a new thread is also less costly that creation of a new process. Indeed, even if the kernel does not need to duplicate the process’ memory contents during ```fork()```, it still has to copy its page table. For the same reason, the context switch between two threads is more quick than between two processes.

Nevertheless, programming with threads is a delicate matter: simultaneous access to shared variables can lead to interference errors which are :
- difficult to detect since error manifestation depends on scheduling decisions as well as on the specific architecture details in case of a multi-processor system.
- difficult to analyse since consecutive executions of the same program do not behave in the same manner, and what is more, the debugging itself 
	- via code instrumentation or using a debugger
	- can prevent the error from happening (this nightmare of any programmer was dubbed Heisenbug);
- difficult to fix since correct handling of access to shared data can require major changes in the program code, up to complete reimplementation of relevant parts.

Thus, design and development of multi-threaded applications requires from the programmer to be aware of the problems related to shared memory and to master the solutions : 
- mutual exclusion
- condition variables
-  appropriate data structures and algorithms
- etc.

In this course we shall use the POSIX threads (also called ```pthreads```) and their current implementation in Linux.

## Creation and identification of threads

```c
#include <pthread.h>
int pthread_create(
pthread_t *thread_id /* pointeur to the thread's identifier */
pthread_attr_t *attr, /* NULL: attributes by default */
void *(*routine) (void *), /* function executed by the thread */
void *arg /* argument passed to routine() */);
```

The system call ```pthread_create()``` creates a new thread and stores its identifier in ```thread_id```. This identifier is an opaque value which is used in subsequent handling of the new thread.

The parameter ```attr``` defines the attributes of the created thread; we will always use ```NULL``` which corresponds to the default attributes.

The parameter routine is a pointer to a function which will be executed by the thread.

This function, which is called the start routine, takes a single argument of type `void` and returns `void*`.

Finally, the last parameter, `arg`, corresponds to the argument which will be passed to
the function `routine()`. This system call returns 0 on success and a non-zero error code otherwise.

The type `void*` represents a pointer to an unspecified type. The programmer may pass a pointer to any kind of data to the new thread by casting this pointer to `void*` in the last argument of `pthread_create()` and by casting it back to the actual type inside the start routine.

The return value of the start routine is handled in the same way (see the next section).

The new thread has the same `PID` and `PPID` as the original thread. It also shares its permissions (`UID`, `GID`, `EUID`, `EGID`), open files, signal handlers (though not the set of blocked signals, which is a per-thread property), its current directory, environment variables and other properties (see ```man 7 pthreads``` for the complete list).

Every thread has a unique identifier of type ```pthread_t```. To obtain the identifier of the current thread or compare two thread identifiers, the following functions can be used :

```c
#include <pthread.h>
pthread_t pthread_self(void);
int pthread_equal(pthread_t tid1, pthread_t tid2);
```

The function `pthread_equal()` returns a non-zero value if `tid1` and `tid2` are equal and 0 otherwise.

## Thread termination

All threads in a process terminate when one of them calls exit() or when the initial thread (the one that existed before any call to `pthred_create()`) returns from the function main().

A single thread terminates when it returns from its start routine (the function passed as argument to
`pthread_create()`) or when it calls the system call `pthread_exit()` :

```c
#include <pthread.h>
void pthread_exit(void *retval);
```

which takes as argument the return value of the thread. The system call `pthread_exit()` can never return to the calling thread. The return value can be an integer converted to void*: for example, one can return `((void*) 1)` from the start routine.

By default, any thread is considered joinable. This means that when this thread terminates, its resources are not freed until some other thread calls `pthread_join()` :

```c
#include <pthread.h>
int pthread_join(pthread_t thread_id, void **retval);
```

This system call suspends execution of the calling thread until the termination of the thread with the identifier `thread_id`. If this thread has already terminated, the system call returns immediately.

In case of success, `pthread_join()` returns 0 and writes the return value of the joined thread at the address `*retval`.

In case of an error, `pthread_join()` returns an error code. This system call is analogous to `waitpid()` for processes.

It is possible to declare a thread non-joinable or detached, by using `pthread_detach()` :

```c
#include <pthread.h>
int pthread_detach(pthread_t thread_id);
```

This system call tells the kernel that the resources allocated for the thread `thread_id` can be freed when it terminates (immediately if it has already terminated).

A call to `pthread_join()` on a thread which is detached or has already been joined returns the `EINVAL` error code.

## Mutual exclusion

The main synchronization utility for threads is the mutual exclusion object, or mutex.

Mutexes allow us to ensure that several threads can not access shared values at the same time. At any given moment, a mutex is either free (unlocked), or taken by exactly one thread (locked).

Any thread that tries to take a locked mutex must wait until the mutex is released.

We manipulate mutexes using four basic system calls listed below. All of them return 0 in case of success and an error code otherwise.

```c
#include <pthread.h>
int pthread_mutex_init(pthread_mutex_t *mutex, NULL);
int pthread_mutex_lock(pthread_mutex_t *mutex);
int pthread_mutex_unlock(pthread_mutex_t *mutex);
int pthread_mutex_destroy(pthread_mutex_t *mutex);
```

The system call `pthread_mutex_init()` initialises a mutex whose address is given in the first argument.

The second argument indicates the attributes of the mutex. We will use `NULL` to get the attributes by default. The newly initialised mutex is in the unlocked state.

The system call `pthread_mutex_lock()` locks the mutex given in the argument. If the mutex was unlocked, it becomes locked and `pthread_mutex_lock()` returns immediately.

If the mutex was locked by another thread, the calling thread is suspended until the mutex is released.

If the mutex was locked by the calling thread, the behaviour of `pthread_mutex_lock()` depends on the attributes of the mutex. By default, the calling thread is blocked indefinitely.

The system call `pthread_mutex_unlock()` unlocks the mutex given in the argument. The mutex must be locked by the calling thread at the moment of the call.

If there are other threads waiting to take the mutex with `pthread_mutex_lock()`, then one of them will lock the mutex and continue execution.

The system call `pthread_mutex_destroy()` destroys the mutex given in the argument. The mutex must be unlocked at the moment of the call.

It is possible to reinitialize a destroyed mutex with a new call to `pthread_mutex_init()`.

## Condition variables

Mutexes protect concurrent threads against the interference errors by giving one thread the exclusive access to a shared resource. However, it may happen that the thread cannot perform the desired action right away and must wait until the shared resource’s state changes so that the thread can proceed.

For example, a thread that tries to debit a bank account can only do it when the account contains a sufficient sum. If the debiting thread keeps the locked mutex (protecting the account) while waiting, it will prevent other threads from accessing the account and crediting it. If, on the other hand, the debiting thread releases the mutex, it will later have to retake it to make a new attempt.

Doing it immediately will heavily burden the system, since the thread will stay in the «locking-test-unlocking» loop (this kind of behaviour is called busy waiting). At the same time, adding a delay before a new attempt risks to slow the program for no good reason.

A condition variable (or simply a condition) is a synchronization utility that allows a thread to temporarily give up a mutex and to suspend itself, waiting for a notification that would indicate a change in the state of the shared resource.

Upon receiving a notification, the waiting thread retakes the mutex and attempts to perform the desired operation again.

We manipulate condition variables using five system calls listed below. All of them return 0 in case of success and an error code otherwise.

```c
#include <pthread.h>
int pthread_cond_init(pthread_cond_t *cond, NULL);
int pthread_cond_signal(pthread_cond_t *cond);
int pthread_cond_broadcast(pthread_cond_t *cond);
int pthread_cond_wait(pthread_cond_t *cond, pthread_mutex_t *mutex);
int pthread_cond_destroy(pthread_cond_t *cond);
```

The system call `pthread_cond_init()` initialises a condition variable whose address is given in the first argument. The second argument indicates the attributes of the condition. We will use `NULL` to get the attributes by default.

The system call `pthread_cond_signal()` sends a notification to a thread that is waiting over the
condition variable at the moment of the call. If no thread is waiting, the system call has no effect.
If several threads are waiting over the condition, only one of them will be notified (we cannot know
which one).

The system call `pthread_cond_broadcast()` sends a notification to all threads that are waiting
over the condition variable at the moment of the call. If no thread is waiting, the system call has no
effect.

The system call `pthread_cond_wait(cond,mutex)` releases mutex (which must be locked by
the calling thread at the moment of the call) and suspends the calling thread until a notification on
the condition cond arrives (following a call from another thread to `pthread_cond_signal(cond)` or
`pthread_cond_broadcast(cond)`).

Upon receiving a notification, the system call retakes the mutex (in the same way as `pthread_mutex_lock()`) and returns.

Since unlocking and suspending are done atomically (that is, inseparably), it is impossible for another thread to lock the released mutex, change the state of the shared resource, and signal the modification before `pthread_cond_wait()` can receive the notification.

Calling `pthread_cond_wait()` for the same condition with two different
mutexes is forbidden.

The system call `pthread_cond_destroy()` destroys the condition variable given in the argument.

No thread should be waiting on the condition at the moment of the call. It is possible to reinitialize a
destroyed condition variable with a new call to pthread_cond_init().

## Exercises

### Exercise 1 : Data sharing and termination

Let us consider the code below where several threads are created, and each one of them prints its own identifier :

```c
#define NB_THREADS 3
pthread_t tid[NB_THREADS];
int thread_execute = 0;

/* Fonction executée par les threads.
L'argument et la valeur de retour sont de type void*,
ce qui nécessite des conversions de type. */
void *routine(void *i) {
	int n = *((int *) i);
	// aujourd'hui sous Linux les identifiants sont numériques
	printf("Thread numéro %d, ID %lu\n", n, pthread_self());
	thread_execute = 1;
	return NULL;
}

int main()
{
	int i;
	for (i = 0; i < NB_THREADS; i++) {
		if (pthread_create(&tid[i], NULL, routine, (void *) &i) != 0) {
			fprintf(stderr, "Erreur création thread numéro %d.\n", i);
			exit(1);
		}
	}
	printf("Thread initial d'ID %lu\n", pthread_self());

	if (thread_execute)
		printf("Des threads annexes ont été exécutés.\n");
	else
		printf("Aucun thread annexe n'a été exécuté.\n");

	return 0;
}
```

#### How many threads, at most, are running in parallel during the execution of this program ?

>4

#### For each variable in this program, specify which threads can directly access it.

>- `n` : one for each thread
>- `i` (routine) : one for each thread
>- `i` (for loop) : just for main thread 

#### How a thread can read or modify the variable n of another thread?

>You can pass the variable's address in pthread_create().

#### Explain the following execution where every thread has the same number. Propose a fix.

```
Thread numéro 3, ID 3084860304
Thread numéro 3, ID 3076467600
Thread numéro 3, ID 3068074896
Thread initial d'ID 3084863168
Des threads annexes ont été exécutés.
```

```c
int i;
int arg[3];

for (i = 0; i < NB_THREADS; i++) {
	arg[i] = i;
	if (pthread_create(&tid[i], NULL, routine, (void *) &arg[i]) != 0)
```

#### Explain the following execution where the created threads produce no output. Propose a fix.

```
Thread initial d'ID 3084601024
Aucun thread annexe n'a été exécuté.
```

>f

### Exercise 2 : Parallelization of matrix × vector multiplication

Multiplication of a matrix by a vector is an operation frequently used in computer science (scientific computation, computer graphics, etc.). It is therefore quite interesting to parallelize it in order to improve its performance.

This computation is realized with two nested loops as shown in Figure 1, where we multiply matrix A by vector x and obtain another vector y. 

Specifically, the i-th element of vector y is obtained from the i-th line of A (and only that line) and the elements of vector x. Since A and x are not modified during the computation, we can compute the elements of y independently from each other.

```c
for (i = 0; i < NB_LIGNES; i++)
	for (j = 0; j < NB_COLONNES; j++)
		y[i] += A[i][j] * x[j];
```

#### Implement a program that uses threads to parallelize the matrix-vector multiplication in such a way that each element of the result is computed in parallel with others.

>[See file](./matrix.c)

### Exercise 3 : Client-server application.

Let us simulate a server application for booking of seats in a theater.

There are 100 seats which are represented by an array place of 100 integers. The element place[i] is 0 if the seat i is available, otherwise place[i] contains the number of the client that has booked the seat.

The booking requests from clients are received by the initial thread from the standard input. The initial thread reads an integer: if it is greater than zero, it stands for the number of seats to book, otherwise the server prints the current state of places[] and quits.

After each bookingrequest, the initial thread creates a new thread to handle the request and waits for a new request. The
clients are numbered in the order of arrival.

#### Implement the server program with all the necessary synchronization to avoid the interference errors.

>[See file](./server.c)

### Exercise 4 : Dining philosophers.

Five philosophers - P<sub>0</sub>, P<sub>1</sub>, P<sub>2</sub>, P<sub>3</sub>, P<sub>4</sub> - are seated around a bowl of noodles.

Between each pair of neighbors there is a fork: F<sub>0</sub> between P<sub>4</sub> and P<sub>0</sub>, F<sub>1</sub> between P<sub>0</sub> and P<sub>1</sub>, F<sub>2</sub> between P<sub>1</sub> and P<sub>2</sub>, F<sub>3</sub> between P<sub>2</sub> and P<sub>3</sub>, F<sub>4</sub> between P<sub>3</sub> and P<sub>4</sub>. Each philosopher passes back and forth between eating and thinking; both activities take some arbitrary amount of
time.

A philosopher P<sub>i</sub> can only eat when he holds the fork F<sub>i</sub> to its right and the fork F<sub>(i+1) mod 5</sub>  to its left. A fork can only be held by one philosopher at a time, and philosophers can not pick two forks simultaneously.

#### Complete the code below in order to simulate the dining philosophers.

The exclusive access to the forks is realized by the locking of corresponding mutexes.

```c
#define PLACES 5
pthread_mutex_t fourchettes[PLACES];

void *philosophe(void *arg) {
	long int place = (long int) arg % PLACES;
	long int f_droite = place;
	long int f_gauche = (place + 1) % PLACES;

	while (1) { // philosophe un jour, philosophe toujours
		pthread_mutex_lock(&fourchettes[MIN(f_droite, f_gauche)]); // ADDED
		pthread_mutex_lock(&fourchettes[MAX(f_droite, f_gauche)]); // ADDED
		sleep(random() % 3); // je médite, mes deux mains libres

		pthread_mutex_unlock(&fourchettes[f_droite]); // ADDED
		pthread_mutex_unlock(&fourchettes[f_gauche]); // ADDED
		sleep(random() % 3); // je mange, les fourchettes dans les mains
	}
	return NULL;
}

int main()
{
	long int i;
	pthread_t tid;
	srandom(time(NULL));

	for (i = 0; i < PLACES; i++) { // initialiser les fourchettes
		pthread_mutex_init(&fourchettes[i], NULL);
	}

	for (i = 0; i < PLACES; i++) { // initialiser les philosophes
		if (pthread_create(&tid, NULL, philosophe, (void *) i) != 0) exit(1);
			pthread_detach(tid); // on n'attend pas le résultât
	}

	pause();
	return 0;
}
```

#### Is the order in which mutexes are taken important ?

>a

#### Is the order in which mutexes are released important ?

>a

### Exercise 5 : Countdown.

A countdown can be implemented using the following operations:
```c
void car_init(int c); // initialize the counter
void car_count_down(); // decrement the counter
void car_wait(); // wait for the end of the countdown
```

Any thread that calls `car_wait()` must be suspended until the count reaches zero.

#### Propose an implementation of a countdown using a condition variable.

>a

### Exercise 6 : Read-write lock.

It is often the case that the majority of accesses to a shared resource are made just to read its state without changing it.

Since there is no danger of interference between concurrent read accesses, it is not necessary to ensure mutual exclusion for them. On the other hand, any write access can only be performed in absence of concurrent readers and writers.

A read-write lock is essentially a pair of locks with the following four operations :

```c
void reader_lock(); // take the reader's lock
void reader_unlock(); // release the reader's lock
void writer_lock(); // take the writer's lock
void writer_unlock(); // release the writer's lock
```

These operations ensure the following properties :
- if the writer’s lock is taken by a thread, then every call to `reader_lock()` or `writer_lock()` suspends the calling thread until the writer’s lock is released by the writer thread;
- if the reader’s lock is taken by one or several threads, then any call to `writer_lock()` suspends the calling thread until the reader’s lock is released by all reader threads.

#### Propose an implementation of the four operations above using two condition variables.

>a

#### Is it necessary to retest the condition after returning from pthread_cond_wait()?

Describe a scenario where an absence of such a test leads to a violation of the read-write lock specification.

>a

In case of a continuous inrush of readers, a writer thread may find himself suspended indefinitely : a condition called starvation.

One way of avoiding this problem is to suspend any request for the
reader’s lock when there is a thread waiting for the writer’s lock.

#### Modify your implementation to prevent starvation of writer threads.

>a

## Training: exercise with solution

### The fleeing number.

We want to implement a game of Mysterious Fleeing Number.

This is a variation of a classical guess-the-number game, where the computer chooses a random number and replies to player’s guesses with “Too big! ”, “Too small! ” or “Found it! ”.

In this variation, thecomputer changes the number every t seconds by some amount x. The player is informed of thechange by a message (for example: “The mysterious number was increased by 13.”).

The numbers `t` and `x` are chosen randomly and change every time they are used. For example, after 5 seconds in game, the computer adds 32 to the mysterious number, then after 11 seconds, it subtracts 13, etc.

The
player has a limited amount of time to find the fleeing mysterious number.

#### Implement the game using three threads.

The initial thread realises the normal guess-the-number game. The first created thread handles the modification of the mysterious number with time.

The second created thread handles the time limit for the game. The number should be chosen between 0 and 200, t between 5 and 10, x between 0 and 50 shall be added or subtracted (the choice is random), but the number itself should stay between 0 and 200.

The time limit shall be set to 40 seconds.

### Correction.

The program is relatively simple : we start by implementing the traditional guessing game.

Then we add an auxiliary thread for the time limit. When the limit is exceeded, this thread can terminate the whole process by calling `exit()`.

Finally, we add the “fleeing” dimension by
using another thread. The mysterious number is a shared resource now and therefore needs to be protected with a mutex, both on reading and writing. The initial thread must check if the player’s
guess is correct (read access), and the auxiliary thread must modify the number from time to time (write access).

When the player wins, the initial thread quits, which also terminates the two auxiliary threads.

```c
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#define N_INF 0
#define N_SUP 200
#define X_MAX 50
#define T_INF 5
#define T_SUP 10
#define TIMEOUT 40

int mystere; /* nombre mystère */
pthread_mutex_t mutex; /* mutex de protection */

void *fuyeur(void *arg) { /* routine de départ du thread fuyeur */
	int t, x;
	while (1) {
		t = random() % (T_SUP T_INF + 1) + T_INF; /* temps d'attente */
		x = random() % (X_MAX + 1); /* modification */

		sleep(t);
		pthread_mutex_lock(&mutex); /* protection modification */

		if (random() % 2) { /* on ajoute ou on retire */
			printf("Le nombre mystère a été augmenté de %d !\n",x);
			mystere = ((mystere + x) > N_SUP) ? N_SUP : mystere + x;
		} else {
			printf("Le nombre mystère a été diminué de %d !\n",x);
			mystere = ((mystere x) < N_INF) ? N_INF : mystere x;
		}
		pthread_mutex_unlock(&mutex); /* fin de protection */
	}
}

void *timeout(void *arg) { /* routine de départ du thread timeout */
	sleep(TIMEOUT);
	printf("Temps écoulé ! Perdu !\n");
	exit(1); /* exit() termine tout */
}

int main(int argc, char **argv)
{
	int mystere_local, proposition;
	pthread_t t1, t2;

	srandom(getpid()); /* initialisation du générateur */
	pthread_mutex_init(&mutex, NULL); /* initialisation du mutex */
	mystere = random() % (N_SUP N_INF + 1) + N_INF; /* mystère */

	pthread_create(&t1, NULL, fuyeur, NULL); /* lancement des threads */
	pthread_create(&t2, NULL, timeout, NULL);

	while (1)
	{ /* jeu classique du nombre mystère */
		printf("Proposition ?\n");
		scanf(" %d", &proposition);

		pthread_mutex_lock(&mutex); /* protection lecture */
		mystere_local = mystere;
		pthread_mutex_unlock(&mutex); /* fin de protection */

		if (proposition > mystere_local)
			printf("Trop grand !\n");
		else if (proposition < mystere_local)
			printf("Trop petit !\n");
		else
			break;
	}

	printf("Gagne !\n");
	return 0;
}
```
