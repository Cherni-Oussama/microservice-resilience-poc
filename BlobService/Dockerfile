FROM bitnami/java:11 AS builder
LABEL authors="Oussama Cherni"
RUN apt-get update -y && apt-get install maven -y
COPY . .
RUN mvn clean package

FROM openjdk:11
COPY --from=builder /target/BlobService-0.0.1-SNAPSHOT.jar /app/BlobService-0.0.1.jar
WORKDIR /app
EXPOSE 8000
CMD ["java", "-jar", "BlobService-0.0.1.jar"]