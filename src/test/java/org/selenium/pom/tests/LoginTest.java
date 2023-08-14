package org.selenium.pom.tests;

import java.io.IOException;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.AccountPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Cookies;

public class LoginTest extends BaseTest {
	
	@Test
	public void loginDuringCheckout() throws IOException, InterruptedException {
		SignUpApi signUpApi = new SignUpApi();
		String username = "demouser" + new FakerUtils().generateRandomNumber();
		User user = new User().
				setUsername(username).
				setPassword("demopwd").
				setEmail(username + "@testing.com");
		
		signUpApi.register(user);
		CartApi cartApi = new CartApi(new Cookies());
		Product product = new Product(1215);
		cartApi.addToCart(product.getId(), 1);
		
		CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
		Thread.sleep(5000);
		injectCookiesToBrowser(cartApi.getCookies());
		checkoutPage.load();
		Thread.sleep(5000);
		checkoutPage.
				clickLoginLink().
				login(user);
		Thread.sleep(5000);
		
		Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
	}
	
	@Test
	public void failedLoginAccountDoesNotExist() {
		String username = "demouser" + new FakerUtils().generateRandomNumber();
		User user = new User(username, "demopwd");
		AccountPage accountPage = new AccountPage(getDriver()).
				load().
				login(user.getUsername(), user.getPassword());
		Assert.assertEquals(accountPage.getErrorMessage(), "Error: The username " + user.getUsername() +
                " is not registered on this site." +
                " If you are unsure of your username, try your email address instead.");
	}
	
	@Test
	public void failedLoginIncorrectPassword() {
		String username = "demouser" + new FakerUtils().generateRandomNumber();
		User user = new User(username, "demopwd", username + "@testing.com");
		SignUpApi signUpApi = new SignUpApi();
		signUpApi.register(user);
		AccountPage accountPage = new AccountPage(getDriver());
		accountPage.load().login(user.getUsername(), "demopwd2");
		Assert.assertEquals(accountPage.getErrorMessage(), "Error: The password you entered for the username "
                + user.getUsername() + " is incorrect. Lost your password?");
	}
}


















