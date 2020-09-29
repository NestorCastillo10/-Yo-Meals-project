package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {

	// ctor
	public ProfilePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void changeBasicInfo(String firstName, String lastName, String address, String phone, String country,
			String state, int cityIndex) {

		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);

		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);

		this.getAddress().clear();
		this.getAddress().sendKeys(address);

		this.getPhone().clear();
		this.getPhone().sendKeys(phone);

		this.getCountrySelect().selectByVisibleText(country);
		this.getStateSelect().selectByValue(state);
		this.getCitySelect().selectByIndex(cityIndex);
		
		this.clickOnSaveBtn();
	}

	public void clickUploadFile() {
		JavascriptExecutor jse = (JavascriptExecutor) this.driver;
		String scriptOne = "arguments[0].click();";
		jse.executeScript(scriptOne, this.getUploadFile());

		this.getUploadFile().click(); // mozda bez ovoga
	}

	public void uploadImage(String path) {
		this.getUploadFile().sendKeys(path);
	}

	public void deleteImage() {
		JavascriptExecutor jse = (JavascriptExecutor) this.driver;
		String scriptOne = "arguments[0].click();";
		jse.executeScript(scriptOne, this.getRemoveBtn());
	}

	public void clickOnSaveBtn() {
		this.getSaveBtn().click();
	}

	// getters
	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountrySelect() {
		return new Select(this.driver.findElement(By.id("user_country_id")));
	}

	public Select getStateSelect() {
		return new Select(this.driver.findElement(By.id("user_state_id")));
	}

	public Select getCitySelect() {
		return new Select(this.driver.findElement(By.id("user_city")));
	}

	public WebElement getSaveBtn() {
		return this.driver.findElement(By.name("btn_submit"));
	}

//ovo
	public WebElement getHoverElement() {
		return this.driver.findElement(By.className("hover-elemnts"));
	}

	// ovo
	public void clickOnHOverElement() {
		this.getHoverElement().click();
	}

	public WebElement getUploadFile() {
		return this.driver.findElement(By.xpath("//a[@class='upload uploadFile-Js']"));
	}

	public WebElement getRemoveBtn() {
		return this.driver.findElement(By.xpath("//a[@class='remove']"));
	}

}