package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasePage {

	// ctor
	public NotificationSistemPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void noticeIsNotVisible() {
		this.wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"), "style",
				"display: none;"));
	}

	public String getMessage() {
		return this.getMsgElement().getText();
	}

	// getter
	public WebElement getMsgElement() {
		return this.driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

}
