package ui.pages;

import core.logging.LoggerUtilityUi;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.object.data.ui.LoginObject;


public class LoginPage extends CommonPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@data-test='nav-sign-in']")
    private WebElement signInButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "email-error")
    private WebElement emailError;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "password-error")
    private WebElement passwordError;

    @FindBy(xpath = "//*[@data-test='login-submit']")
    private WebElement loginSubmitButton;

    @FindBy(xpath = "//*[@data-test='login-error']")
    private WebElement loginErrorMessage;


    public void openLoginForm() {
        //javaScriptHelperMethods.scrollToElement(driver, signInButton);
        signInButton.click();
        LoggerUtilityUi.infoLog("The user clicked Sign In button");
    }

    public void fillValidCredentials(LoginObject loginObject) {
        elementsMethods.fillElement(emailInput, loginObject.getEmail());
        elementsMethods.fillElement(passwordInput, loginObject.getPassword());
        LoggerUtilityUi.infoLog("The user provided correct email and password.");
    }

    public void fillInvalidCredentials(String email, String password) {
        elementsMethods.fillElement(emailInput, email);
        elementsMethods.fillElement(passwordInput, password);

        LoggerUtilityUi.infoLog("The user provided incorrect email and password.");
    }

    public void clickLogin() {
        elementsMethods.clickElement(loginSubmitButton);
        LoggerUtilityUi.infoLog("The user clicked login button.");
    }

    public String getEmailError() {
        LoggerUtilityUi.infoLog("Getting email error message.");
        return emailError.getText();
    }

    public String getPasswordError() {
        LoggerUtilityUi.infoLog("Getting password error message.");
        return passwordError.getText();
    }

    public String getLoginErrorMessage() {
        LoggerUtilityUi.infoLog("Getting general login error message.");
        return loginErrorMessage.getText();
    }

}
