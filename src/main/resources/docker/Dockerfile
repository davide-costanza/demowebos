# Docker image that contains a Spring Boot application.
#
#FROM anapsix/alpine-java:8_jdk_unlimited
FROM davide/devjava:1.0 
 
# Absolute path to the WAR file to be launched when a Docker container is started (variabile d'ambiente usata dallo script di init).
ENV JAR_PATH=/application/lib/demowebos-webapp.war
 
# Create directory to hold the application and all its contents in the Docker image.
RUN mkdir /application

# Copy all the static contents to be included in the Docker image.
COPY ./application/ /application/

# Make all scripts in the bin directory executable. Includes start-script.
RUN chmod +x /application/bin/*.sh
 
# Web port.
EXPOSE 8080
 
#CMD [ "/application/bin/start-app.sh" ]
ENTRYPOINT [ "/application/bin/start-app.sh" ]
