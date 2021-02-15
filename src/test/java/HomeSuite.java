
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.*;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

/**
 * Created by bijuj on 12/02/21.
 */
public class HomeSuite extends BaseTest {

    private HomePage home;
    private WebDriver driver;
    private String attribute = "href";
    private int HTTP_OK = 200;
    private int HTTP_LIMIT = 400;
    private String exchange = "Bitcoin & Cryptocurrency Exchange";
    private String pageNotFoundErr = "404 - Page Not Found";

    @BeforeMethod
    public void setup(){
        driver = getDriver();
        home = new HomePage(getDriver());
    }

    @Test(enabled = false)
    public void checkInit() throws Exception{
        assertTrue(home.homePageTitle().contains(exchange));
    }

    @Test(enabled = false)
    public void linksValidator() throws Exception {
        List<WebElement> allLinks = home.fetchLinks();
         for( WebElement link : allLinks ){
                String aHref = link.getAttribute(attribute);
                int status = checkLink(aHref);
                if( status != HTTP_OK && status < HTTP_LIMIT ){
                    fail(link.getText()+" link broken, having "+aHref);
                }
         }
    }

    @Test(enabled = false)
    public void consoleErrorsChecker() throws Exception {
        List<LogEntry> foundErrors = fetchConsoleErrors();
        if(foundErrors.size() > 0) {
            for (LogEntry logEntry : foundErrors) {
                if (logEntry.getLevel().equals(Level.SEVERE)) {
                    fail("Error found: "+logEntry.getTimestamp() + " ==> " + logEntry.getMessage());
                }
            }
        }
    }

    @Test
    public void invalidPageCheck() {
        assertTrue(home.invalidPageTitle().contains(pageNotFoundErr));
    }
}