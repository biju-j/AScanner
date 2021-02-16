FROM maven:3.6.3-jdk-8

COPY src /Users/bijuj/AllScan/src

COPY pom.xml /Users/bijuj/AllScan/pom.xml

COPY testng.xml /Users/bijuj/AllScan/

RUN mvn -f /Users/bijuj/AllScan/pom.xml clean

ENTRYPOINT mvn test
