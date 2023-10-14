#!/usr/bin/env bash

# Reference:
# https://container-registry.oracle.com/ords/f?p=113:4:113855913370151:::4:P4_REPOSITORY,AI_REPOSITORY,AI_REPOSITORY_NAME,P4_REPOSITORY_NAME,P4_EULA_ID,P4_BUSINESS_AREA_ID:803,803,Oracle%20Database%20Express%20Edition,Oracle%20Database%20Express%20Edition,1,0&cs=3T65RnpvElM3fe9l6FLZxhYIHsTSHMPpdcNPdJwbJZBdFEGPyfAC-vfkx4Gq_gyRLyfhFm5QI9sX18wNaTrWV_Q

# latest  linux/amd64 3.49 GB
# IMG=container-registry.oracle.com/database/express
# VER=latest

# 21.3.0-xe   linux/amd64 3.49 GB
# IMG=container-registry.oracle.com/database/express
# VER=21.3.0-xe


# 18.4.0-xe   linux/amd64 2.84 GB
IMG=container-registry.oracle.com/database/express
VER=18.4.0-xe
CONTAINER_NAME=oracle-18c

PORT_LISTENER=9121
PORT_EM=9122

PASS=Hiperquimia1

echo -e "Download Docker Image $IMG:$VER..."
docker pull $IMG:$VER


echo -e "Creating a DATA_DIRECTORY=$DATA_DIR"
DATA_DIR=/usr/local/mein_apps/docker/oracle/18.4
DATABASE=quironlab
DATABASE_DIR=$DATA_DIR/$DATABASE

if [[ ! -d $DATABASE_DIR ]]; then
    echo -e "Create a data bind dir: '$DATABASE_DIR' ..."
    mkdir -p $DATABASE_DIR
    chmod -Rf 777 $DATABASE_DIR
fi


echo -e "Build a container from '$IMG:$VER'..."

docker run -ti \
    --name $CONTAINER_NAME \
    -p $PORT_LISTENER:1521 \
    -p $PORT_EM:5500 \
    -e ORACLE_PWD=$PASS \
    -e ORACLE_CHARACTERSET=AL32UTF8 \
    -v $DATABASE_DIR:/opt/oracle/oradata \
    --restart always \
    -d $IMG:$VER


echo -e "Done!"
