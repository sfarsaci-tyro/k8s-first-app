#!/usr/bin/env bash
./mvnw package -Pnative
docker build -f src/main/docker/Dockerfile.native -t k8s-first-app:0.0.5 .
docker run -i --rm -p 8080:8080 k8s-first-app 0.0.5