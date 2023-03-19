#stage 1
#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

#stage 2
#Same Java runtime
FROM openjdk:17-jdk-slim
WORKDIR application

COPY --from=build dependencies/ ./
RUN true
COPY --from=build spring-boot-loader/ ./
RUN true
COPY --from=build snapshot-dependencies/ ./
RUN true
COPY --from=build application/ ./

#execute the application
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]