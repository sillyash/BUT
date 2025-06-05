FROM nginx:latest

WORKDIR /usr/share/nginx/html

RUN echo "<body>Hello World!</body>" > hello.html
RUN chmod 644 hello.html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
