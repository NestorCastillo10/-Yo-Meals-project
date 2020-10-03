package test;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;

public class MealItemTest extends BaseTest {

	@Test(priority = 10, description = "Trying to add meal to cart without and with select location")
	public void addMealToCard() {

		LocationPopupPage locationPopupPage = new LocationPopupPage(driver, wait, jsExecutor);
		MealPage mealPage = new MealPage(driver, wait, jsExecutor);
		NotificationSistemPage notificationSistemPage = new NotificationSistemPage(driver, wait, jsExecutor);

		String message = "";
		String locationName = "City Center - Albany";
		int orderedPortions = 9;

		this.driver.navigate().to(baseUrl + "meal/chicken-sandwich-beef-empanadas-combo");

		// Add Meal
		locationPopupPage.closePopup();
		mealPage.addMeal(orderedPortions);

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("The Following Errors Occurred:" + "\n" + "Please Select Location"),
				"[ERROR] Unexpected message... 'Please Select Location message' is not display");

		notificationSistemPage.noticeIsNotVisible();

		// Set location, Add Meal.
		locationPopupPage.showPopup();
		locationPopupPage.setLocation(locationName);
		mealPage.addMeal(orderedPortions);

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Meal Added To Cart"), "[ERROR] Meal IS NOT Added To Cart!!");

		softAssert.assertAll();
	}

	@Test(priority = 20, description = "Adding Meals to Favorites With and Without Logging In")
	public void addMealToFavorite() {

		LocationPopupPage locationPopupPage = new LocationPopupPage(driver, wait, jsExecutor);
		MealPage mealPage = new MealPage(driver, wait, jsExecutor);
		NotificationSistemPage notificationSistemPage = new NotificationSistemPage(driver, wait, jsExecutor);
		LoginPage loginPage = new LoginPage(driver, wait, jsExecutor);

		String message = "";
		String name = "customer@dummyid.com";
		String pass = "12345678a";

		// Add To Favorite
		this.driver.navigate().to(this.baseUrl + "meal/west-fish-tacos-jackfruit");
		locationPopupPage.closePopup();
		mealPage.addToFavotife();

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Please login first!"),
				"[ERROR] Adding a meal without logging in succeeded !!");

		// User Login
		loginPage.clickLoginBtn();
		loginPage.login(name, pass);

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Login Successfull"), "[ERROR] Login FAILED!");

		// Add To Favorite
		this.driver.navigate().to(this.baseUrl + "meal/west-fish-tacos-jackfruit");
		mealPage.addToFavotife();

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Product has been added to your favorites."),
				"[ERROR] Product is not added to favorites.");

		softAssert.assertAll();
	}

	@Test(priority = 30, description = "Testing Cart Emptying Functionality ")
	public void clearCart() throws IOException {

		LocationPopupPage locationPopupPage = new LocationPopupPage(driver, wait, jsExecutor);
		MealPage mealPage = new MealPage(driver, wait, jsExecutor);
		NotificationSistemPage notificationSistemPage = new NotificationSistemPage(driver, wait, jsExecutor);
		CartSummaryPage cartSummaryPage = new CartSummaryPage(driver, wait, jsExecutor);

		String message = "";
		String locationName = "City Center - Albany";
		int orderedPortions = 5;

		this.driver.navigate().to(this.baseUrl + "meals");
		locationPopupPage.setLocation(locationName);

		// -------- data import ------
		ArrayList<String> urlList = DataImportClass.importUrls();

		// Adding Meals To the Cart
		for (int i = 0; i < urlList.size(); i++) {
			this.driver.navigate().to(urlList.get(i));
			mealPage.addMeal(orderedPortions);

			message = notificationSistemPage.getMessage();
			this.softAssert.assertTrue(message.contains("Meal Added To Cart"), "[ERROR] Meal IS NOT Added To Cart!!");
		}
		softAssert.assertAll();

		notificationSistemPage.noticeIsNotVisible();

		// Removing All Meals
		cartSummaryPage.clearAll();

		message = notificationSistemPage.getMessage();
		Assert.assertTrue(message.contains("All meals removed from Cart successfully"), "[ERROR] Cart is not empty!!");
	}
}
