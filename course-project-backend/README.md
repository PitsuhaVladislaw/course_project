### requirements:

+ jdk 11 (or higher)
+ maven 3.8.6 (or higher)

How to create database schema and run a local app, run following commands:

* mvn clean compile -U
* mvn spring-boot:run

How to (target folder):

* build - mvn clean package -Pdev
* run local - java -jar <jarname>.jar

Api Documentation (Swagger)

* http://localhost:8080/api/swagger-ui/index.html (local host)
* http://host/context-path/api/swagger-ui/index.html
