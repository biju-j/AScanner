import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseTest{
    private WebDriver driver;

    @Parameters("browser")
    @BeforeSuite
    public void before(String browser){

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            driver = new ChromeDriver();

        }
        else if(browser.equalsIgnoreCase("firefox")) {
            //System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./FFLog.log");
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    @AfterSuite
    public void tearDown(){
        this.driver.close();
        this.driver.quit();
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public int checkLink(String link) throws Exception{
        return given().when().get(new URL(link)).statusCode();
    }

    public List<LogEntry> fetchConsoleErrors() throws Exception {
        if(this.driver instanceof FirefoxDriver ) {
            String script =
                    "(function() {" +
                            "var oldLog = console.error;" +
                            "window.myError = [];" +
                            "console.error = function (message) {" +
                            "window.myError.push(message);" +
                            "oldLog.apply(console, arguments);" +
                            "};" +
                            "})();" ;
            ((JavascriptExecutor)driver).executeScript(script);
            ((JavascriptExecutor)driver).executeScript("console.error('Test Error')");
            Thread.sleep(3000);
            String err = (String)((JavascriptExecutor)driver).executeScript("return JSON.stringify(window.myError);");
            return Collections.emptyList();
        }
        else {
            LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
            return logEntries.getAll();
        }
    }


}