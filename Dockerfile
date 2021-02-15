FROM maven:3.6.0-jdk-8-alpine

COPY src /Users/bijuj/AllScan/src

COPY pom.xml /Users/bijuj/AllScan/

COPY testng.xml /Users/bijuj/AllScan/

RUN mvn -f /Users/bijuj/AllScan/pom.xml clean test -DskipTests=true

