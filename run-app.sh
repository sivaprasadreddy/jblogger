#!/bin/sh

# -------------------------------------------------------------------------
# Configuration parameter definitions
# -------------------------------------------------------------------------
#

skipTests="-Dmaven.test.skip=true"

configFile="./config/production.properties"

mvn $skipTests clean package

# -------------------------------------------------------------------------
# run SpringBoot app
# -------------------------------------------------------------------------
#
java -jar target/jblogger.jar --spring.config.location=$configFile