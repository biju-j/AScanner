import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    private String baseUrl = "https://kraken.com";
    private String invalidUrl = "https://www.kraken.com/doesntexist";

    private By allLinks = By.tagName("a");
    private String href = "href";
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String homePageTitle(){
        driver.navigate().to(baseUrl);
        return driver.getTitle();
    }

    public String invalidPageTitle() {
        driver.get(invalidUrl);
        return driver.getTitle();
    }

    public List<WebElement> fetchLinks() throws Exception{
        driver.get(baseUrl);
        return driver.findElements(allLinks).stream()
                .filter(element -> element.getAttribute(href) != null)
                .collect(Collectors.toList());
    }
}
