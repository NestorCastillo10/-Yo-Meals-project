package test;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public class ProfileTest extends BaseTest {

	@Test(priority = 5, description = "Testing LogIn, Editing Profile and Logout.")
	public void editProfile() throws InterruptedException {

		LocationPopupPage locationPopupPage = new LocationPopupPage(driver, wait, jsExecutor);
		LoginPage loginPage = new LoginPage(driver, wait, jsExecutor);
		NotificationSistemPage notificationSistemPage = new NotificationSistemPage(driver, wait, jsExecutor);
		ProfilePage profilePage = new ProfilePage(driver, wait, jsExecutor);
		AuthPage authPage = new AuthPage(driver, wait, jsExecutor);

		String name = "customer@dummyid.com";
		String pass = "12345678a";
		String firstName = "Nestor";
		String lastName = "Castillo";
		String address = "Le Cap D'Adge";
		String phone = "+486432901";
		String zip = "2901";
		String country = "United Kingdom";
		int stateIndex = 2;
		int cityIndex = 1;
		String message = "";

		// LogIn
		this.driver.navigate().to(baseUrl + "guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.login(name, pass);

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Login Successfull"), "[ERROR] Login FAILED!");

		// Editing Profile
		this.driver.navigate().to(baseUrl + "member/profile");
		profilePage.changeBasicInfo(firstName, lastName, address, phone, zip, country, stateIndex, cityIndex);

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Setup Successful"), "[ERROR] Unexpected profile update message");

		// LogOut
		authPage.logout();
		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Logout Successful!"), "[ERROR] Logout FAILED!");

		softAssert.assertAll();
	}

	// @Test(priority = 10, description = "Testing the Functionality of Adding and
	// Deleting Profile Images")
	public void changeProfileImage() throws IOException, InterruptedException {

		LocationPopupPage locationPopupPage = new LocationPopupPage(driver, wait, jsExecutor);
		LoginPage loginPage = new LoginPage(driver, wait, jsExecutor);
		NotificationSistemPage notificationSistemPage = new NotificationSistemPage(driver, wait, jsExecutor);
		ProfilePage profilePage = new ProfilePage(driver, wait, jsExecutor);
		AuthPage authPage = new AuthPage(driver, wait, jsExecutor);

		String name = "customer@dummyid.com";
		String pass = "12345678a";
		String message = "";
		// String imgPath = new File("imagеs/nestor.jpg").getCanonicalPath();
		String imgPath = "C:\\Users\\ivica\\Desktop\\Zavrsni Projekat\\-Yo-Meals-project\\images\\nestor.jpg";

		// LogIn
		this.driver.navigate().to(baseUrl + "guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.login(name, pass);

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Login Successfull"), "[ERROR] Login FAILED!");

		// Upload Image
		this.driver.navigate().to(baseUrl + "member/profile");
		profilePage.uploadImage(imgPath);

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Profile Image Uploaded Successfully"),
				"[ERROR] Image is not uploaded!!");

		notificationSistemPage.noticeIsNotVisible();

		// Delete Image
		profilePage.deleteImage();

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Profile Image Deleted Successfully"),
				"[ERROR] The image is not deleted");

		notificationSistemPage.noticeIsNotVisible();

		// LogOut
		authPage.logout();

		message = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(message.contains("Logout Successfull!"), "[ERROR] Logout FAILED!");

		softAssert.assertAll();
	}
}
