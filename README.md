# tdddemo
Demo app built using TDD approach
- Simple web server which will expose REST API on port 8080 (default)
- Swagger is implemented

The app is Dockerized. Please follow the steps to create and run the Docker image on your system:
- Assumptions:
  Maven is set up on your system and you are able to run mvn commands from IDE/Terminal
  Docker is set up on your system and running
  You have an IDE where you could load the project. This is not necessary

1) Download the code from Github repository. This will be referred to as <PRJ_HOME>

2) Open Terminal/Command prompt/Bash in the folder having Dockerfile <PRJ_HOME>

3) Build the project jar using Maven
  
  mvn clean package
  
  Result: This should successfully build the project and create the final jar in the <PRJ_HOME>/target folder
  
3) Run the docker build command in the Terminal
  
  docker build -t divya/t-tdddemo .
  
  Result: Run the command 'docker images' and it should display the image by name 'divya/t-tdddemo'
  
4) Run the docker image with port (You can modify the port if necessary)
  
  docker run -p 8080:8080 divya/t-tdddemo
  
  Result: Message that the application has started will be displayed
  
5) Check the application is running by accessing REST API
  
  http://localhost:8080/swagger-ui.html
