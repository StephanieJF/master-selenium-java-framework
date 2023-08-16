package org.selenium.pom.base;

import java.util.List;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.restassured.http.Cookies;


public class BaseTest {
	
	private ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();
	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	
	private void setDriverManager(DriverManagerAbstract driverManager) {
		this.driverManager.set(driverManager);
	}
	
	protected DriverManagerAbstract getDriverManager() {
		return this.driverManager.get();
	}
	
	
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
//		setDriver(new DriverManagerOriginal().initializeDriver(browser));
//		setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
		setDriverManager(DriverManagerFactoryAbstract.
				getManager(DriverType.valueOf(browser)));
		setDriver(getDriverManager().getDriver());
		System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + 
				"DRIVER = " + getDriver());
	}
	
	
	@AfterMethod
	public void quitDriver() {
//		getDriver().quit();
		getDriverManager().getDriver().quit();
		System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
	}
	
	public void injectCookiesToBrowser(Cookies cookies) {
		List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
		for(Cookie cookie: seleniumCookies) {
			getDriver().manage().addCookie(cookie);
		}
	}
}
