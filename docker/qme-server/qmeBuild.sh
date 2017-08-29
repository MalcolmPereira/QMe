#!/bin/sh
rm qmerest.jar
cp ../../service/java/qmedataservice/qmerest/build/libs/qmerest.jar qmerest.jar
docker build --tag qme-server .
