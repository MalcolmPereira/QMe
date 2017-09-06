#Use alphine - light weigth linux distro
FROM nginx:alpine

MAINTAINER Malcolm Pereira <malcolm.j.pereira@gmail.com>

#execute as root user
USER root

#Update Packages and install open JDK 8
RUN apk update && apk upgrade &&  rm -rf /var/cache/apk/*

COPY nginx.conf /etc/nginx/nginx.conf

#Expose Port 8000
EXPOSE 8000



