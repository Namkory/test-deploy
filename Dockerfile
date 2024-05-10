FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x ./mvnw

# Converting the mvnw line ending during build (if you don't change line endings of the mvnw file)
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix ./mvnw

RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]