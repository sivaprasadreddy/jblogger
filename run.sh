#!/bin/sh

dc_file="docker-compose.yml"
jblogger="jblogger"

build_app() {
    ./mvnw verify
}

start() {
    echo 'Starting jblogger....'
    build_app
    docker-compose -f ${dc_file} up --build --force-recreate -d ${jblogger}
    docker-compose -f ${dc_file} logs -f
}

stop() {
    echo 'Stopping jblogger....'
    docker-compose -f ${dc_file} stop
    docker-compose -f ${dc_file} rm -f
}

restart() {
  stop
  sleep 3
  start
}

action="start"

if [ "$1" != "0"  ]
then
    action="$*"
fi

eval "${action}"
