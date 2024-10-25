/* SERVEUR. Lancer ce programme en premier (pas d'arguments). */

#include <stdio.h>                  /* fichiers d'en-tête classiques */
#include <stdlib.h>
#include <sys/select.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>
#include <fcntl.h>
#include <errno.h>
#include <sys/socket.h>             /* fichiers d'en-tête "réseau" */
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

#define PORT_SERVEUR 5015           /* Numéro de port pour le serveur */
#define MAX_CLIENTS   128           /* Nombre maximum de clients */
#define BUFFER_SIZE  1024           /* Taille maximum des messages */

int main(int argc, char *argv[])
{
  /* 1. On crée la socket d'écoute. */
  int secoute = socket(
    AF_INET,
    SOCK_STREAM,
    0
  );

  /* 2. On prépare l'adresse du serveur. */
  struct sockaddr_in saddr = {0};
  saddr.sin_family = AF_INET;
  saddr.sin_port = htons(PORT_SERVEUR);
  saddr.sin_addr.s_addr = htonl(INADDR_ANY);

  /* 3. On attache la socket a l'adresse du serveur. */
  int bindFail = bind(
    secoute,
    (struct sockaddr*) &saddr,
    sizeof(saddr)
  );

  if (bindFail) {
    perror("Bind failed.");
    exit(2);
  }

  /* 4. Enregistrement auprès du système. */
  listen(secoute, 10);
  char message[BUFFER_SIZE];

  fd_set ensemble, temp;
  FD_ZERO(&ensemble); // vider l’ensemble principal
  int max = secoute;

  while (1)
  {
    printf("Serveur en attente de nouveaux clients ou messages.\n");

    temp = ensemble;                      // copier l’ensemble
    select(max+1, &temp, NULL, NULL, NULL);

    /* Pour chaque client */
    for (int fd=0; fd<=max; fd++)
    {
      if (!FD_ISSET(fd, &temp)) continue; // fd pas prêt

      /* 5. Si on a reçu une demande sur la socket d'écoute... */
      if (fd == secoute) {                // demande de connexion
        int socserv = accept(
          secoute,
          NULL,
          NULL
        );
        FD_SET(socserv, &ensemble);       // ajouter socket de service
        if (socserv > max) max = socserv; // mettre max à jour
        continue;
      }
    
      /* 6. Si on a reçu des données sur une socket de service... */
      int bytesRead = read(fd, message, BUFFER_SIZE);
      if (bytesRead <= 0) {
        FD_CLR(fd, &ensemble);            // on retire le socket de service
        shutdown(fd, SHUT_RDWR);
        close(fd);
        continue;
      }

      for (int fd2=0; fd2<max; fd2++) {
        if (FD_ISSET(fd2, &temp) && fd2 != secoute && fd2 != fd) {
          write(fd2, message, BUFFER_SIZE);
        }
      }
    }
  }

  /* 7. On termine et libère les ressources. */
  shutdown(secoute, SHUT_RDWR);
  close(secoute);

  return 0;
}
