The framework is devised with structure ease to understand and run tests.
Below libraries and tools are used
- Selenium 4
- restassured
- testNG
- Extent Reports(blocking few runs)
- maven 
- docker-compose with Grid setup

All tests are under src/test/
BaseTest has prerequisite driver instances(Firefox, Chrome) and initial.

HomePage, Prices and Security scans are done.

Prerequisite: JAVA_HOME with MAVEN to be setup.
1. git clone AScanner repository.
2. Checkout master branch.
   Check every pom.xml testng.xml Dockerfile and docker-compose.xml is available
3. The Tests can be run in
   a. Standalone mode
   b. Grid based
4. Reports will be in `/test-output/html`
5. Firefox with Selenium 4 has issues capturing console errors, a known issue yet to be fixed by Mozilla. 
   Thus, `/reports/FFlog.log`
6. mvn clean install test => locally runs test in both browsers
7. Another option is right-click testng.xml from the `/AppScan` and run tests
8. Standalone local setup can be disabled in BaseTest and RemoteDriver with 
   Selenium-Grid based run too can be triggered.
9. Dockerfile is provided to build the image and run
10. docker-compose.yml has the Selenium-Grid with two browsers(Chrome and firefox) exposed. 
   Can spinoff run tests and release container.
   
Note: Extent reports with good visualized Reports was also tried.
But, free version has some limitations.

Can tweak and still enhance the framework with detailed logging and covering more
functionalities depending on the timelines.   
