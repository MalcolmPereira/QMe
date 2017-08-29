#!/bin/sh
rm -rf mysql
`docker rm $(docker ps -a)`
`docker rmi $(docker images -q -a)`
