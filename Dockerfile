# build phase
FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

# execute
FROM openjdk:8-jre-alpine

EXPOSE 8080 8081
ENV BASE_DIR=/opt/pnia

WORKDIR $BASE_DIR

COPY config.yml $BASE_DIR/config.yml
COPY --from=MAVEN_BUILD target/pnia-1.0.jar $BASE_DIR/pnia-1.0.jar

ENTRYPOINT ["java", "-jar", "pnia-1.0.jar", "server", "config.yml"]