FROM eclipse-temurin:17-jre-alpine@sha256:e7b28b772a5aaa0dc759c540ffcf1670765c1ae0245d41d9995db8a3a6115bd7
WORKDIR /app
COPY ./build/libs/product-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","app.jar"]