# TP1


## build-debian

### Build

```bash
docker build -t debian-hangman ./debian-hangman
```

### Run

```bash
docker run -it debian-hangman
```

## build-nginx

### Build

```bash
docker build -t nginx-hello ./nginx-hello
```

### Run

```bash
docker run -p 8080:80 -d nginx-hello
```

### Test

```bash
firefox 127.0.0.1:8080/hello.html
```

## debian2

### Build

```bash
docker build -t debian2 ./debian2
```

### Run

```bash
docker run -it --user ash debian2
```
