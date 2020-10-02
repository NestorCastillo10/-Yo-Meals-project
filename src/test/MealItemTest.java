package test;

import java.util.ArrayList;

import org.apache.poi.util.SystemOutLogger;
import org.testng.annotations.Test;

import com.sun.corba.se.spi.orbutil.proxy.LinkedInvocationHandler;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public class MealItemTest extends BaseTest {

	@Test(priority = 10, description = "Trying to add meal to cart without and with select location")
	public void addMealToCard() throws InterruptedException {

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
}
