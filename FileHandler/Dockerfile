FROM bitnami/java:11 AS builder
LABEL authors="Oussama Cherni"
RUN apt-get update -y && apt-get install maven -y
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11
COPY --from=builder /target/FileHandler-0.0.1-SNAPSHOT.jar /app/FileHandler-0.0.1.jar
WORKDIR /app
EXPOSE 8000
CMD ["java", "-jar", "FileHandler-0.0.1.jar"]