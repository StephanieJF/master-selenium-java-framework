package org.selenium.pom.base;

import java.util.List;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.restassured.http.Cookies;


public class BaseTest {
	
	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	
	private void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}
	
	protected WebDriver getDriver() {
		return this.driver.get();
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void startDriver(@Optional String browser) {
		browser = System.getProperty("browser", browser); //system property from mvn command if running from maven cli, otherwise defaults to testng.xml parameter
//		if(browser == null) browser = "CHROME";
		setDriver(new DriverManager().initializeDriver(browser));
		System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
	}
	
	
	@AfterMethod
	public void quitDriver() {
		getDriver().quit();
		System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
	}
	
	public void injectCookiesToBrowser(Cookies cookies) {
		List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
		for(Cookie cookie: seleniumCookies) {
			getDriver().manage().addCookie(cookie);
		}
	}
}
