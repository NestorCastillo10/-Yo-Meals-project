package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasePage {

	// ctor
	public AuthPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
		super(driver, wait, jsExecutor);
		// TODO Auto-generated constructor stub
	}

	public void logout() {
		this.clickOnUser();
		this.cliCkOnLogout();
	}

	public void cliCkOnLogout() {
		this.getLogout().click();
	}

	public void clickOnUser() {
		this.getUserElement().click();
	}

	// getters
	public WebElement getUserElement() {
		return this.driver.findElement(By.xpath("//a[@class='after-arrow user-trigger-js']"));
	}

	public WebElement getMyAccount() {
		return this.driver.findElement(By.xpath("(//div[@class='my-account-dropdown']/descendant::li)[1]/a"));
	}

	public WebElement getLogout() {
		return this.driver.findElement(By.xpath("(//div[@class='my-account-dropdown']/descendant::li)[2]/a"));
	}
}
