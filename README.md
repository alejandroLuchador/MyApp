# MyApp

This repository contains a simple Spring Boot app writen in Java. The reason I chose Java was simply because I am most familiar with that language

The application exposes two endpoints:

* root context i.e. http://localhost:8083
* /info endpoint i.e. http://localhost:8083/info

More about that later.



## How to run the application:

There are two main ways to run the application:

* By downloading the latest container image from Docker Hub. The image is published to docker hub by github actions CI pipeline configured for this project. The following command will download the container image and run it by exposing the application on port 8084 (note: having local instace of Docker Desktop is a pre-requisite).

```bash
docker run -p 8084:8083 "alcos/my-app:latest"
```

You should then be able to acces the application by following [these](#accessing-the-containerised-application) steps

* By cloning repository and [running locally](#running-locally) in which case pre-requisites are Apache Maven 3.6.3 and Oracle JDK: 1.8



### Running locally:

First you will need to clone this github repo

```bash
git clone https://github.com/alejandroLuchador/MyApp.git
cd MyApp
```

The simplest way to run the application from the code repo is by running the followig command.

```bash
mvn spring-boot:run
```

The command below will build the code and run the application. The application should then be accessible via your favourite browser via the following URL http://localhost:8083/info

You can further package the application into a docker container.

First step is to package the application via the following command

```bash
mvn package
```

Once completed the command will package the application into MyApp/target/MyApp-1.0.jar

The application can now be deployed to a docker container image using the following command.


```bash
docker build -t myapp/myapp-spring-boot-docker .
```

Once the command is completed you should be able to see the newly created container image by running the following command.
```bash
docker image ls
```

The output should look similar to 
```
REPOSITORY                          TAG                 IMAGE ID            CREATED              SIZE
myapp/myapp-spring-boot-docker      latest              65fe4fb3e3a4        About a minute ago   122MB
```
Finally we can run the container using the following command, which exposes container port 8083 on localhost port 80804.

```bash
docker run -p 8084:8083 -t "myapp/myapp-spring-boot-docker"
```

### Accessing the containerised application

If you followed the instructions and used port 8084, the application should then be accessible via your favourite browser via the following URL http://localhost:8084/info and you should see json response similar to 

```json
{
  "environment" : {
    "log_level" : "info",
    "service_port" : "8083"
  },
  "service_name" : "MyApp",
  "git_commit_sha" : "08de467f7a4f1e0a4936c4a13c4c1e568792e33a",
  "version" : "1.0"
}
```

Access to the /info endpoint will be logged to the console using `org.slf4j.Logger` and should look similar to the following

```
2020-01-07 01:26:38.342  INFO 1 --- [nio-8083-exec-4] demo.AppController                       : Loading "info" endpoint...
```

### Possible Improvements

To further demostrate build automation the following items could be added

* At present setup instructions specify what pre-requites user needs to install to run the project. This could be automated by using Maven Wrapper, which is an easy way to ensure a user of of this project's Maven build has everything necessary to run the Maven build.

* The entire process could be added to CI/CD pipeline, which would unit test the application by running `mvn test` (you can run this locally, there is a very basic unit test added), package it, deploy the container image to a registry, deploy to a container orchestrator e.g. GKE and run further tests (e.g. API tests that /info endpoint returns correct info), load test to ensure the /info endpoint can gracefully serve all the requests.
