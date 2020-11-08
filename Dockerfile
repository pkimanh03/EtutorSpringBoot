FROM tomcat:9.0-alpine
ADD /target/etutor.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]

#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=/target/etutor.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]