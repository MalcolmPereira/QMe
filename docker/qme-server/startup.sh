#!/bin/sh
if [ -d /app/mysql ]; then
  echo "[i] MySQL directory already present, skipping creation"
else
  echo "[i] MySQL data directory not found, creating initial DBs"
  mysql_install_db --user=root > /dev/null
  MYSQL_DATABASE=${MYSQL_DATABASE:-""}
  MYSQL_USER=${MYSQL_USER:-""}
  MYSQL_PASSWORD=${MYSQL_PASSWORD:-""}
  echo "[i] MySQL root Password: $MYSQL_ROOT_PASSWORD"
  echo "[i] MySQL Database: $MYSQL_DATABASE"
  echo "[i] MySQL Database User: $MYSQL_USER"
  echo "[i] MySQL Database Password: $MYSQL_PASSWORD"

  if [ ! -d "/run/mysqld" ]; then
    mkdir -p /run/mysqld
  fi

  tfile=`mktemp`
  if [ ! -f "$tfile" ]; then
      return 1
  fi

cat << EOF > $tfile
USE mysql;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY "$MYSQL_ROOT_PASSWORD" WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;
UPDATE user SET password=PASSWORD("") WHERE user='root' AND host='localhost';
DROP DATABASE IF EXISTS test;
EOF

  /usr/bin/mysqld --user=root --bootstrap --verbose=0 < $tfile
  rm -f $tfile

  /usr/bin/mysqld --user=root --bootstrap --verbose=0 < /qme.sql

fi

exec /usr/bin/mysqld --user=root --console
