package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;

public class PageHeader extends BasePage {
	
	private final By storeMenuLink = By.linkText("Store");
	
	public PageHeader(WebDriver driver) {
		super(driver);
	}
	
	public StorePage navigateToStoreUsingMenu() {
		driver.findElement(storeMenuLink).click();
		return new StorePage(driver); //return an object of the new page being navigated to
	}
	
	public ProductPage navigateToProductPage(String productName) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(), '" + productName + "')]"))).click();
		return new ProductPage(driver);
	}


}
