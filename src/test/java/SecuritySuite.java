
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Level;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

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

    @BeforeClass
    public void setup(){
        //test = setupReporting("SecuritySuite");
        driver = getDriver();
        pgpPage = new SecurityPgp(getDriver());
    }

    @Test
    public void checkInit() throws Exception{
        //test.pass("Init checked");
        assertTrue(pgpPage.pgpPageTitle().contains(pgpDesc));
    }

    @Test
    public void linksValidator() throws Exception {
        List<WebElement> allLinks = pgpPage.fetchLinks();
        for( WebElement link : allLinks ){
            String aHref = link.getAttribute(attribute);
            int status = checkLink(aHref);
            if( status != HTTP_OK && status > HTTP_LIMIT ){
               // test.fail("broken link found");
               fail(link.getText()+" link broken, having "+aHref);
            }
        }
        //test.pass("All links checked");
    }

    @Test
    public void consoleErrorsChecker() throws Exception {
        List<LogEntry> foundErrors = fetchConsoleErrors();
        if (foundErrors.size() > 0) {
            for (LogEntry logEntry : foundErrors) {
                if (logEntry.getLevel().equals(Level.SEVERE)) {
                     //test.fail("Console Error foundd");
                     fail("Error found: " + logEntry.getTimestamp() + " ==> " + logEntry.getMessage());
                }
            }
        }
        //test.pass("Clean, no errors found");
    }

}