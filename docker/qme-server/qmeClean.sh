#!/bin/sh
`docker rm $(docker ps -a)`
`docker rmi $(docker images -q -a)`
