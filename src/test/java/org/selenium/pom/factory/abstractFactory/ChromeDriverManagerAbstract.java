package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract{


	@Override
	protected void startDriver() {
		WebDriverManager.chromedriver().cachePath("Drivers").setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

}
