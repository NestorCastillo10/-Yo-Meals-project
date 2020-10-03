package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {

	// ctor
	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
		super(driver, wait, jsExecutor);
	}

	public void changeBasicInfo(String firstName, String lastName, String address, String phone, String zip,
			String country, int stateIndex, int cityIndex) throws InterruptedException {

		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zip);

		this.getCountrySelect().selectByVisibleText(country);
		Thread.sleep(700);
		this.getStateSelect().selectByIndex(stateIndex);
		Thread.sleep(700);
		this.getCitySelect().selectByIndex(cityIndex);
		this.clickOnSaveBtn();

		// Thread.sleep() method is to synchronize all three select elements
	}

	public void uploadImage(String path) {
		String script = "arguments[0].click();";
		jsExecutor.executeScript(script, this.getCamera());

		this.getUploadForm().sendKeys(path);
	}

	public void deleteImage() {
		String script = "arguments[0].click();";
		jsExecutor.executeScript(script, this.getRemoveBtn());
	}

	public void clickOnSaveBtn() {
		// this.getSaveBtn().click();

		String script = "arguments[0].click();";
		jsExecutor.executeScript(script, this.getSaveBtn());
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

	public WebElement getUploadForm() {
		return this.driver.findElement(By.xpath("//*[@id='form-upload']/input"));
	}

	public WebElement getCamera() {
		return this.driver.findElement(By.xpath("//a[@class='upload uploadFile-Js']"));
	}

	public WebElement getRemoveBtn() {
		return this.driver.findElement(By.xpath("//a[@class='remove']"));
	}
}