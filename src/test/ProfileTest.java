package test;

import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public class ProfileTest extends BaseTest {

	@Test(priority = 5, description="login, editing profile, logout.")
	public void editProfile() throws InterruptedException {

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

		LocationPopupPage locationPopupPage = new LocationPopupPage(driver, wait, jsExecutor);
		LoginPage loginPage = new LoginPage(driver, wait, jsExecutor);
		NotificationSistemPage notificationSistemPage = new NotificationSistemPage(driver, wait, jsExecutor);
		ProfilePage profilePage = new ProfilePage(driver, wait, jsExecutor);
		AuthPage authPage = new AuthPage(driver, wait, jsExecutor);

		this.driver.navigate().to(baseUrl + "guest-user/login-form");
		
		locationPopupPage.closePopup();
		loginPage.login(name, pass);

		String messageOne = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(messageOne.contains("Login Successfull"), "[ERROR] Unexpected login message");

		this.driver.navigate().to(baseUrl + "member/profile");
		profilePage.changeBasicInfo(firstName, lastName, address, phone, zip, country, stateIndex, cityIndex);

		String messageTwo = notificationSistemPage.getMessage();
		System.out.println(messageTwo);
		this.softAssert.assertTrue(messageOne.contains("Setup Successfull"),
				"[ERROR] Unexpected profile update message");

		authPage.logout();
		String messageThree = notificationSistemPage.getMessage();
		this.softAssert.assertTrue(messageThree.contains("Logout Successfull!"), "[ERROR] Unexpected logout message");

	}
}
