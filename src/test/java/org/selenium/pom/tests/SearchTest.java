package org.selenium.pom.tests;

import java.io.IOException;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
	
	@Test
	public void searchWithPartialMatch() {
		String searchFor = "Blue";
		StorePage storePage = new StorePage(getDriver()).
				load().
				search(searchFor);
		Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
	}
	
	@Test
	public void searchWithExactMatch() throws IOException {
		String product = new Product(1215).getName();
		ProductPage productPage = new StorePage(getDriver()).
				load().
				searchExactMatch(product);
		Assert.assertEquals(productPage.getProductTitle(), product);
				
	}
	
	@Test
	public void searchNonExistingProduct() {
		StorePage storePage = new StorePage(getDriver()).
		load().
		search(new FakerUtils().generateRandomName());
		Assert.assertEquals(storePage.getProductSearchFailureMessage(), "No products were found matching your selection.");
	}
}
