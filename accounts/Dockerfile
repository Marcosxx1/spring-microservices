FROM openjdk:17-jdk-slim

MAINTAINER marcosxx1

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

# docker build . -t marcossata/accounts:s4
# list:  docker images OU docker ps -a
# run accounts: docker run -d  -p 8080:8080 marcossata/accounts:s4

# Stop and remove the container(s) using the image:

# docker stop <container_id>
# docker rm <container_id>