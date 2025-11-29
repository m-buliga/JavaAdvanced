package ui.pages;

import helpers.ElementsMethods;
import helpers.JavaScriptHelperMethods;
import org.openqa.selenium.WebDriver;;
import org.openqa.selenium.support.PageFactory;


public class CommonPage {

    protected WebDriver driver;
    protected ElementsMethods elementsMethods;
    protected JavaScriptHelperMethods javaScriptHelperMethods;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        this.elementsMethods = new ElementsMethods(driver);
        this.javaScriptHelperMethods = new JavaScriptHelperMethods(driver);
        PageFactory.initElements(driver, this);
    }
}
