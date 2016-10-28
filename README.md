# JBlogger
A simple blog application using SpringBoot.

Run the application using run-app.bat or run-app.sh

Access Application: http://localhost:8080/

Monitoring Dashboard using JavaMelody : http://localhost:8080/monitoring

Login with admin@gmail.com/admin

Running on Heroku https://sivalabs-jblogger.herokuapp.com/

Build Status

[![Build Status](https://travis-ci.org/sivaprasadreddy/jblogger.svg?branch=master)](https://travis-ci.org/sivaprasadreddy/jblogger)

## Running on Docker container

Build the docker image

jblogger> mvn clean package docker:build

### Running MySQL and Application containers individually


__Run mysql :__

docker run -d --name demo-mysql -e MYSQL_ROOT_PASSWORD=secret123 -e MYSQL_DATABASE=demo -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=secret mysql:latest

__Run application linking to demo-mysql container:__

docker run -d --name jblogger --link demo-mysql:mysql -p 8080:8080 sivaprasadreddy/jblogger


### Running MySQL and Application using docker-compose

Navigate to the directory where **docker-compose.yml** file is there.

src/main/docker> docker-compose up