FROM arm64v8/eclipse-temurin:22-jdk-alpine as base
WORKDIR /usr/local/app

FROM base as backend
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/fitness_tracker-0.0.1-SNAPSHOT.jar
COPY build/libs/fitness_tracker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]