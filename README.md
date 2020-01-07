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

The application should then be accessible via your favourite browser via the following URL http://localhost:8084/info and you should see json response similar to 

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
