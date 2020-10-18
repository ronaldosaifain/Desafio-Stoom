FROM openjdk:8

EXPOSE 8080

RUN mkdir  /app

ENV APP_NAME=endereco-0.0.1-SNAPSHOT.jar

COPY /target/${APP_NAME} /app/

ENTRYPOINT [ "java", "-jar", "/app/endereco-0.0.1-SNAPSHOT.jar" ]


