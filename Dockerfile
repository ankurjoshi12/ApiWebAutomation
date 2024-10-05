FROM maven:3.8.1-openjdk-11
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# This will run your tests when the container starts
CMD ["mvn", "test"]