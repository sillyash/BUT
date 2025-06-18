# TP3 - Nagios


## Build

```bash
docker build -t nagios .
```

## Run

### Launch the container (nagios4 only)

```bash
docker run --name CONTAINER_NAME -d -p 8080:80 nagios 
```

## Launch all services

```bash
docker compose up
```

## Access the interface

- [localhost:8080](http://localhost:8080/nagios4/)
- Username : nagiosadmin
- Password : admin

## Modify config files

```bash
docker exec -it CONTAINER_NAME /bin/bash
```

```bash
cd /etc/nagios4/objects
nano localhost.cfg
```

### Reload from config

```bash
docker cp localhost.cfg CONTAINER_NAME:/etc/nagios4/objects/localhost.cfg
docker exec CONTAINER_NAME service nagios4 restart
```
