# PROVISORY README

### Add this to the `pom.xml` for Dockerization:

```xml
<packaging>jar</packaging> <!-- DOCKERIZATION -->
``` 

### Steps to Run the Application
* Delete the contents of the target directory:
  ```bash
  rm -rf target/* 
  ```

### Generate the JAR file using Maven:
  ```bash
  mvn clean install
  ```

### Alternatively, run the application directly with Java:
  ```bash
  java -jar target/accounts-0.0.1-SNAPSHOT.jar
  ```


## Docker Instructions

### Build the Docker image:
  ```bash
  docker build . -t marcossata/accounts:s4
   ```

### List Docker images or running containers:
  ```bash
  docker images
  docker ps -a
   ```

### Run the Docker container:
  ```bash
  docker run -d -p 8080:9000 marcossata/accounts:s4
   ```

## Stop and Remove Docker Containers

### Stop the container:
  ```bash
  docker stop <container_id>
   ```

### Remove the container:
  ```bash
  docker rm <container_id>
   ```



### 
  ```bash
  
   ```