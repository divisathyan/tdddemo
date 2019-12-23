# tdddemo
Demo app built using TDD approach
- Simple web server which will expose REST API on port 8080 (default)
- Swagger is implemented

The app is Dockerized. Please follow the steps to create and run the Docker image
1)Download the code from Github repository
2)Open Terminal/Command prompt/Bash in the folder having Dockerfile
3)Build the project jar using Maven
  mvn clean package
3)Run the docker build command
  docker build -t divya/t-tdddemo .
4)Run the docker image with port
  docker run -p 8080:8080 divya/t-tdddemo
5)Check the application is running by accessing REST API
  http://localhost:8080/swagger-ui.html
