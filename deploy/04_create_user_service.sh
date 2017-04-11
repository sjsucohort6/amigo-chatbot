#!/usr/bin/env bash

eval $(docker-machine env node-1)

# Create user-db service
docker service create --name user-db \
    --network user-net \
    mongo:3.2.10

# Create user-service service
docker service create --name user-service \
  -e DB=user-db \
  --network user-net \
  --network proxy-net \
  --label com.df.notify=true \
  --label com.df.distribute=true \
  --label com.df.servicePath=/api/v1.0/users \
  --label com.df.port=8080 \
  sjsucohort6/user-service:1.0

docker service ps user-db
docker service ps user-service

echo "Created user service and user db"