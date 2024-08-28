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