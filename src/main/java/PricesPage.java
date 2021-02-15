import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class PricesPage {

    private String pricesUrl = "https://www.kraken.com/prices";

    private By allLinks = By.tagName("a");
    private String href = "href";
    private WebDriver driver;

    public PricesPage(WebDriver driver){
        this.driver = driver;
    }

    public String pricesPageTitle(){
        driver.get(pricesUrl);
        return driver.getTitle();
    }

    public List<WebElement> fetchLinks() throws Exception{
        driver.get(pricesUrl);
        return driver.findElements(allLinks).stream()
                .filter(element -> element.getAttribute(href) != null)
                .collect(Collectors.toList());
    }
}