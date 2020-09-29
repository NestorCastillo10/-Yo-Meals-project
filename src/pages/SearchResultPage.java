package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasePage {

	// ctor
	public SearchResultPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public List<WebElement> getSearchResult() {
		List<WebElement> list = this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
		return list;
	}

	public int numberOfSearchResults() {
		List<WebElement> listOfElements = this.getSearchResult();
		return listOfElements.size();
	}

	public ArrayList<String> getProductsNames() {
		List<WebElement> listOfElements = this.getSearchResult();
		ArrayList<String> listOfNames = new ArrayList<>();

		for (WebElement webElement : listOfElements) {
			listOfNames.add(webElement.getText());
		}
		return listOfNames;
	}

}
