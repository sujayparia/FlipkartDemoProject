package com.flipkart.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;

public class LogInPageTest extends TestBase {
	
	@BeforeMethod 
	public void launchBrowser() throws IOException {
		System.out.println("Before Method");
		webDriverInitialization();
	}
	
	@Test
	public void verifyUserName() {
		System.out.println("verifyUserName");
		Assert.assertEquals(11, 11);
	}
	
	@Test
	public void verifyPassword() {
		System.out.println("verifyPassword");
		Assert.assertEquals(10, 11);
	}
	
	@AfterMethod 
	public void closeBrowser() { 
		System.out.println("After Method");
		driver.quit();
	}

}
