package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor jsExecutor;

	public BasePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
		super();
		this.driver = driver;
		this.wait = wait;
		this.jsExecutor = jsExecutor;
	}
}
