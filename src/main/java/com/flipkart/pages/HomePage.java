package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement LoginButton;
	
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean VerifyLoginButton() {
		String loginText=LoginButton.getText();
		if(loginText.equalsIgnoreCase("Login")) {
			return true;
		}else 
			return false;
	}
	
	
	
}
