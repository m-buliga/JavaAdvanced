package ui.share.data.browser.service;

import core.utils.xml.xmlFile.xmlNode.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirefoxBrowserService implements BrowserService {
    private WebDriver driver;

    @Override
    public void openBrowser(DriverConfig driverConfig) {
        FirefoxOptions options = (FirefoxOptions) getBrowserOptions(driverConfig);
        driver = new FirefoxDriver(options);
        driver.get(driverConfig.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Override
    public Object getBrowserOptions(DriverConfig driverConfig) {
        FirefoxOptions options = new FirefoxOptions();

        if (!driverConfig.headless.isEmpty()) {
            options.addArguments(driverConfig.headless);
        }
        options.addArguments(driverConfig.resolution);
        options.addArguments(driverConfig.gpu);
        options.addArguments(driverConfig.extensions);
        return options;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
