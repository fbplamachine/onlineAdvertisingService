# onlineAdvertisingService
Implement a REST service for managing listings for online advertising service.
## Requirement
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [PostgresSql](https://www.postgresql.org/download)

## Run the application locally

For running the application locally, open the `main` application file in the ```com.fbplamachine.onlineAdvertisingService.OnlineAdvertisingServiceApplication```, and run it as Java Application.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Then access from your browser to [localhost:8082/dealer](localhost:8082/dealer) for dealer management, [localhost:8082/listing](localhost:8082/listing) for listing management and [localhost:8082/listing/manage/{listingId}](localhost:8082/listing/manage/{listingId}) for listing state management

## API Documentation
To access the documentation of the project, after starting, go to this link : [localhost:8080/swagger-ui.html](localhost:8080/swagger-ui.html) or [localhost:8080/v3/api-docs](localhost:8080/v3/api-docs)

## Application features
- **Listing**
    - add listing
    - get listings
    - detail listing
    - delete listing
    - update listing
    - Publish a listing
    - Unpublished a listing
- **Car Dealer**
    - add dealer
    - get dealers
    - detail dealer
    - delete dealer
    - update dealer
- **Tier Limit**

## Database Schema
<img alt="online-advertising-service-schema-database" src="https://github.com/fbplamachine/onlineAdvertisingService/blob/main/docs/database/online-advertising-service-schema-database.png?raw=true">



## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
