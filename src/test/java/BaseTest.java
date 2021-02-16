/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;*/
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseTest{
    private WebDriver driver;
    /*public ExtentTest test;
    ExtentReports reports;
    ExtentSparkReporter spark;*/

    @Parameters("browser")
    @BeforeClass
    public void before(String browser){


        // setupDrivers(browser);

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName(BrowserType.CHROME);
            driver = new ChromeDriver();

        }
        else if(browser.equalsIgnoreCase("firefox")) {
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./logs/FFLog.log");
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            driver = new FirefoxDriver();
        }
    }

    public void setupDrivers(String browser) throws MalformedURLException {

        if(browser.equalsIgnoreCase("chrome"))
        {   DesiredCapabilities ccaps = new DesiredCapabilities();
            ccaps.setBrowserName(BrowserType.CHROME);
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), ccaps);
            driver.manage().window().maximize();
        }
        else if(browser.equalsIgnoreCase("firefox")){
            DesiredCapabilities ffCaps = new DesiredCapabilities();
            ffCaps.setBrowserName(BrowserType.FIREFOX);
            driver = new RemoteWebDriver(new URL("http:localhost:4445/wd/hub"), ffCaps);
            driver.manage().window().maximize();
        }
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public int checkLink(String link) throws Exception{
        return given().when().get(new URL(link)).statusCode();
    }

    public List<LogEntry> fetchConsoleErrors() throws Exception {
        if(this.driver instanceof RemoteWebDriver ) {
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
            Thread.sleep(3000);
            String err = (String)((JavascriptExecutor)driver).executeScript("return JSON.stringify(window.myError);");
            return Collections.emptyList();
        }
        else {
            LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
            return (logEntries.getAll()!=null)?logEntries.getAll():Collections.emptyList();
        }
    }

    /*public ExtentTest setupReporting(String reportName) {
        spark = new ExtentSparkReporter("./reports/"+reportName+"Report.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("All Scans");
        spark.config().setReportName("Scan report");
        reports = new ExtentReports();
        reports.attachReporter(spark);
        return reports.createTest(reportName);
    }*/
}