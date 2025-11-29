package ui.share.data.browser.service;

import core.utils.xml.xmlFile.xmlNode.DriverConfig;

public interface BrowserService {
    void openBrowser(DriverConfig driverConfigNode);
    Object getBrowserOptions(DriverConfig driverConfigNode);

}
