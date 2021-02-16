import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityPgp {

    private WebDriver driver;

    private String pgpUrl = "https://www.kraken.com/en-us/features/security/pgp";
    private By allLinks = By.tagName("a");
    private String href = "href";

    public SecurityPgp(WebDriver driver){
        this.driver = driver;
    }

    public String pgpPageTitle(){
        driver.navigate().to(pgpUrl);
        return driver.getTitle();
    }

    public List<WebElement> fetchLinks() throws Exception{
        driver.get(pgpUrl);
        return driver.findElements(allLinks).stream()
                .filter(element -> element.getAttribute(href) != null)
                .collect(Collectors.toList());
    }
}