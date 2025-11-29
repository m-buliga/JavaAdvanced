package helpers;

import core.logging.LoggerUtilityUi;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptHelperMethods {

    static WebDriver driver;
    JavascriptExecutor jsExecutor;

    public JavaScriptHelperMethods(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public void scrollToElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void scrollBy(int x, int y) {
        jsExecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    public static void removeBannersIfPresent(WebDriver driver) {
        LoggerUtilityUi.infoLog("All banners (if any) are removed.");
        try {
            ((JavascriptExecutor) JavaScriptHelperMethods.driver).executeScript(
                    "const a=document.querySelector('#fixedban'); if(a) a.remove();" +
                            "const f=document.querySelector('footer'); if(f) f.remove();" +
                            "const c=document.querySelector('.fc-consent-root'); if(c) c.remove();"
            );
        } catch (Exception ignored) {}
    }
}
