package org.selenium.pom.tests;

import java.io.IOException;

import org.selenium.pom.api.actions.BillingApi;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
	
	@Test
	public void guestCheckoutUsingDirectBankTransfer() throws IOException {
		BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
		CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
		CartApi cartApi = new CartApi();
		cartApi.addToCart(1215, 1);
		injectCookiesToBrowser(cartApi.getCookies());
		checkoutPage.load().
		setBillingAddress(billingAddress).
		selectDirectBankTransfer().
		placeOrder();
	Assert.assertEquals(checkoutPage.getOrderConfirmationText(), "Thank you. Your order has been received.");
}
	
	@Test
	public void loginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
		BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
		SignUpApi signUpApi = new SignUpApi();
		String username = "demouser" + new FakerUtils().generateRandomNumber();
		User user = new User().
				setUsername(username).
				setPassword("demopwd").
				setEmail(username + "@testing.com");
		signUpApi.register(user);
		CartApi cartApi = new CartApi(signUpApi.getCookies());
		Product product = new Product(1215);
		cartApi.addToCart(product.getId(), 1);
		
		CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
		Thread.sleep(5000);
		injectCookiesToBrowser(signUpApi.getCookies());
		checkoutPage.load().
		setBillingAddress(billingAddress).
		selectDirectBankTransfer().
		placeOrder();
	Assert.assertEquals(checkoutPage.getOrderConfirmationText(), "Thank you. Your order has been received.");
	}
	
	@Test
	public void checkoutAccountAndEditBillingAddress() throws IOException, InterruptedException {
		BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User(username, "demopwd", username + "@testing.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        BillingApi billingApi = new BillingApi(signUpApi.getCookies());
        billingApi.addBillingAddress(billingAddress);

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load();
        checkoutPage.selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getOrderConfirmationText(), "Thank you. Your order has been received.");
	}
	
	@Test
	public void guestCheckoutUsingCashOnDelivery() throws InterruptedException, IOException {
		BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
		CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
		CartApi cartApi = new CartApi();
		cartApi.addToCart(1215, 1);
		injectCookiesToBrowser(cartApi.getCookies());
		checkoutPage.load().
		setBillingAddress(billingAddress).
		selectCashOnDeliveryRadioBtn().
		placeOrder();
	Assert.assertEquals(checkoutPage.getOrderConfirmationText(), "Thank you. Your order has been received.");
	}
	
	@Test
	public void loginAndCheckoutUsingCashOnDelivery() throws IOException, InterruptedException {
		BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
		SignUpApi signUpApi = new SignUpApi();
		String username = "demouser" + new FakerUtils().generateRandomNumber();
		User user = new User().
				setUsername(username).
				setPassword("demopwd").
				setEmail(username + "@testing.com");
		signUpApi.register(user);
		CartApi cartApi = new CartApi(signUpApi.getCookies());
		Product product = new Product(1215);
		cartApi.addToCart(product.getId(), 1);
		CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
		Thread.sleep(5000);
		injectCookiesToBrowser(signUpApi.getCookies());
		checkoutPage.load().
		setBillingAddress(billingAddress).
		selectCashOnDeliveryRadioBtn().placeOrder();
		Assert.assertEquals(checkoutPage.getOrderConfirmationText(), "Thank you. Your order has been received.");
	}

}
