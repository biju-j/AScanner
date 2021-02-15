
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Level;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

/**
 * Created by bijuj on 12/02/21.
 */
public class PriceSuite extends BaseTest {

    private PricesPage pricesPage;
    private WebDriver driver;
    private String attribute = "href";
    private int HTTP_OK = 200;
    private int HTTP_LIMIT = 400;
    private String cryptoPrices = "Cryptocurrency Prices";

    @BeforeMethod
    public void setup(){
        driver = getDriver();
        pricesPage = new PricesPage(getDriver());
    }

    @Test
    public void checkInit() throws Exception{
        assertTrue(pricesPage.pricesPageTitle().contains(cryptoPrices));
    }

    @Test
    public void linksValidator() throws Exception {
        List<WebElement> allLinks = pricesPage.fetchLinks();
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