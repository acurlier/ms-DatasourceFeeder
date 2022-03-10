FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /usr/app

COPY . . 

RUN mvn clean install

FROM openjdk:17

WORKDIR /usr/app

COPY --from=builder /usr/app/target/http-parser-1.0-SNAPSHOT-jar-with-dependencies.jar .

CMD ["java", "-jar", "http-parser-1.0-SNAPSHOT-jar-with-dependencies.jar"]