FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
ARG RESOURCES=target/classes/textfiles/
COPY ${JAR_FILE} jros-nve.jar
COPY ${RESOURCES} src/main/resources/textfiles/
ENTRYPOINT ["java", "-jar", "/jros-nve.jar"]

# Build command: docker build -t jros-nve .
# Run command:   docker run -d -p 8080:8080 jros-nve