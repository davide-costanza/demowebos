#!/bin/sh

if [ "$DEBUG" = true ]; then
  printf "Running the application in debug mode\n"
  #JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"
  JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"
fi

# Enables application to take PID 1 and receive SIGTERM sent by Docker stop command.
# See here: https://docs.docker.com/engine/reference/builder/#/entrypoint
# L'opzione "java.security.egd" serve per ridurre il tempo di startup di Tomcat.
exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar $JAR_PATH
