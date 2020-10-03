package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import pages.LocationPopupPage;
import pages.SearchResultPage;

public class SearchTest extends BaseTest {

	public DataImportClass xlsxFile;

	@Test(description = "Check the Displayed Search Results")
	public void searchResults()
			throws IOException, InstantiationException, IllegalAccessException, InterruptedException {

		LocationPopupPage locationPopupPage = new LocationPopupPage(driver, wait, jsExecutor);
		SearchResultPage searchResultPage = new SearchResultPage(driver, wait, jsExecutor);

		String locationName = "City Center - Albany";

		this.driver.navigate().to(this.baseUrl + "meals");
		locationPopupPage.setLocation(locationName);

		// -------- data import ------

		DataImportClass xlsxFile = new DataImportClass();
		List<DataImportClass> listOfSearches = xlsxFile.importSearches();

		// Searching...
		for (int i = 0; i < listOfSearches.size(); i++) {

			this.driver.navigate().to(listOfSearches.get(i).url);

			locationPopupPage.showPopup();
			locationPopupPage.setLocation(listOfSearches.get(i).location);
			Thread.sleep(1000);

			softAssert.assertEquals(listOfSearches.get(i).listOfResults.size(),
					searchResultPage.numberOfSearchResults(),
					"[ERROR] !! The number of results found on the page is incorrect!!");

			ArrayList<String> xlsxList = listOfSearches.get(i).listOfResults;
			ArrayList<String> siteList = searchResultPage.getProductsNames();

			for (int j = 0; j < xlsxList.size(); j++) {
				softAssert.assertTrue(siteList.get(j).contains(xlsxList.get(j)),
						"[ERROR] The order of the found results is not appropriate!");
			}
		}
		softAssert.assertAll();
	}
}
