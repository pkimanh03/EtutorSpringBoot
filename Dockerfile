FROM tomcat:9.0-alpine
ADD /target/etutor.jar /home/etutor.jar
EXPOSE 8080
CMD ["catalina.sh", "run"]