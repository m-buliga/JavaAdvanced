package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminHomePage extends CommonPage {

    public AdminHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@data-test='page-title']")
    private WebElement pageTitle;


    public boolean checkPageTitle(String expectedTitle) {
        return pageTitle.isDisplayed() && pageTitle.getText().equals(expectedTitle);
    }
}
