FROM openjdk:21-jdk-slim

# Install Node.js and npm
RUN apt-get update && apt-get install -y curl && \
    curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs

WORKDIR /app
# Copy package.json to the image
COPY package.json ./

# Install Newman and other dependencies
RUN npm install -g newman

VOLUME /tmp
COPY target/searchengine-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]