#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

const unsigned short int PORT = 5015;

int main(int argc, char* argv[])
{
	if (argc < 3){
		printf("Invalid number of arguments : please input the IP address of the server and a message.\n");
		exit(1);
	}

	char* ip = argv[1];
	char* msg = argv[2];

	printf("IP \t%s\nMSG \t%s\n", ip, msg);

	struct sockaddr_in saddr = {0};
	saddr.sin_family = AF_INET;
	saddr.sin_port = htons(PORT);
	saddr.sin_addr.s_addr = inet_addr(ip);

	int sclient = socket(
		AF_INET, 
		SOCK_STREAM, 
		0
	);

	int connectStatus = connect(sclient, (struct sockaddr*)&saddr, sizeof(saddr));

	if (connectStatus == -1) {
		perror("Connection failed ! ");
		exit(2);
	}

	write(sclient, argv[2], strlen(msg));
	char buf[1024];

	while(1) {
		int response = read(sclient, &buf, sizeof(buf));
		if (response == -1) {
			perror("read() error !\n");
			exit(3);
		}
		if (response == 0) {
			break;
		}
	}

	printf("Server response :\n%s\n", buf);
	printf("\nExiting...\n");

	shutdown(sclient, SHUT_RDWR);
	close(sclient);
	return 0;
}