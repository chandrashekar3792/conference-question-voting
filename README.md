# Conference vote over a question 

<h4 align="center">An application that will let participants at a conference vote over a question given by a speaker</h4>

## Technologies

- ### Back end

  - [Spring Boot](https://spring.io/projects/spring-boot/)- REST API using JAVA Spring Boot Framework
  - [MySQL](http://mysql.com/)- SQL database Storing Data
  - [Maven](http://maven.apache.org/)-- Maven as the build system


- ### Front end

  - [React](https://reactjs.org/) - JavaScript library for building user interfaces.
  - [React-router](https://github.com/ReactTraining/react-router)- Complete routing library for React
  - [npm](https://www.npmjs.com/) -Building the frontend Application 
 

 ## Building the Project

 ### Backend

 ```
 cd backend
 mvn clean install
 java -jar target/conference-voting-0.0.1-SNAPSHOT.jar
 ```
 OR

 Use Docker to run
 ```
 cd backend
 docker build -t backend . && docker run -it backend
 ```

 This uses the port 8080.
 To run with MySQL, use the "prod" Spring Boot profile

 ### Frontend

```
cd frontend
npm install
npm start
```

OR

Use Docker to run
```
 cd frontend
 docker build -t frontend . && docker run -it frontend
```
This uses the port `3000`. So navigate to http://localhost:3000.

### Web pages
<img src="results/Register.png" alt="Register">
<img src="results/listQuestion.png" alt="List Questions">
<img src="results/AddQuestion.png" alt="Add Question">
<img src="results/VoteQuestion.png" alt="Vote Question">

## Running Tests

Use the below Maven command to run test cases -

```bash
mvn clean test
```

## API Documentation

Swagger API Documentation. Open http://localhost:8080/swagger-ui.html from the browser.
Example:-
<img src="results/APIDoc.png" alt="API DOc">
