FROM --platform=linux/amd64 amazoncorretto:21-alpine-jdk
RUN apk add --no-cache tzdata

WORKDIR /app
VOLUME /tmp
ARG JAR_FILE
ADD target/*.jar /app/service.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","-Xverify:none","-Djava.security.egd=file:/dev/./urandom","/app/service.jar" ]