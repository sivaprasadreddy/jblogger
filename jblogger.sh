#!/bin/sh

declare project_dir=$(dirname $0)
declare docker_compose_file=${project_dir}/docker-compose.yml
declare jblogger="jblogger"

function start() {
    echo 'Starting jblogger....'
    build_app
    stop
    docker-compose -f ${docker_compose_file} up --build --force-recreate -d ${jblogger}
    docker-compose -f ${docker_compose_file} logs -f
}

function stop() {
    echo 'Stopping jblogger....'
    docker-compose -f ${docker_compose_file} stop
    docker-compose -f ${docker_compose_file} rm -f
}

function build_app() {
    ./mvnw clean package -DskipTests
}

function start_fakesmtp() {
    nohup java -jar support/fakesmtp/fakesmtp-2.0.jar -s -m -p 2525 -a 127.0.0.1 &
}

action="start"

if [[ $1 != "0"  ]]
then
    action=$@
fi

eval ${action}
