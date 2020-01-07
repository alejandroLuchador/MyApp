# MyApp

This repository contains a simple Spring Boot app writen in Java. The reason I chose Java was simply because I am most familiar with that language

The application exposes two endpoints:

* root context i.e. http://localhost:8083
* /info endpoint i.e. http://localhost:8083/info

More about later.

## Pre-requisites

Running the project locally requires:

* Apache Maven 3.6.3
* Oracle JDK: 1.8


## How to run the project:

There are number of ways for running the project. Pre-requisite step for all of them is:

```bash
git clone https://github.com/alejandroLuchador/MyApp.git
cd MyApp
```

### Running locally:

The command below will build the code and run the application. The application should then be accessible via your favourite browser via the following URL http://localhost:8083/info

```bash
mvn spring-boot:run
```

### Running containerised application

Please note that this will require Docker Desktop to be installed.

First step is to package the application via the following command

```bash
mvnw package
```

Once completed the command will package the application into MyApp/target/MyApp-1.0.jar

The application can now be deployed to a docker container and run.


```bash
docker build -t myapp/myapp-spring-boot-docker .

docker run -p 8084:8083 -t "myapp/myapp-spring-boot-docker"
```

The application should then be accessible via your favourite browser via the following URL http://localhost:8084/info
