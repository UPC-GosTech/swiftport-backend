FROM openjdk:24-jdk
VOLUME /tmp
EXPOSE 8080
COPY target/swiftport-backend-0.0.3-SNAPSHOT.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar