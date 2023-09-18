FROM gradle:8.3.0-jdk20-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:20
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/quoteapi-0.0.1-SNAPSHOT.jar /app/quoteapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/quoteapi-0.0.1-SNAPSHOT.jar"]