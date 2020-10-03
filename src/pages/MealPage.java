package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasePage {

	// ctor
	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
		super(driver, wait, jsExecutor);
	}

	public void addMeal(int qty) {
		this.getQty().sendKeys(Keys.chord(Keys.CONTROL, "a"));

		String script = "arguments[0].value=arguments[1]";
		this.jsExecutor.executeScript(script, getQty(), String.valueOf(qty));

		this.clickOnAddBtn();
	}

	public void addToFavotife() {
		this.getFavBtn().click();
	}

	public void clickOnMealImg() {
		this.getMealImage().click();
	}

	public void clickOnQty() {
		this.getQty().click();
	}

	public void clickOnAddBtn() {
		this.getAddToCart().click();
	}

	// getters
	public WebElement getFavBtn() {
		return this.driver.findElement(By.xpath("//div[@class='product-detail-image']/child::a"));
	}

	public WebElement getMealImage() {
		return this.driver.findElement(By.xpath("(//img[@alt='Product Image'])[3]"));
	}

	public WebElement getAddToCart() {
		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Add To Cart')]")));
		return btn;
	}

	public WebElement getQty() {
		WebElement qty = wait.until(ExpectedConditions.elementToBeClickable(By.name("product_qty")));
		return qty;
	}
}
