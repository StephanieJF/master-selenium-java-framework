package org.selenium.pom.tests;

import java.io.IOException;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.DataProviders;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {
	
	@Test(dataProvider = "getStorePageProducts", dataProviderClass = DataProviders.class)
	public void addToCartFromStorePage(Product product) throws IOException {
		CartPage cartPage = new StorePage(getDriver()).
				load().getProductThumbnail().
				clickAddToCartBtn(product.getName()).
				clickViewCart();
		Assert.assertEquals(cartPage.getProductName(), product.getName());
	}

	
	@Test
	public void addToCartFromProductPage() throws IOException {
		Product product = new Product(1215);
		String productNameSeparatedByDashes = product.getName().toLowerCase().replace(" ", "-");
		ProductPage productPage = new ProductPage(getDriver()).loadProduct(productNameSeparatedByDashes).addToCart();
		Assert.assertTrue(productPage.getSuccessMessage().contains("“" + product.getName() +"” has been added to your cart."));
	}
	
	@Test
	public void addFeaturedProductToCart() throws IOException {
		Product product = new Product(1215);
		CartPage cartPage = new HomePage(getDriver()).load().
				getProductThumbnail().clickAddToCartBtn(product.getName()).clickViewCart();
		Assert.assertEquals(cartPage.getProductName(), product.getName());Assert.assertEquals(cartPage.getProductName(), product.getName());	
	}
	
	
	@Test(dataProvider = "getFeaturedProducts", dataProviderClass = DataProviders.class)
	public void addToCartFromFeaturedProducts(Product product) {
		CartPage cartPage = new HomePage(getDriver()).load().
				getProductThumbnail().clickAddToCartBtn(product.getName()).
				clickViewCart();
		Assert.assertEquals(cartPage.getProductName(), product.getName());
		
	}
}
