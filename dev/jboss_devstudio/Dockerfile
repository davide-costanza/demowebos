# Docker image that contains JBoss Developer Studio.

FROM davide/devjboss:1.0

# MAINTAINER e' deprecato
LABEL maintainer "Davide Costanza davide.costanza@extrays.it"

RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.03.1-ce.tgz && \
    tar --strip-components=1 -xvzf docker-17.03.1-ce.tgz -C /usr/local/bin && rm docker-17.03.1-ce.tgz

ADD docker_user.sh /

# In questo modo l'utente nomachine acquisisce lo stesso gruppo dell'utente host (nx).
# Al contrario, l'utente host deve entrare nel gruppo di nomachine (operazione di una volta sola):
# $ sudo groupadd -g 433 nomachine
# $ sudo usermod -a -G nomachine davide
# Inoltre, la directory dei sorgenti sulla macchina host deve essere condivisa a livello di gruppo:
# $ chmod -R g+w /home/.../demowebos  (chmod -R g+w .)
RUN usermod -a -G nx nomachine

#RUN chown -R nomachine:nx /home/nomachine/.m2 && chown -R nomachine:nx /home/nomachine/devstudio_workspace

# NON usare!
#VOLUME /var/run/docker.sock

RUN chmod +x docker_user.sh
ENTRYPOINT [ "/docker_user.sh" ]

