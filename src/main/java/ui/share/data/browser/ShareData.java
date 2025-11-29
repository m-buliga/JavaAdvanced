package ui.share.data.browser;

import org.openqa.selenium.WebDriver;


public class ShareData {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void prepareBrowser() {
        driver = new BrowserFactory().getBrowserFactory();
        //LoggerUtilityUi.infoLog("The browser was successfully opened.");
    }

    public void clearBrowser() {
        driver.quit();
        //LoggerUtilityUi.infoLog("The browser was successfully closed.");
    }
}
