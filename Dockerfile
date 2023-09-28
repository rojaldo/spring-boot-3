FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
# copy jar file demo-0.0.1-SNAPSHOT.jar from target folder to app folder
COPY --from=build /workspace/app/target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar
# run the application with jar
ENTRYPOINT ["java", "-jar" , "/app/demo-0.0.1-SNAPSHOT.jar"]