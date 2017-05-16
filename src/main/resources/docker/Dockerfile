# Docker image that contains a Spring Boot application.
#
#FROM anapsix/alpine-java:8_jdk_unlimited
FROM davide/devjava:1.0

# MAINTAINER e' deprecato
LABEL maintainer "Davide Costanza davide.costanza@extrays.it"

# Create directory to hold the application and all its contents in the Docker image.
RUN mkdir /application

# Copy all the static contents to be included in the Docker image.
COPY ./application/ /application/

# Make all scripts in the bin directory executable. Includes start-script.
RUN chmod +x /application/bin/*.sh

# Absolute path to the WAR file to be launched when a Docker container is started (variabile d'ambiente usata dallo script di init).
# Il nome del WAR e' definito nel pom.xml.
ENV JAR_PATH="/application/lib/demowebos-webapp.war"

ENV JAVA_OPTS="" DEBUG=false DEBUG_PORT=8000 SPRING_PROFILES_ACTIVE=dockerdev

# Web port (vedi anche application.yml).
EXPOSE 8080

#CMD [ "/application/bin/start-app.sh" ]
ENTRYPOINT [ "/application/bin/entrypoint.sh" ]
