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

In this project we will also add the use of the DTO pattern 
for data persistence.

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

## 📚 Additional Resources
- [Badges](https://github.com/alexandresanlim/Badges4-README.md-Profile?tab=readme-ov-file#-frameworks--library-)
- [Spring-MYSQL](https://spring.io/guides/gs/accessing-data-mysql)
- [Springboot](https://dev.to/abhi9720/a-beginners-guide-to-crud-operations-of-rest-api-in-spring-boot-mysql-5hcl)
- [Postman](https://learning.postman.com/docs/getting-started/first-steps/sending-the-first-request/)
- [JPA](https://www.arquitecturajava.com/spring-boot-jpa-y-su-configuracion/)
- [Hibernate](https://www.baeldung.com/spring-boot-hibernate)
- [HTTP](https://www.restapitutorial.com/httpstatuscodes)

[Back to top](#top)