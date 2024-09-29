FROM maven:3-amazoncorretto-17-alpine AS build

WORKDIR /app

COPY pom.xml .

RUN ["mvn", "dependency:go-offline"]

COPY src ./src

RUN ["mvn", "clean", "package", "-DskipTests"]

FROM amazoncorretto:17-alpine3.20-jdk AS runtime

WORKDIR /app

RUN apk add --no-cache curl

COPY --from=build /app/target/genius-finance-0.0.1-SNAPSHOT.jar /app/genius-finance-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/genius-finance-0.0.1-SNAPSHOT.jar"]
