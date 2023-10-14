# Getting Started

The project needs a environment variable named `ENVIRONMENT` with these values `{'dev', 'qa', 'prod'}` depending on the type of environment to deploy the project.

- Alternatively, you can run the following command to deploy the jar app.
```shell
$ java -DENVIRONMENT=dev -jar target/api-demo.jar
```

## Docker:
### Backend Image: RedHat 9

- See details in the script `Docker/docker_rhel9-openjdk17.sh`

```bash
$ docker pull registry.access.redhat.com/ubi9/ubi-micro:9.2-15.1696515526
```


### Database Image: Oracle 18.4

- See details in the script `Docker/docker_oracle-db.sh`

```bash
$ docker pull container-registry.oracle.com/database/express:18.4.0-xe
```

### Trivy: Docker image verification

- Run this script to verify a docker image:

```bash
$ docker run -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image registry.access.redhat.com/ubi9/ubi-micro:9.2-15.1696515526
```


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.16/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.16/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.16/reference/htmlsingle/index.html#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.16/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

