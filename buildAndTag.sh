#!/usr/bin/env bash
./mvnw package -Pnative

docker build -f src/main/docker/Dockerfile.native -t k8s-first-app:0.0.20 .
