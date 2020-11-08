FROM tomcat:9.0-alpine
ADD /target/etutor.jar Library/Tomcat/apache-tomcat-7.0.106/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]