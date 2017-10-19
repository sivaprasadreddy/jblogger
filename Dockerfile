FROM frolvlad/alpine-oraclejdk8:slim
ADD ["target/jblogger.jar", "app.jar"]
EXPOSE 8080
EXPOSE 8787
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n  -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]
