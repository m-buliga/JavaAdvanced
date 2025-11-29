package helpers;

import core.logging.LoggerUtilityUi;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ElementsMethods {
    WebDriver driver;
    Actions actions;

    public ElementsMethods(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

   /* public void fillElement(WebElement element, String value) {
        element.sendKeys(value);
    }*/

    public void fillElement(WebElement element, Object value) {
        try {
            waitForVisibilityOfElement(element);
            LoggerUtilityUi.infoLog("Waiting for element " + element.toString() + " to be visible.");

            element.clear();
            LoggerUtilityUi.infoLog("Clearing the element " + element.toString());

            element.sendKeys(String.valueOf(value));
            LoggerUtilityUi.infoLog("Filling the element " + element.toString() + " with value " + value);

        } catch (Exception e) {
            LoggerUtilityUi.errorLog("Element " + element.toString() + " couldn't be filled. Error: " + e.getMessage());
            throw new RuntimeException("Filling element " + element.toString() + " failed.");
        }
    }


    public WebElement findElementFromListByText(List<WebElement> elementsList, String value) {
        for (WebElement element : elementsList) {
            if (element.getText().equals(value)) {
                return element;
            }
        }
        throw new NoSuchElementException("Elementul '" + value + "' nu este in lista.");
    }

    public void fillWithActions(WebElement element, String value) {
        actions.sendKeys(value).perform();
        waitForVisibilityOfElement(element);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void waitForVisibilityOfElement(WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                element
        );

        // definim un wait explicit care asteapta pana un anume element e vizibil
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void fillMultipleValues(WebElement element, List<String> list) {
        for (String value : list) {
            element.sendKeys(value);
            element.sendKeys(Keys.ENTER);
        }
    }

    public void clickMultipleValues(List<WebElement> elements, List<String> list) {
        for (String value : list) {
            for (WebElement element : elements) {
                if (element.getText().equals(value)) {
                    element.click();
                }
            }
        }
    }

    public void selectDropDownValue(WebElement element, WebElement inputElement, String value) {
        element.click();
        inputElement.clear();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-yk16xz-control")));
        inputElement.sendKeys(value);
        inputElement.sendKeys(Keys.ENTER);
    }

    public void uploadFile(WebElement element, String pathToFile) {
        element.sendKeys(pathToFile);
    }


}
