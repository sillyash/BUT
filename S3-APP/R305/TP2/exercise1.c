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

	if (proc == 0) // DRAW
	{
		close(pi);
		printf("Hi im the child\n");
		char req;
		double z;
		
		while (1)
		{
			/* wait for 8 bits (char) of data from draw */
			read(draw, &req, sizeof(req));

			z = ((double)random()/RAND_MAX);
			write(draw, &z, sizeof(z));
		}
	}
	else // PI
	{
		close(draw);
		printf("Hi I'm the parent\n");
		int N = 0, M = 0;
		double x=0, y=0;
		char msg = 'c';
		double PI;

		while (1)
		{
			/* request x */
			write(pi, &msg, sizeof(msg));
			read(pi, &x, sizeof(x));

			/* request y */
			write(pi, &msg, sizeof(msg));
			read(pi, &y, sizeof(y));

			N++;

			if ((x*x + y*y) <= 1) M++;

			PI = (double)(4*M)/N;

			printf("x \t%lf\ny \t%lf\nN \t%d\nM \t%d\nPI \t%lf\n\n", x, y, N, M, PI);
		}
		
		if (N == M) {}
		
	}


	shutdown(pi, SHUT_RDWR);
	close(pi);
	shutdown(draw, SHUT_RDWR);
	close(draw);
	return 0;
}
