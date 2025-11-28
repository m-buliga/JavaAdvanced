package ui.share.data.browser.service;

import core.utils.xml.xmlFile.xmlNode.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ChromeBrowserService implements BrowserService {
    private WebDriver driver;

    @Override
    public void openBrowser(DriverConfig driverConfig) {
        ChromeOptions options = (ChromeOptions) getBrowserOptions(driverConfig);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.get(driverConfig.url);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Override
    public Object getBrowserOptions(DriverConfig driverConfig) {
        ChromeOptions options = new ChromeOptions();

        if (!driverConfig.headless.isEmpty()) {
            options.addArguments(driverConfig.headless);
        }
        options.addArguments(driverConfig.resolution);
        options.addArguments(driverConfig.gpu);
        options.addArguments(driverConfig.extensions);

        if (Boolean.parseBoolean(System.getProperty("ciCd", "false"))) {
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        return options;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
