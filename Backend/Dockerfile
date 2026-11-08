# build the jar
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# deps first, so Docker can cache them and rebuilds are faster
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY src src
RUN ./mvnw package -DskipTests -B

# run it with just the JRE (much smaller image)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
