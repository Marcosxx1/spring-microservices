# PROVISORY README


## How to run locally

### Prerequisites
* Java Development Kit (JDK) 17: Ensure that you have JDK 17 installed on your machine.
* Maven: Ensure that Apache Maven is installed for building the Java services.
* Docker: Ensure that Docker is installed for containerization.
* Docker Compose: Ensure that Docker Compose is installed for managing multi-container Docker applications.

### 1. Build All Services
To build all Java services and create Docker images, run the `build-all-services.sh` script. This script will format the
code using Spotless and package the services into JAR files.

```bash
./build-all-services.sh
```

### 2. Build and Run Containers with Docker Compose
After building the services, you can use Docker Compose to build and run the containers. Docker Compose will manage the
multi-container setup and networking.
### Add this to the `pom.xml` for Dockerization:
```bash
docker-compose up --build
```


# Study notes
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
 primeiro: docker build . -t marcossata/accounts:s4  
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
  Or
  docker rmi <container_id> OR <container_name>

   ```

## Building Docker Images with Spring Boot Maven Plugin

This section explains how to configure the Spring Boot Maven plugin to build a Docker image of your application. By
specifying the necessary configuration, you can create a Docker image directly from Maven.

Maven Plugin Configuration
The following configuration in your pom.xml enables building a Docker image using the spring-boot-maven-plugin. The
build-image goal will be used to create an image based on the application’s properties and environment settings.

###  

  ```xml

<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <image><!-- This section configures the image creation -->
            <name>marcossata/${project.artifactId}:s4</name> <!-- Sets the name of the image -->
            <env>
                <BP_JVM_VERSION>${java.version}</BP_JVM_VERSION> <!-- Sets the Java version used in the image -->
            </env>
        </image>
    </configuration>
</plugin>
   ```

### Key Elements:

`<name>`: This tag specifies the name of the Docker image that will be built. It uses the artifact ID of your project,
which ensures that the image is named consistently with your project. In this case, the image name will be
marcossata/<artifactId>:s4.

`<env>`: This section allows you to pass environment variables to the build process. Here, we are setting the
BP_JVM_VERSION variable to use the Java version specified in the pom.xml.

### Command to Build the Image
To build the Docker image using the above configuration, run the following Maven command:


###  

  ```bash
  mvn spring-boot:build-image
   ```



## Google Jib
* First we add this dependency to our pom  
```xml
<plugin>
  <groupId>com.google.cloud.tools</groupId> <!--mvn compile jib:dockerBuild-->
  <artifactId>jib-maven-plugin</artifactId>
  <version>3.4.2</version>
  <configuration>
    <to>
      <image>marcossata/${project.artifactId}:s4</image>
    </to>
  </configuration>
</plugin>
   ```
* Run this command:
```bash
mvn compile jib:dockerBuild 
```

* Why use Google Jib?
  * It offers an alternative approach to Dockerfiles, with it, we can automatically generate production ready OCI images
  * from their application soure code without the need to write a Dockerfile and even local Docker setup


## Pushing images to Docker Hub
```bash
docker image push docker.io/<account_name>/<image_name>:<tag>

In my case:

docker image push docker.io/marcossata/loans:s4
```

## Using docker-compose file

```yaml
services:
    accounts:
        image: marcossata/accounts:s4 # Que subimos no Docker.io hub
        container_name: accounts-ms
        ports:
            - "8080:8080"
            #- Podemos definir várias portas para o serviço
        deploy:
            resources:
                 limits:
                     memory: 700m
        networks:
            - bankApplication # Rede interna que os serviços vão se comunicar

    loans:
        image: marcossata/loans:s4
        container_name: loans-ms
        ports:
            - "8090:8090"
        deploy:
            resources:
                limits:
                    memory: 700m
        networks:
            - bankApplication

    cards:
        image: marcossata/cards:s4
        container_name: cards-ms
        ports:
            - "9000:9000"
        deploy:
            resources:
                limits:
                    memory: 700m
        networks:
            - bankApplication
networks:
    bankApplication: # Rede interna que os serviços vão se comunicar
        driver: "bridge"
```

## Useful docker compose commands
- **Start services in the background (detached mode):
  ```bash
  docker-compose up -d 
  ```
- 
- **Stop all running services:**
  ```bash
  docker-compose down
  ```
-**Rebuild the services (useful when Dockerfile or dependencies change):**
```bash
docker-compose up --build
```

-**View logs from running services:**

```bash
docker-compose logs
```

- **docker-compose logs <service_name>**
```bash
docker-compose logs <service_name>

 ```

- **Scale a service to a specific number of instances:**
```bash
docker-compose up --scale <service_name>=<number>
 ```

- **Stop services without removing containers:**
```bash
docker-compose stop
 ```

- **Remove stopped containers:**
```bash
docker-compose rm
 ```

- **Execute a command inside a running service container:**
```bash
docker-compose exec <service_name> <command>
 ```

- **List containers associated with the compose project:**
```bash
docker-compose ps
 ```

- **pull the latest images for all services:**
```bash
docker-compose pull
 ```

- **Force recreation of containers (even if there are no changes):**
```bash
docker-compose up -d --force-recreate
 ```

- **Start specific services:**
```bash
docker-compose up -d <service_name>
 ```

- **Remove containers, networks, and volumes created by docker-compose up:**
```bash
docker-compose down --volumes
 ```

- **Start interactive shell inside a container:**
```bash
docker-compose run <service_name> /bin/sh
 ```


- ****
```bash
 ```