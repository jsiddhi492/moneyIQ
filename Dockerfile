# ---- Build Step ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY . .
RUN ./mvnw clean package -DskipTests

# ---- Run Step ----
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/moneyIQ-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]
