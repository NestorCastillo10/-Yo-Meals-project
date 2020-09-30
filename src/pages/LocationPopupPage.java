package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasePage {

	// ctor
	public LocationPopupPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
		super(driver, wait, jsExecutor);
	}

	public void setLocation(String locationName) {
		this.clickOnKeyword();
		String dataValue = this.getDataValue(locationName);

		String scriptOne = "arguments[0].value=arguments[1]";
		jsExecutor.executeScript(scriptOne, locationName, dataValue);

		String scriptTwo = "arguments[0].click()";
		jsExecutor.executeScript(scriptTwo, this.getSubmit());
	}

	public void showPopup() {
		this.getSelectLocationInHeader().click();
	}

	public void closePopup() {
		this.getCloseBtn().click();
	}

	public void clickOnKeyword() {
		this.getKeyword().click();
	}

	// getters
	public WebElement getSelectLocationInHeader() {
		return this.driver.findElement(By.xpath("//div[@class='location-selector']"));
	}

	public WebElement getCloseBtn() {
		return this.driver.findElement(By.xpath("//a[@class='close-btn close-btn-white']"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.id("locality_keyword"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), 'Test - Caxito')]/parent::*"));
	}

	public String getDataValue(String locationName) {
		return this.getLocationItem(locationName).getAttribute("data-value");
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.id("location_id"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.name("btn_submit"));
	}
}
