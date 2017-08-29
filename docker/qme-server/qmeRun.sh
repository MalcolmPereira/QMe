#!/bin/sh
rm -rf mysql
docker run -it --name qme-server -p 6603:3306 -v $(pwd):/app -e MYSQL_DATABASE=qme -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbuser -e MYSQL_ROOT_PASSWORD=dbuser --publish 3306:3306 --publish 8002:8002 qme-server

#mysql --host 172.17.0.2 -u root -P 3306 -p QME
