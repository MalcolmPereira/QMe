#!/bin/sh
docker run -it --name qme-webapp -v ~/git/qme/client/angular/qmeAngular/app:/usr/share/nginx/app:ro --publish 8000:8000 qme-webapp
