FROM openjdk:11-jdk-slim
MAINTAINER Luis Molina <molicode1347@gmail.com>
ARG JAR_FILE=target/challenge-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

# Builder: 
# docker build -t challenge-meli .
# Execute: 
# docker run -p 8080:8080  challenge-meli -d

# Directivas de docker:
#  FROM: Crea una capa (stage) base a partir de una imagen docker existente
#  VOLUME: Crea un mecanismo para persistir datos
#  EXPOSE: Indica al contenedor que puerto exponer
#  ARG: Define una variable que puede ser pasada en tiempo de construcción
#  ADD: Agrega contenido al sistema de archivos del contenedor que se va a crear
#  ENTRYPOINT: Configura lo que se ejecutará dentro del contenedor