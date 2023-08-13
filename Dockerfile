# Utiliza una imagen base de OpenJDK para Java 11
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app

COPY build/libs/apiProxy-1.0.jar apiProxy-1.0.jar

EXPOSE 8080

CMD ["java", "-jar", "apiProxy-1.0.jar"]