#!/bin/sh
rm -rf mysql
docker run -it --name mysql -p 3306:3306 -v $(pwd):/app -e MYSQL_DATABASE=qme -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbuser -e MYSQL_ROOT_PASSWORD=dbuser --publish 6603:3306 qme-server

#mysql --host 172.17.0.2 -u root -P 3306 -p QME
