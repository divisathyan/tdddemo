FROM openjdk:8

ADD target/d-tdddemo.jar /tmp/d-tdddemo.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/tmp/d-tdddemo.jar"]
