package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasePage {

	// ctor
	public NotificationSistemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
		super(driver, wait, jsExecutor);
	}

	public void noticeIsNotVisible() {
		this.wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"), "style",
				"display: none;"));
	}

	// getters
	public WebElement getMsgElement() {
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]")));
		return alert;
	}

	public String getMessage() {
		return this.getMsgElement().getText();
	}
}
