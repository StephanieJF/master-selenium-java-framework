package org.selenium.pom.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage{
	
//	private final By productName = By.cssSelector("td[class='product-name'] a");
//	private final By checkoutBtn = By.linkText("PROCEED TO CHECKOUT");
	
	@FindBy(css = "td[class='product-name'] a") private WebElement productName;
	@FindBy(linkText = "PROCEED TO CHECKOUT") @CacheLookup WebElement checkoutBtn;
	@FindBy(id = "coupon_code") private WebElement couponCodeInput;
	@FindBy(css = "button[name='apply_coupon']") private WebElement applyCouponBtn;
	@FindBy(css = "[role='alert']") private WebElement couponAppliedAlert;
	@FindBy(xpath = "//ul[@id='shipping_method']//label[.='Free shipping']") private WebElement freeShippingRadio;
	@FindBy(xpath = "//*[@class='product-subtotal']//bdi") private WebElement cartSubtotal;
	@FindBy(xpath = "*//ul[@id='shipping_method']//bdi") private WebElement shippingCost;
	@FindBy(xpath = "//ul[@id='shipping_method']//input[contains(@value, 'free_shipping')]") private WebElement freeShipping;
	@FindBy(css = "#shipping_method li") private List<WebElement> shippingOptions;
	@FindBy(xpath = "//td[contains(@data-title, 'State Tax')]") private WebElement salesTax;
	@FindBy(xpath = "*//td[@data-title='Total']//bdi") private WebElement cartTotal;
	@FindBy(css = "div[role='alert']") private WebElement couponSuccessMessage;
	@FindBy(xpath = "//th[.='Coupon: offcart5']") private WebElement fiveOffDiscount;


	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public CartPage load() {
		load("/cart/");
		return this;
	}
	
	public String getProductName() {
		return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
	}
	
	public CheckoutPage checkout() {
		wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
		return new CheckoutPage(driver);
	}
	
	public double getCartSubtotal() {
		return Double.parseDouble(cartSubtotal.getText().substring(1));
	}
	
	public CartPage enterCouponCode(String couponCode) {
		wait.until(ExpectedConditions.visibilityOf(couponCodeInput)).sendKeys(couponCode);
		applyCouponBtn.click();
		return this;
	}
	
	public double getShippingCost() {
			return Double.parseDouble(shippingCost.getText().substring(1));	
	}
	
	public float getSalesTax() {
		return Float.parseFloat(salesTax.getText().substring(1));
	}
	
	public double getCartTotal() {
		return Double.parseDouble(cartTotal.getText().substring(1));
	}

	
	public CartPage loadFreeShipping() {
		wait.until(ExpectedConditions.visibilityOf(freeShippingRadio));
		return this;
	}
	
	public CartPage loadFiveOff() {
		wait.until(ExpectedConditions.visibilityOf(fiveOffDiscount));
		return this;
	}
}
