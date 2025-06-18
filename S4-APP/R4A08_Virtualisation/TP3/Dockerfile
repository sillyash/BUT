FROM debian:latest

EXPOSE 80

RUN apt-get update
RUN apt-get install --yes nagios4
RUN apt-get install --yes iproute2 procps nano

# Variables
ARG NAGIOS_APACHE=/etc/apache2/conf-enabled/nagios4-cgi.conf
ARG HTDIGEST=/etc/nagios4/htdigest.users

# Nagios4 apache config
COPY nagios4-cgi.conf ${NAGIOS_APACHE}

# Activate Apache server modules
RUN a2enmod cgi rewrite

# Activate auth on nagios
RUN sed -i 's/use_authentication=0/use_authentication=1/' /etc/nagios4/cgi.cfg
RUN rm ${HTDIGEST}
COPY htdigest.users ${HTDIGEST}

# Manage permission
# RUN chmod -R 755 /etc/nagios4/

# Config files
COPY localhost.cfg /etc/nagios4/objects/
# ...

CMD apachectl start && nagios4 /etc/nagios4/nagios.cfg
