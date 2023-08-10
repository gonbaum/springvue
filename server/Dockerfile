FROM gradle:jdk17 as builder

# COPY --chown=gradle:gradle . /home/gradle/src

# WORKDIR /home/gradle/src

# RUN gradle clean build -x test
WORKDIR /app
COPY . .
RUN ./gradlew clean build

FROM openjdk:17.0.1-jdk-slim as finalApp

RUN mkdir /app/
WORKDIR /app
COPY --from=builder /app/build/libs/main.java.com.getyourguide-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/main.java.com.getyourguide-0.0.1-SNAPSHOT.jar"]