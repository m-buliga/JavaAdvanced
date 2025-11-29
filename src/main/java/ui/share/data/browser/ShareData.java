package ui.share.data.browser;

import core.logging.LoggerUtilityUi;
import core.utils.xml.xmlFile.GeneralXml;
import core.utils.xml.xmlFile.xmlNode.Configuration;
import org.openqa.selenium.WebDriver;


public class ShareData {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void prepareBrowser() {
        driver = new BrowserFactory().getBrowserFactory();

        Configuration config = GeneralXml.createConfig(Configuration.class);
        String url = config.driverConfig.url;
        driver.get(url);
        LoggerUtilityUi.infoLog("Browser opened and navigated to: " + url);
    }

    public void clearBrowser() {
        driver.quit();
        LoggerUtilityUi.infoLog("The browser was successfully closed.");
    }
}
