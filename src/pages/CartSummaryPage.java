package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummaryPage extends BasePage {

	// ctor
	public CartSummaryPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
		super(driver, wait, jsExecutor);
	}

	public void clearAll() {
		this.clickOnClearAll();
	}

	public void clickOnClearAll() {
		this.getClearAllBtn().click();
	}

	// getter
	public WebElement getClearAllBtn() {
		return this.driver.findElement(By.xpath("//div[@class='cart-head']/child::a[last()] "));
	} // proveri da li je klikabilno dugme odmah u startu??
}
