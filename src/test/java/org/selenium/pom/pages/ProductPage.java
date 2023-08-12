package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class ProductPage extends BasePage {
	
	private final By title = By.cssSelector(".product_title.entry-title");
	private final By addToCartBtn = By.xpath("//button[@value='1215']");
	private final By addToCartSuccessMessage = By.cssSelector("div[role='alert']");
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
    public ProductPage loadProduct(String productNameSeparatedByDash){
        load("/product/" + productNameSeparatedByDash + "/");
        return this;
    }
	
	public String getProductTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
	}
	
	public ProductPage addToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
		return this;
	}
	
	public String getSuccessMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartSuccessMessage)).getText();
	}

}
