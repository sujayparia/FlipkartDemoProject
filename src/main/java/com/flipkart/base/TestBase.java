package com.flipkart.base;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestBase {

	public static WebDriver driver;

	public static Properties prop;


	public static void webDriverInitialization() throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		prop = new Properties();
		prop.load(fis);
		String Browser = prop.getProperty("BROWSER");
		String Url = prop.getProperty("URL");


		if(Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\flipkart\\browserDriver\\chromedriver.exe");
			driver=new ChromeDriver();
		}else if (Browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\flipkart\\browserDriver\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		driver.get(Url);

	}


}
