
# Simple User Registration and login Application with Spring Boot, Mysql, JPA and Hibernate 

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/devnitk/TestAuthService.git
```

**2. Create Mysql database**
```bash
create database user_db;
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.yml`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/SumoLogicAuthService-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

You can use Swagger link to access the REST API's <http://localhost:8080/swagger-ui/index.html#/>

Screenshots:- 

Swagger Home Page:-

![Screenshot](/TestAuthService/screenshots/MainPage.png)

===================

 User Register Success:-
 
![Screenshot](/TestAuthService/screenshots/signup-UserRegister.png)

 User Register MalformedRequest:-
 
![Screenshot](/TestAuthService/screenshots/signup-malformeduserRegister.png)


 User Register Duplicate Email scenario:-
 
![Screenshot](/TestAuthService/screenshots/signup-duplicateemail.png)

User Register with invalid email:-
 
![Screenshot](/TestAuthService/screenshots/signup-invalidemail.png)

==========================

User Login Success:- 

![Screenshot](/TestAuthService/screenshots/loginsuccess.png)


User Login With incorrect password:- 

![Screenshot](/TestAuthService/screenshots/incorrectpassword.png)

User Login With invalid user:- 

![Screenshot](/TestAuthService/screenshots/login-usernotfound.png)

