package ui.share.data.browser.service;

import core.utils.xml.xmlFile.xmlNode.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class EdgeBrowserService implements BrowserService {
    private WebDriver driver;

    @Override
    public void openBrowser(DriverConfig driverConfig) {
        EdgeOptions options = (EdgeOptions) getBrowserOptions(driverConfig);
        driver = new EdgeDriver(options);
        driver.get(driverConfig.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Override
    public Object getBrowserOptions(DriverConfig driverConfig) {
        EdgeOptions options = new EdgeOptions();

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
