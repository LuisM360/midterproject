# Use a standard OpenJDK base image
FROM openjdk:17-jdk-slim

# Create a directory for your application
WORKDIR /app

# Copy your application JAR and its dependencies
# The 'target/MidtermProject-1.0-SNAPSHOT.jar' is your runnable JAR.
# The 'target/lib' directory contains all the dependencies.
COPY target/MidtermProject-1.0-SNAPSHOT.jar ./app.jar
COPY target/lib ./lib

# Command to run your application when the container starts
# java -jar app.jar will automatically find dependencies in the 'lib' folder
# because of the classpathPrefix in pom.xml.
CMD ["java", "-jar", "app.jar"]

# Note: No need for VNC, X servers, chmod +x, complex CMD scripts, or exposed GUI ports.
# This app will run in the container's standard output.