package com.flipkart.test;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.extentReport.ExtentReport;

@Listeners(ExtentReport.class)
public class HomePageTest extends TestBase {
	
	@BeforeMethod 
	public void launchBrowser() throws IOException {
		System.out.println("Before Method");
		webDriverInitialization();
	}


	@Test
	public void verifyLoginButton() {
		System.out.println("test1");
		Assert.assertEquals(11, 11);
	}

	
	@Test
	public void verifyLoginPopUp() {
		System.out.println("test2");
		Assert.assertEquals(10, 11);
	}


	@AfterMethod 
	public void closeBrowser() { 
		System.out.println("After Method");
		driver.quit();
	}

}
