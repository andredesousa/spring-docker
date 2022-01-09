# Spring Boot with Docker

[Docker](https://www.docker.com/) is a Linux container management toolkit with a “social” aspect, letting users publish container images and consume those published by others.
This project walks you through the process of building a Docker image for running a Spring Boot application using a basic `Dockerfile`.

## Overview

In the world of cloud-native applications, Docker and containerization is a must.
Jar (or war) file as a final artifact is no longer an option.
To build execute all tasks of this project, you need Docker installed, which only runs on 64-bit machines.

A Spring Boot application is easy to convert into an executable JAR file. With Gradle, you run `./gradlew build`.
A basic Dockerfile to run that JAR would then look like this, at the top level of your project:

```dockerfile
FROM openjdk:11-jre-slim

COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
```

This Dockerfile is very simple, but it is all you need to run a Spring Boot app: just Java and a JAR file.
The `docker build` command builds Docker images from a Dockerfile and a "context":

```bash
docker build -t spring-docker .
```

After running the last command, you can run a container from the Docker image:

```bash
docker run -d -p 8080:8080 spring-docker
```

Here, `-d` means detach mode, and `-p 8080:8080` means external port is 8080 is mapped with internal port 8080.

Alternatively, you can build a tagged docker image with Gradle using `./gradlew buildDockerImage` task.

Spring Boot supports buildpacks.
Put simply, instead of creating our own Dockerfile and building it using something like docker build, all you need is the `./gradlew bootBuildImage` task.
Alternatively, the [Palantir Gradle Plugin](https://github.com/palantir/gradle-docker) works with a Dockerfile and can also generate a Dockerfile for you.
Using [Google Jib](https://github.com/GoogleContainerTools/jib) makes preparing and pushing Docker image pretty easy.
Jib builds optimized Docker and OCI images for your Java applications without a Docker daemon, and without deep mastery of Docker best practices.

## Project structure

This project was generated with [Spring Initializr](https://start.spring.io/).
All of the app's code goes in a folder named `src/main`.
The **unit tests** and **integration tests** are in the `src/test` and `src/integrationTest` folders.
Static files are placed in `src/main/resources` folder.

## Available gradle tasks

The tasks in [build.gradle](build.gradle) file were built with simplicity in mind to automate as much repetitive tasks as possible and help developers focus on what really matters.

The next tasks should be executed in a console inside the root directory:

- `./gradlew tasks` - Displays the tasks runnable from root project 'app'.
- `./gradlew bootRun` - Runs this project as a Spring Boot application.
- `./gradlew check` - Runs all checks.
- `./gradlew test` - Runs the unit tests.
- `./gradlew integrationTest` - Run the integration tests.
- `./gradlew clean` - Deletes the build directory.
- `./gradlew buildDockerImage` - Builds a Docker image of the application.
- `./gradlew help` - Displays a help message.

For more details, read the [Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html) documentation in the [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html).

## Running unit tests

Unit tests are responsible for testing of individual methods or classes by supplying input and making sure the output is as expected.

Use `./gradlew test` to execute the unit tests via [JUnit 5](https://junit.org/junit5/), [Mockito](https://site.mockito.org/) and [AssertJ](https://assertj.github.io/doc/).
Use `./gradlew test -t` to keep executing unit tests in real time while watching for file changes in the background.
You can see the HTML report opening the [index.html](build/reports/tests/test/index.html) file in your web browser.

It's a common requirement to run subsets of a test suite, such as when you're fixing a bug or developing a new test case.
Gradle provides different mechanisms.
For example, the following command lines run either all or exactly one of the tests in the `SomeTestClass` test case:

```bash
./gradlew test --tests SomeTestClass
```

For more details, you can see the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation.

This project uses [JaCoCo](https://www.eclemma.org/jacoco/) which provides code coverage metrics for Java.
The minimum code coverage is set to 80%.
You can see the HTML coverage report opening the [index.html](build/reports/jacoco/test/html/index.html) file in your web browser.

## Running integration tests

Integration tests determine if independently developed units of software work correctly when they are connected to each other.

Use `./gradlew integrationTest` to execute the integration tests via [JUnit 5](https://junit.org/junit5/), [Mockito](https://site.mockito.org/) and [AssertJ](https://assertj.github.io/doc/).
Use `./gradlew integrationTest -t` to keep executing your tests while watching for file changes in the background.
You can see the HTML report opening the [index.html](build/reports/tests/integrationTest/index.html) file in your web browser.

Like unit tests, you can also run subsets of a test suite.
See the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation.

## Debugging

You can debug the source code, add breakpoints, inspect variables and view the application's call stack.
Also, you can use the IDE for debugging the source code, unit and integration tests.
You can customize the [log verbosity](https://docs.gradle.org/current/userguide/logging.html#logging) of gradle tasks using the `-i` or `--info` flag.

This project includes [Swagger](https://swagger.io/). To get a visual representation of the interface and send requests for testing purposes go to <http://localhost:8080/swagger-ui.html>.

## Reference Documentation

For further reference, please consider the following articles:

- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.5/gradle-plugin/reference/html/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Spring Boot Docker](https://spring.io/guides/topicals/spring-boot-docker/)
- [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
- [Testing with Spring Boot and @SpringBootTest](https://reflectoring.io/spring-boot-test/)
