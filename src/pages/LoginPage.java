package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	// ctor
	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void login(String name, String pass) {
		this.getEmailInput().clear();
		this.getEmailInput().sendKeys(name);
		this.getPasswordInput().clear();
		this.getPasswordInput().sendKeys(pass);
		this.clickRememberMe();
		this.clickSubmitBtn();
	}

	public void clickRememberMe() {
		this.getRememberMeCheckBox().click();
	}

	public void clickSubmitBtn() {
		this.getSubmitBtn().click();
	}

	// getters
	public WebElement getEmailInput() {
		return this.driver.findElement(By.name("username"));
	}

	public WebElement getPasswordInput() {
		return this.driver.findElement(By.name("password"));
	}

	public WebElement getRememberMeCheckBox() {
		return this.driver.findElement(By.name("remember_me"));
	}

	public WebElement getSubmitBtn() {
		return this.driver.findElement(By.name("btn_submit"));
	}
}
