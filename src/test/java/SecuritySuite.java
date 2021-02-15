
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.*;

import java.util.List;
import java.util.logging.Level;

import static org.testng.Assert.*;

/**
 * Created by bijuj on 12/02/21.
 */
public class SecuritySuite extends BaseTest {

    private SecurityPgp pgpPage;
    private WebDriver driver;
    private String attribute = "href";
    private int HTTP_OK = 200;
    private int HTTP_LIMIT = 400;
    private String pgpDesc = "Kraken | Buy, Sell and Margin";

    @BeforeMethod
    public void setup(){
        driver = getDriver();
        pgpPage = new SecurityPgp(getDriver());
    }

    @Test
    public void checkInit() throws Exception{
        assertTrue(pgpPage.pgpPageTitle().contains(pgpDesc));
    }

    @Test
    public void linksValidator() throws Exception {
        List<WebElement> allLinks = pgpPage.fetchLinks();
        for( WebElement link : allLinks ){
            String aHref = link.getAttribute(attribute);
            int status = checkLink(aHref);
            if( status != HTTP_OK && status < HTTP_LIMIT ){
                fail(link.getText()+" link broken, having "+aHref);
            }
        }
    }

    @Test
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

    @Test(enabled = false)
    public void activePagesCheck() {

    }
}