package ui.share.data.browser;

import core.logging.LoggerUtility;
import org.openqa.selenium.WebDriver;


public class ShareData {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void prepareBrowser() {
        driver = new BrowserFactory().getBrowserFactory();
        //LoggerUtility.infoLog("The browser was successfully opened.");
    }

    public void clearBrowser() {
        driver.quit();
        //LoggerUtility.infoLog("The browser was successfully closed.");
    }
}
