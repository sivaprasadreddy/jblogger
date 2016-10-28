@echo off
rem -------------------------------------------------------------------------
rem Build configuration parameter definitions
rem -------------------------------------------------------------------------
rem

set skipTests=-Dmaven.test.skip=true

set configFile=config/production.properties

call mvn %skipTests% clean package

rem -------------------------------------------------------------------------
rem run SpringBoot app
rem -------------------------------------------------------------------------
rem

java -jar target/jblogger.jar --spring.config.location=%configFile%