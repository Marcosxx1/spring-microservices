FROM openjdk:17-jdk-slim

MAINTAINER marcosxx1

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

# docker build . -t marcossata/accounts:s4
# list:  docker images
# run accounts: docker run -p 8080:8080 marcossata/accounts:s4