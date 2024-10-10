/* CLIENT. Donner l'adresse IP et un pseudo en paramètre */
/* exemple "client 127.0.0.1 dr.ced", lancer en dernier. */

#include <stdio.h>             /* fichiers d'en-tête classiques */
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <errno.h>

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

  printf("IP \t%s\nUsername \t%s\n", ip, username);

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


  /* 4. On communique. */
  int proc = fork();

  if (proc == 0) // child
  {

  } else // parent
  {

  }

  /* 5. On termine et libère les ressources. */

  return 0;
}
