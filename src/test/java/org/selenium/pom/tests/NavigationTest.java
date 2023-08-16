package org.selenium.pom.tests;

import java.io.IOException;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {
	
	@Test
	public void navigateFromHomeToStoreUsingMainMenu() {

		StorePage storePage = new HomePage(getDriver()).
				load().
				getPageHeader().navigateToStoreUsingMenu();
		Assert.assertEquals(storePage.getTitle(), "Store");
	}
	
	@Test
	public void navigateFromStoreToProductPage() throws IOException {
		String product = new Product(1215).getName();
		ProductPage productPage = new StorePage(getDriver()).load().
				navigateToProductPage(product);
		Assert.assertEquals(productPage.getProductTitle(), product);
	}
	
	@Test
	public void navigateFromHomeToFeaturedProductPage() throws IOException {
		String product = new Product(1215).getName();
		ProductPage productPage = new HomePage(getDriver()).load().
				getPageHeader().navigateToProductPage(product);
		Assert.assertEquals(productPage.getProductTitle(), product);
	}
	
}
