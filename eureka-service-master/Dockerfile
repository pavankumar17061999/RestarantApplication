
#FROM adoptopenjdk/openjdk21:jdk-21.0.3.0-slim
#WORKDIR /opt
#COPY target/*.jar /opt/app.jar
#ENTRYPOINT exec java $JAVA_OPTS -jar app.jar

FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt
COPY target/*.jar /opt/app.jar

