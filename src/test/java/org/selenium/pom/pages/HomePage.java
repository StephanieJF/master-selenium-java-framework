package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {
	
	private final By storeMenuLink = By.linkText("Store");

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
}
