package org.selenium.pom.tests;

import java.io.IOException;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;


public class FirstTestCase extends BaseTest {
	
	//@Test
	public void guestCheckoutUsingDirectBankTransfer() throws IOException {

		String searchFor = "Blue";
		
		BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
		Product product = new Product(1215);
		
		StorePage storePage = new HomePage(getDriver()).
				load().
				getPageHeader().navigateToStoreUsingMenu().
				search(searchFor);
		
		Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
		storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
		
		CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
		Assert.assertEquals(cartPage.getProductName(), product.getName());
		
		CheckoutPage checkoutPage = cartPage.
			checkout().
			setBillingAddress(billingAddress).
			selectDirectBankTransfer().
			placeOrder();
		Assert.assertEquals(checkoutPage.getOrderConfirmationText(), "Thank you. Your order has been received.");
	}
	
	//@Test
	public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
		
		String searchFor = "Blue";
		BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
		Product product = new Product(1215);
		User user = new User(ConfigLoader.getInstance().getUserName(), ConfigLoader.getInstance().getPassword());
		
		StorePage storePage = new HomePage(getDriver()).
				load().
				getPageHeader().navigateToStoreUsingMenu().
				search(searchFor);
		
		Assert.assertEquals(storePage.getTitle(), "Search results: “"+ searchFor +"”");
		storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
		
		CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
		Assert.assertEquals(cartPage.getProductName(), product.getName());
		
		CheckoutPage checkoutPage = cartPage.
				checkout().
				clickLoginLink().
				login(user).
				setBillingAddress(billingAddress).
				selectDirectBankTransfer().
				placeOrder();
		Assert.assertEquals(checkoutPage.getOrderConfirmationText(), "Thank you. Your order has been received.");
	}
}
