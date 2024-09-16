# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.9-eclipse-temurin-21-alpine AS build

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn clean package -DskipTests

# Use AdoptOpenJDK for base image.
# It's important to use OpenJDK 8u191 or above that has container support enabled.
# https://hub.docker.com/r/adoptopenjdk/openjdk8
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM eclipse-temurin:21-jre-alpine

# Copy the jar to the production image from the builder stage.
COPY --from=build /app/target/api*.jar /api.jar

# Run the web service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom",
"-Dserver.port=${PORT}","-jar","/.jar"]
