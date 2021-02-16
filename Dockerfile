FROM maven:3.6.3-jdk-8

COPY src /AllScan/src

COPY pom.xml /AllScan/pom.xml

COPY testng.xml /AllScan/testng.xml

ENTRYPOINT mvn clean

