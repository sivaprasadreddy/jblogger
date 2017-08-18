FROM frolvlad/alpine-oraclejdk8:slim
ADD ["target/jblogger.jar", "app.jar"]
EXPOSE 8080
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]
