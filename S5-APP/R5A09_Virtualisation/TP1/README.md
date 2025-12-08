# Virtualisation - TP1

## 1. Rappels Docker

### 1.2. Première étape

```bash
docker load -i /home/nfs/virtualization/httpd.tar
docker run --name tutu -d httpd:latest -p 8080:80
```

http://localhost:8080

```bash
docker kill tutu && docker rm tutu
```

### 1.3. Deuxième étape

> See [Dockerfile](Apache/Dockerfile)

```bash
docker build -t apache:latest .
docker run --name toto -d -p 8080:80 apache:latest
```

http://localhost:8080

```bash
docker kill toto && docker rm toto
```

## 2. Déploiement de Docker Container avec les variables d’environnement

### 2.1. Première étape

```bash
docker load -i /home/nfs/virtualization/mysql.tar
docker run --name mysql-test -e MYSQL_ROOT_PASSWORD=secret -d mysql:latest
docker exec -it mysql-test bash
mysql -u root -p
```

```bash
CREATE DATABASE test;
use test;
CREATE TABLE User(uid INT PRIMARY KEY, name NVARCHAR(100) NOT NULL);
INSERT INTO User VALUES (1, "Alexandre Charpentier");
INSERT INTO User VALUES (2, "Alain Sandoz");
INSERT INTO User VALUES (3, "Ash Merienne");
table User;
```

Should display:

```text
+-----+-----------------------+
| uid | name                  |
+-----+-----------------------+
|   1 | Alexandre Charpentier |
|   2 | Alain Sandoz          |
|   3 | Ash Merienne          |
+-----+-----------------------+
```

```bash
exit
exit
docker kill mysql-test
docker rm mysql-test
```

### 2.2. Deuxième étape

```bash
docker load -i /home/nfs/virtualization/mysql.tar
docker run --name mysql-test -v sql_data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=secret -d mysql:latest
docker exec -it mysql-test bash
mysql -u root -p
```

```bash
CREATE DATABASE test;
use test;
CREATE TABLE User(uid INT PRIMARY KEY, name NVARCHAR(100) NOT NULL);
INSERT INTO User VALUES (1, "Alexandre Charpentier");
INSERT INTO User VALUES (2, "Alain Sandoz");
INSERT INTO User VALUES (3, "Ash Merienne");
table User;
```

Should display:

```text
+-----+-----------------------+
| uid | name                  |
+-----+-----------------------+
|   1 | Alexandre Charpentier |
|   2 | Alain Sandoz          |
|   3 | Ash Merienne          |
+-----+-----------------------+
```

The data is stored in `sql_data`:

```bash
exit
exit
docker volume inspect sql_data
```

```bash
docker kill mysql-test
docker rm mysql-test
docker volume ls
```

You can see `sql_data` is still there !

That's because volumes are persistent and not dependent on a container: in fact, they're often used to link filesystems between containers.

### 2.3. Déploiement de Réseau Docker

```bash
docker network ls
```

They're quite the same as volumes, but instead of sharing filesystems between containers, they share networks.

They're very practical because you can use container names and they are resolved to IP's by docker: no need to manually assign an IP address !

## 3. Notre premier réseau de conteneurs

```bash
docker network crate mongo_network
```

### 3.1. Première étape

```bash
docker load -i /home/nfs/virtualization/mongo.tar
docker run -d -p 27017:27017 --network mongo_network --name mongodb \
-e MONGO_INITDB_ROOT_USERNAME=mongouser \
-e MONGO_INITDB_ROOT_PASSWORD=secret \
--hostname mongo mongo:latest
```

http://localhost:27017

### 3.2. Deuxième étape

```bash
docker load -i /home/nfs/virtualization/mongo-express.tar
docker run -d -p 8081:8081 --network mongo_network --name mongo_express \
-e ME_CONFIG_MONGODB_ADMINUSERNAME=mongouser \
-e ME_CONFIG_MONGODB_ADMINPASSWORD=secret \
-e ME_CONFIG_MONGODB_SERVER=mongo \
--hostname express mongo-express:latest
```

http://localhost:8081/

|          |       |
|:---------|:------|
| **Username** | admin |
| **Password** | pass  |

> Create database > "test_db"

And voilà !

### 3.3. Déploiement de Docker Compose

> See [Docker Compose file](docker-compose.yml)

This Docker Compose project has two services:

- **db**: a *MySQL* container
- **phpmyadmin**: a *PHPMyAdmin* container

The **db** service is mapped to port *6033* on host machine, and it's database filesystem is mapped to volume *dbdata* for permanent storage.

The **phpmyadmin** service is mapped to port *8080* on host machine.

Both containers are configured with environment variables (user, password, port, etc.).

```bash
docker compose build
docker compose up
```

http://localhost:8081

```bash
$ docker volume list

DRIVER    VOLUME NAME
local     tp1_dbdata
```

```bash
$ docker compose ps

NAME               IMAGE                   COMMAND                  SERVICE      CREATED         STATUS         PORTS
tp1-db-1           mysql:latest            "docker-entrypoint.s…"   db           4 minutes ago   Up 4 minutes   33060/tcp, 0.0.0.0:6033->3306/tcp, [::]:6033->3306/tcp
tp1-phpmyadmin-1   phpmyadmin/phpmyadmin   "/docker-entrypoint.…"   phpmyadmin   4 minutes ago   Up 4 minutes   0.0.0.0:8081->80/tcp, [::]:8081->80/tcp
```

Here we can see `docker-compose` created a volume and two containers.

We can see these are prepended with `'tp1'` to avoid confusion with other containers from other projects.

### 3.4. Mise en place d'un *'bind mount'* dans un stack

Dans le répertoire courant, reprenez le tutoriel officiel qui se trouve sur la [page suivante](https://docs.docker.com/compose/gettingstarted/).

> Voir [composetest/](./composetest/)

Interprétez l’idée principale de ce tutoriel après avoir effectué toutes les étapes.

> L'idée principale du tutoriel est de voir à la fois les bases d'une image (*Dockerfile*),
> et un réseau de conteneurs (*docker-compose*), et **surtout** le *bind mount*, qui consiste
> à avoir un montage de volume sur un dossier local de la machine hôte: ici cela sert à charger
> le code de l'application Python dynamiquement depuis la machine hôte.