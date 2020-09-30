package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasePage {

	// ctor
	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
		super(driver, wait, jsExecutor);
	}

	public void addMeal(String qty) {
		this.clickOnMealImg();
		this.wait.until(ExpectedConditions.elementToBeClickable(getAddToCart()));
		this.clickOnQty();
		this.getQty().clear();
		this.getQty().sendKeys(qty);
		this.getAddToCart();
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

	// getters
	public WebElement getFavBtn() {
		return this.driver.findElement(By.id("item_118"));
	}

	public WebElement getMealImage() {
		return this.driver.findElement(By.xpath("(//img[@alt='Product Image'])[3]"));
	}

	public WebElement getAddToCart() {
		return this.driver.findElement(By.xpath("//a[contains(.,'Add To Cart')]"));
	}

	public WebElement getQty() {
		return this.driver.findElement(By.name("product_qty"));
	}
}
