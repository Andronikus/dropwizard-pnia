FROM openjdk:8-jre-alpine

EXPOSE 8080 8081
ENV BASE_DIR=/opt/pnia

WORKDIR $BASE_DIR
ENTRYPOINT ["java", "-jar", "pnia-1.0.jar", "server", "config.yml"]

COPY config.yml $BASE_DIR/config.yml
COPY target/pnia-1.0.jar $BASE_DIR/pnia-1.0.jar