package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {
	
	private final By storeMenuLink = By.linkText("Store");
	private final By viewCartLink = By.cssSelector("a[title='View cart']");
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public HomePage load() {
		load("/");
		return this;
	}
	
	public StorePage navigateToStoreUsingMenu() {
		driver.findElement(storeMenuLink).click();
		return new StorePage(driver); //return an object of the new page being navigated to
	}
	
	public ProductPage navigateToProductPage(String productName) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(), '" + productName + "')]"))).click();
		return new ProductPage(driver);
	}
	
	public HomePage addItemToCart(Integer id) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("*//a[@href='?add-to-cart=" + id + "']"))).click();
		return this;
	}
	
	public CartPage clickViewCart() {
		wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
		return new CartPage(driver);
	}
	
	private By getAddToCartBtnElement(String productName) {
		return By.cssSelector("a[aria-label='Add “" +productName + "” to your cart']");
	}
	
	public HomePage clickAddToCartBtn(String productName) {
		By addToCartBtn = getAddToCartBtnElement(productName);
		wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
		return this;
	}
}
