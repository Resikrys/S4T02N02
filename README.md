# S4.02 - API REST with Springboot Framework & MYSQL

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)
![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![MYSQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker%20Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

-----

## Summary
In this task you will do a CRUD (Create, Read, Update, Delete)
with 3 different databases.
You will learn how to correctly use HTTP verbs and how
to manage response codes.

In addition, we will implement the use of DTO to separate 
the responsibilities of the entity model (whose function is 
to represent a row in the DB's fruits table and contains 
annotations for data persistence with JPA/Hibernate) from 
those of the data transfer object (DTO, whose function is 
to transport data between the client and the server, acting 
as a security filter that prevents direct exposure of the entity).

-----

## 📄 LVL 2: Spring & MYSQL
### Key Components

* We have an **Entity** called "Fruit", which has the following
  properties:
    * int id
    * String name
    * int quantityKilos

* **MYSQL Database**
* Subpackages:
    * **Controllers**
    * **Model**
    * **Services**
    * **Repository**
    * **Exception**

-----

## 🔧 Technologies
- **Java 24**: Core programming language
- **Maven**: Build and dependency management
- **MYSQL**: Relational Database
- **Git/GitHub**: version control
- **Additional dependencies**:
    - JDBC
    - Lombok
    - Postman
    - Docker compose

-----

### 🛠️ SCRIPTS
```bash
mvn compile          # Compile project
mvn package          # Package in .jar
mvn clean            # Clean the project
mvn spring-boot:run  # Execute the app
docker-compose up -d  # Run docker container
docker-compose stop   # Stop docker container
docker-compose down -v  # Shutdown docker container
docker-compose ps # See logs container
```

### 🛠️ HTTP requests to update and consult information (POSTMAN)
```bash
URL base:
http://localhost:8080/fruits-api

(POST):
http://localhost:8080/fruits-api + raw JSON:
{
  "name": "Banana",
  "quantityKilos": 13
}

(GET):
http://localhost:8080/fruits-api/{id}
http://localhost:8080/fruits-api/getAll

(PUT):
http://localhost:8080/fruits-api/{id}

(DELETE):
http://localhost:8080/fruits-api/{id}

```
-----

### ⚙️ Practical tips to achieve maximum testing coverage

| Test type                            | What it covers     | Cost | Coverage gain  |
|--------------------------------------|--------------------|------|----------------|
| 🧪 Controller “happy paths”          | Principal flux     | Low  | +20–30 %       |
| 🚫 Controller errors (404, 409, 500) | Negative branch    | Low  | +10 %          |
| 🧱 Service (unitary testing)         | If/else conditions | Mid  | +10 – 15 %     |
| 🧰 Validations `@Valid`              | Bad request        | Low  | +5 %           |
| ⚠️ GlobalExceptionHandler            | 500s generics      | Low  | +3 %           |


## 📚 Additional Resources
- [Badges](https://github.com/alexandresanlim/Badges4-README.md-Profile?tab=readme-ov-file#-frameworks--library-)
- [Spring-MYSQL](https://spring.io/guides/gs/accessing-data-mysql)
- [Springboot](https://dev.to/abhi9720/a-beginners-guide-to-crud-operations-of-rest-api-in-spring-boot-mysql-5hcl)
- [Postman](https://learning.postman.com/docs/getting-started/first-steps/sending-the-first-request/)
- [JPA](https://www.arquitecturajava.com/spring-boot-jpa-y-su-configuracion/)
- [Hibernate](https://www.baeldung.com/spring-boot-hibernate)
- [ResponseEntity](https://www.arquitecturajava.com/responseentity-spring-y-rest/)
- [HTTP](https://www.restapitutorial.com/httpstatuscodes)
- [Conventional commits](https://www.conventionalcommits.org/en/v1.0.0/)

[Back to top](#top)