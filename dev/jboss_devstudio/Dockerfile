# Docker image that contains JBoss Developer Studio.

FROM davide/devjboss:1.0

# MAINTAINER e' deprecato
LABEL maintainer "Davide Costanza davide.costanza@extrays.it"

#ARG usergid

# In questo modo l'utente nomachine acquisisce lo stesso gruppo dell'utente host (nx).
# Al contrario, l'utente host deve entrare nel gruppo di nomachine (operazione di una volta sola):
# $ sudo groupadd -g 433 nomachine
# $ sudo usermod -a -G nomachine davide
# Inoltre, la directory dei sorgenti sulla macchina host deve essere condivisa a livello di gruppo:
# $ chmod -R g+w /home/.../demowebos  (chmod -R g+w .)
RUN usermod -a -G nx nomachine
