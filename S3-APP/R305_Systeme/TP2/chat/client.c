/* CLIENT. Donner l'adresse IP et un pseudo en paramètre */
/* exemple "client 127.0.0.1 dr.ced", lancer en dernier. */

#include <stdio.h>             /* fichiers d'en-tête classiques */
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>        /* fichiers d'en-tête "réseau" */
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

#define PORT_SERVEUR 5015      /* Numéro de port pour le serveur */
#define BUFFER_SIZE  1024      /* Taille maximum des messages */

int main(int argc, char *argv[])
{
  if (argc < 3){
		printf("Invalid number of arguments : please input the IP address of the server and a username.\n");
		exit(1);
	}

	char* ip = argv[1];
	char* username = argv[2];

  printf("IP \t\t%s\nUsername \t%s\n", ip, username);

  /* 1. On crée la socket du client. */
  int soc = socket(
    AF_INET,
    SOCK_STREAM,
    0
  );

  /* 2. On prépare l'adresse du serveur. */
  struct sockaddr_in saddr = {0};
  saddr.sin_family = AF_INET;
	saddr.sin_port = htons(PORT_SERVEUR);
	saddr.sin_addr.s_addr = inet_addr(ip);

  /* 3. On demande une connexion au serveur, tant qu'on y arrive pas. */
  int tries = 0;
  while (connect(soc, (struct sockaddr*) &saddr, sizeof(saddr)))
  {
    sleep(1);
    tries++;
    if (tries >= 3) {
      perror("Connexion to server failed !");
      return 2;
    }
  }

  /* 4. On communique. */
  char bufReceive[BUFFER_SIZE],bufInput[BUFFER_SIZE];
  char msgSend[BUFFER_SIZE*4];

  int proc = fork();

  if (proc == 0) {
    while (1) {
      int bytesReceived = read(soc, &bufReceive, BUFFER_SIZE - 1);
      bufReceive[bytesReceived] = '\0';  // Null-terminate the input string
      write(1, &bufReceive, strlen(bufReceive));
    }
  }
  else {
    while (1) {
        strncpy(msgSend, "", strlen(msgSend));
        
        int bytesReceived = read(0, bufInput, BUFFER_SIZE - 1);  // Reading user input
        bufInput[bytesReceived] = '\0';  // Null-terminate the input string

        // Copy username into msgSend
        strncpy(msgSend, username, strlen(username));

        // Concatenate the input from bufInput into msgSend
        strncat(msgSend, " : ", BUFFER_SIZE - strlen(" : ") - 1);
        strncat(msgSend, bufInput, BUFFER_SIZE - strlen(username) - 1);

        // Print the resulting concatenated message
        write(soc, &msgSend, strlen(msgSend));
    }
  }

  /* 5. On termine et libère les ressources. */
  shutdown(soc, SHUT_RDWR);
  close(soc);
  return 0;
}
