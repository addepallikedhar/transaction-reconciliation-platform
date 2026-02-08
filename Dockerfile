FROM eclipse-temurin:17-jre-alpine
LABEL authors="Kedhar Addepalli"

WORKDIR /app

ARG JAR_FILE=target/reconciliation-platform.jar
COPY ${JAR_FILE} app.jar

ENV JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
