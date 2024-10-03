#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

int main(int argc, char* argv[])
{
	srandom(time(NULL));
	int sockfd[2];

	printf("PPID %d\n", getpid());

	socketpair(
		AF_UNIX,
		SOCK_STREAM,
		0,
		sockfd
	);

	int pi = sockfd[0];
	int draw = sockfd[1];
	int proc = fork();

	if (proc == 0)
	{
		close(pi);
		printf("Hi im the child\n");
		char req;
		double z;

		
		
		while (1)
		{
			printf("PRINTING IN THE WHILE\n");
			/* wait for 8 bits (char) of data from draw */
			read(pi, &req, sizeof(char));

			z = ((double)random()/RAND_MAX);
			printf("z \t%lf", z);
			write(draw, &z, sizeof(z));
		}
	}
	else
	{
		close(draw);
		int N = 0, M = 0;
		double x, y;
		char msg = 'c';

		while (1)
		{
			write(pi, &msg, sizeof(msg)); /* request rand double */
			read(draw, &x, sizeof(double));

			write(pi, &msg, sizeof(msg));
			read(draw, &y, sizeof(double));

			printf("x \t%lf\ny \t%lf\n", x, y);
		}
		
		if (N == M) {}
		
	}


	shutdown(pi, SHUT_RDWR);
	close(pi);
	shutdown(draw, SHUT_RDWR);
	close(draw);
	return 0;
}
