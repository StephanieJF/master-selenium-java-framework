package org.selenium.pom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {
	private final By checkoutPageTitle = By.cssSelector("h1[class='has-text-align-center']");
	private final By firstNameFld = By.id("billing_first_name");
	private final By lastNameFld = By.id("billing_last_name");
	private final By countryDropDown = By.id("billing_country");
	private final By addressFld = By.id("billing_address_1");
	private final By cityFld = By.id("billing_city");
	private final By stateDropDown = By.id("billing_state");
	private final By postCodeFld = By.id("billing_postcode");
	private final By emailFld = By.id("billing_email");
	private final By directBankTransferRadioBtn = By.id("payment_method_bacs");
	private final By placeOrderBtn = By.cssSelector("button#place_order");
	private final By orderConfirmationText = By.cssSelector(".woocommerce-notice");
	
	private final By loginLink = By.cssSelector(".showlogin");
	private final By usernameFld = By.id("username");
	private final By pwdFld = By.id("password");
	private final By loginBtn = By.name("login");
	private final By overlay = By.cssSelector(".blockUI.blockOverlay");
	
	private final By productName = By.cssSelector("td[class='product-name']");

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	public CheckoutPage load() {
		load("/checkout/");
		return this;
	}
	
	public String getCheckoutPageTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPageTitle)).getText();
	}
	
	public CheckoutPage enterFirstName(String firstName) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
		e.clear();
		e.sendKeys(firstName);
		return this;
	}
	
	public CheckoutPage enterLastName(String lastName) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
		e.clear();
		e.sendKeys(lastName);
		return this;
	}
	
	public CheckoutPage selectCountry(String countryName) {
		Select select = new Select(driver.findElement(countryDropDown));
		select.selectByVisibleText(countryName);
		return this;
	}
	
	public CheckoutPage enterAddressLineOne(String addressLineOne) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressFld));
		e.clear();
		e.sendKeys(addressLineOne);
		return this;
	}
	
	public CheckoutPage enterCity(String city) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(cityFld));
		e.clear();
		e.sendKeys(city);
		return this;
	}
	
	public CheckoutPage selectState(String state) {
		Select select = new Select(driver.findElement(stateDropDown));
		select.selectByVisibleText(state);
		return this;
	}
	
	public CheckoutPage enterPostalCode(String postCode) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(postCodeFld));
		e.clear();
		e.sendKeys(postCode);
		return this;
	}
	
	public CheckoutPage enterEmail(String email) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFld));
		e.clear();
		e.sendKeys(email);
		return this;
	}
	
	public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
		return enterFirstName(billingAddress.getFirstName()).
			enterLastName(billingAddress.getLastName()).
			selectCountry(billingAddress.getCountry()).
			enterAddressLineOne(billingAddress.getAddressLineOne()).
			enterCity(billingAddress.getCity()).
			selectState(billingAddress.getState()).
			enterPostalCode(billingAddress.getPostCode()).
			enterEmail(billingAddress.getEmail());
	}
	
	public CheckoutPage selectDirectBankTransfer() {
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
		if(!e.isSelected()) {
			e.click();
		}
		return this;
	}
	
	public CheckoutPage placeOrder() {
		waitForOverlaysToDisappear(overlay); //from basepage
		driver.findElement(placeOrderBtn).click();
		return this;
	}
	
	public String getOrderConfirmationText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationText)).getText();
	}
	
	public CheckoutPage clickLoginLink() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink)).click();
		return this;
	}
	
	
	public CheckoutPage enterLoginName(String userName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(userName);
		return this;
	}
	
	public CheckoutPage enterLoginPwd(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(pwdFld)).sendKeys(password);
		return this;
	}
	
	public CheckoutPage clickLoginBtn() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).click();
		return this;
	}
	
	public CheckoutPage login(User user) {
		return enterLoginName(user.getUsername()).
		enterLoginPwd(user.getPassword()).
		clickLoginBtn();
	}
	
	public String getProductName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
	}

}
