package ui.share.data.browser;

import core.utils.xml.xmlFile.GeneralXml;
import core.utils.xml.xmlFile.xmlNode.Configuration;
import org.openqa.selenium.WebDriver;
import ui.share.data.browser.service.ChromeBrowserService;
import ui.share.data.browser.service.EdgeBrowserService;
import ui.share.data.browser.service.FirefoxBrowserService;

import java.util.Locale;

public class BrowserFactory {
    public WebDriver getBrowserFactory() {

        String ciCd = System.getProperty("ciCd");
        String browser = System.getProperty("browser").toLowerCase(Locale.ROOT);

        Configuration configuration = GeneralXml.createConfig(Configuration.class);

        if (Boolean.parseBoolean(ciCd)) {
            configuration.driverConfig.headless = "--headless";
        } else {
            browser = configuration.driverConfig.localBrowser;
        }

        switch (browser) {
            case BrowserType.BROWSER_CHROME:
                ChromeBrowserService chromeService = new ChromeBrowserService();
                chromeService.openBrowser(configuration.driverConfig);
                return chromeService.getDriver();
            case BrowserType.BROWSER_EDGE:
                EdgeBrowserService edgeService = new EdgeBrowserService();
                edgeService.openBrowser(configuration.driverConfig);
                return edgeService.getDriver();
            case BrowserType.BROWSER_FIREFOX:

                FirefoxBrowserService firefoxService = new FirefoxBrowserService();
                firefoxService.openBrowser(configuration.driverConfig);
                return firefoxService.getDriver();
        }

        return null;

    }

}

