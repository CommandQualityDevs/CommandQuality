package za.co.command_quality.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import za.co.command_quality.util.Query;


public class DriverFactory {

    private RemoteWebDriver webdriver;
    private DriverType selectedDriverType;

    private final DriverType defaultDriverType = DriverType.FIREFOX;
    private final String browser = System.getProperty("browser", defaultDriverType.toString()).toUpperCase();

    public WebDriver getDriver() throws Exception {
        if (null == webdriver) {
            determineEffectiveDriverType();
            MutableCapabilities mutableCapabilities = selectedDriverType.getDesiredCapabilities(null);
            instantiateWebDriver(mutableCapabilities);
            Query.initQueryObjects(webdriver);
        }
        return webdriver;
    }

    public void quitDriver() {
        if (null != webdriver) {
            webdriver.quit();
        }
    }

    private void determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    private void instantiateWebDriver(MutableCapabilities mutableCapabilities) {
        webdriver = selectedDriverType.getWebDriverObject(mutableCapabilities);
    }
}

