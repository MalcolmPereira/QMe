#Use alphine - light weigth linux distro
FROM alpine:latest

MAINTAINER Malcolm Pereira <malcolm.j.pereira@gmail.com>

#execute as root user
USER root

#Update Packages and install open JDK 8
RUN apk update && apk upgrade && apk add openjdk8 &&  rm -rf /var/cache/apk/*
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
CMD ["/usr/bin/java", "-version"]

#Install MySQL
RUN apk add --no-cache mysql mysql-client  && rm -f /var/cache/apk/*

#Initialize Work dir and Volume
WORKDIR /app
VOLUME  /app

#Copy Start up Script and set script execution permission
COPY startup.sh /startup.sh
RUN chmod 777 /startup.sh

COPY qme.sql /qme.sql
RUN chmod 777 /qme.sql

#Copy MySQL conf and set permission
COPY my.cnf /etc/mysql/my.cnf

#Copy Qme Service Start Script
COPY qmeserver.sh /qmeserver.sh
RUN chmod 777 /qmeserver.sh

#Expose MySQL Port 3306
EXPOSE 3306

#Expose QMe Server Port 8002
EXPOSE 8002

#Start MySQL
CMD ["/startup.sh"]
