/* SERVEUR. Lancer ce programme en premier (pas d'arguments). */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include <errno.h> // Include this header for error handling

#define BUFFER_SIZE 1024
#define PORT 5015

void handle_new_connection(int secoute, fd_set *ensemble, int *max) {
  int socserv = accept(secoute, NULL, NULL);
  if (socserv < 0) {
    perror("accept error");
    return;
  }
  FD_SET(socserv, ensemble);       // ajouter socket de service
  if (socserv > *max) *max = socserv; // mettre max à jour
  printf("Nouvelle connexion acceptée : %d\n", socserv);
}

void broadcast_message(int fd, fd_set *ensemble, int max, char *message, int bytesRead) {
  /* On parcourt les sockets */
  for (int fd2 = 0; fd2 <= max; fd2++) {
    if (FD_ISSET(fd2, ensemble) && fd2 != fd) {
      int bytesWritten = write(fd2, &message, bytesRead);
      if (bytesWritten <= 0) {
        if (errno == EPIPE) {
          printf("Socket fermé : %d\n", fd2);
          FD_CLR(fd2, ensemble);
          shutdown(fd2, SHUT_RDWR);
          close(fd2);
        } else {
          perror("write error");
        }
      }
    }
  }
}

void handle_client_message(int fd, fd_set *ensemble, int max, char *message) {
  int bytesRead = read(fd, message, BUFFER_SIZE);
  if (bytesRead <= 0) {
    printf("Client déconnecté\n");
    FD_CLR(fd, ensemble);            // on retire le socket de service
    shutdown(fd, SHUT_RDWR);
    close(fd);
  } else {
    printf("Message reçu : %s\n", message);
    broadcast_message(fd, ensemble, max, message, bytesRead);
  }
}


int main()
{
  int secoute, max;
  fd_set ensemble;
  struct sockaddr_in server_addr;

  // Create socket
  secoute = socket(AF_INET, SOCK_STREAM, 0);
  if (secoute < 0) {
    perror("socket");
    exit(EXIT_FAILURE);
  }

  // Set up server address
  server_addr.sin_family = AF_INET;
  server_addr.sin_addr.s_addr = INADDR_ANY;
  server_addr.sin_port = htons(PORT);

  // Bind socket
  if (bind(secoute, (struct sockaddr *)&server_addr, sizeof(server_addr)) < 0) {
    perror("bind");
    close(secoute);
    exit(EXIT_FAILURE);
  }

  // Listen for connections
  if (listen(secoute, 5) < 0) {
    perror("listen error");
    close(secoute);
    exit(EXIT_FAILURE);
  }

  char message[BUFFER_SIZE];
  
  // Initialize fd sets
  FD_ZERO(&ensemble);
  FD_SET(secoute, &ensemble);
  max = secoute;

  printf("Serveur en attente de connexions...\n");

  while (1)
  {
    fd_set read_fds = ensemble;

    // Use select to monitor multiple file descriptors
    if (select(max + 1, &read_fds, NULL, NULL, NULL) < 0) {
      perror("select error");
      close(secoute);
      exit(EXIT_FAILURE);
    }

    for (int fd = 0; fd <= max; fd++) {
      if (FD_ISSET(fd, &read_fds)) {
        if (fd == secoute) {
          handle_new_connection(secoute, &ensemble, &max);
        } else {
          handle_client_message(fd, &ensemble, max, message);
        }
      }
    }
  }

  printf("Serveur terminé.\n");
  // Terminate and free resources
  shutdown(secoute, SHUT_RDWR);
  close(secoute);

  return 0;
}
