FROM tomcat:9.0-alpine
ADD /target/etutor.war /home/etutor.jar
EXPOSE 8080
CMD ["catalina.sh", "run"]