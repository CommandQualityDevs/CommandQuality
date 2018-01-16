package za.co.command_quality.tests.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import za.co.command_quality.DriverBase;
import za.co.command_quality.page_objects.google.GoogleHomePage;

public class GoogleHomePageTest extends DriverBase {
    private String baseUrl = "http://www.google.com";

    @Test(testName = "Google Cheese Search",
            description = "Search cheese then check if any title that start" +
                    " with cheesed title it is found")
    public void googleCheeseExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit Google
        driver.get(baseUrl);
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        GoogleHomePage googleHomePage = new GoogleHomePage();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        googleHomePage.enterSearchTerm("Cheese")
                .submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 40)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheesed");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test(testName = "Google Milk Search")
    public void googleMilkExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit Google
        driver.get(baseUrl);
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        GoogleHomePage googleHomePage = new GoogleHomePage();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        googleHomePage.enterSearchTerm("Milk")
                .submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("milk");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
    }
}