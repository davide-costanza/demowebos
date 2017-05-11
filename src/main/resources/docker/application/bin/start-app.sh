#! /bin/sh
java -jar -Dspring.profiles.active=dockerdev ${JAR_PATH}

# TODO: Provare per ridurre il tempo di startup di Tomcat. La variabile JAVA_OPTS va inizializzata a "" mediante ENV nel Dockerfile 
# java ${JAVA_OPTS} -Dspring.profiles.active=dockerdev -Djava.security.egd=file:/dev/./urandom -jar ${JAR_PATH}

# TODO: DEBUG. Si sfrutta la variabile JAVA_OPTS introdotta nel passo precedente 
# $ docker run -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8080:8080 -p 5005:5005 -t demowebos-web:latest

# TODO: Provare a usare insieme i flag -d -t per creare un container detached ma con stdout pienamente accessibile mediante "docker logs <container>" 
