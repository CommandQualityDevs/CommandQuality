package za.co.command_quality.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.HashMap;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

public enum DriverType implements DriverSetup {

    FIREFOX {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("marionette", true);
            return addProxySettings(firefoxOptions, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            // System.setProperty("webdriver.gecko.driver", "resources/drivers/geckodriver");
            return new FirefoxDriver(capabilities);
        }
    },
    CHROME {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            //System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver");
            return new ChromeDriver(capabilities);
        }
    },
    IE {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            //System.setProperty("webdriver.ie.driver", "resources/drivers/IEDriverServer.exe");
            return new InternetExplorerDriver(capabilities);
        }
    },
    EDGE {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.edge();
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            //System.setProperty("webdriver.edge.driver", "resources/drivers/MicrosoftWebDriver.exe");
            return new EdgeDriver(capabilities);
        }
    },
    OPERA {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            // System.setProperty("webdriver.opera.driver", "resources/drivers/operadriver");
            return new OperaDriver(capabilities);
        }
    },
    CHROME_HEADLESS {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            HashMap<String, String> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            capabilities.setCapability("chrome.prefs", chromePreferences);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            //System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver");
            return new ChromeDriver(capabilities);
        }
    };

    protected MutableCapabilities addProxySettings(MutableCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }

        return capabilities;
    }
}