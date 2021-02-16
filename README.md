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
2. Check every pom.xml testng.xml Dockerfile and docker-compose.xml is copied
3. The Tests can be run in
   a. Standalone mode
   b. Grid based
4. Reports will be in `/test-output/html`
5. Firefox with Selenium 4 has issues capturing console errors, a known issue yet to be fixed by Mozilla. 
   Thus, `/reports/FFlog.log`