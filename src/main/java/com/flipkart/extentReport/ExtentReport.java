package com.flipkart.extentReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.flipkart.base.TestBase;


public class ExtentReport extends TestBase implements ITestListener{

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public String folder = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());


	public void reportSetUp() throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		prop = new Properties();
		prop.load(fis);

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReport\\"+"\\"+folder+"\\"+"ExtentReport.html");
		htmlReporter.config().setReportName(prop.getProperty("REPORTNAME"));
		htmlReporter.config().setDocumentTitle(prop.getProperty("REPORTTITLE"));
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", prop.getProperty("OS"));
		extent.setSystemInfo("AUTHOR", prop.getProperty("AUTHOR"));
		extent.setSystemInfo("BROWSER", prop.getProperty("BROWSER"));

	}

	public void reportEnd() {
		extent.flush();
	}

	public String takeScreenshot() throws IOException {
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot screenshot = (TakesScreenshot) driver;

		File source = screenshot.getScreenshotAs(OutputType.FILE);
		//String destinationPath = System.getProperty("user.dir")+"\\Screenshots\\"+date+".png";
		String destinationPath = System.getProperty("user.dir")+"\\ExtentReport\\"+"\\"+folder+"\\"+date+".png";
		File destination = new File(destinationPath);
		FileUtils.copyFile(source, destination);
		return destinationPath;
	}

	public void sendEmailReport() throws EmailException {

		String date = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
	

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("gymvshape@gmail.com", "sujay16041990@"));
		email.setSSLOnConnect(true);
		email.addTo("sujayparia@gmail.com", "sujay paria");
		email.addTo("gymvshape@gmail.com", "vshape gym");
		email.addTo("sohinitune@gmail.com", "sohini");
		email.setFrom("gymvshape@gmail.com", "Me");
		email.setSubject("Automation Test Report :: DateTime "+date);
		email.setMsg("Hi All, Please find attached the Automation Test Report");


		File directoryPath = new File(System.getProperty("user.dir")+"\\ExtentReport\\"+"\\"+folder);
		
		//List of all files and directories
		String contents[] = directoryPath.list();
		
		for(int i=0; i<contents.length; i++) {
			System.out.println(contents[i]);
			
			// Create the attachment
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(System.getProperty("user.dir")+"\\ExtentReport\\"+"\\"+folder+"\\"+contents[i]);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription(contents[i]);
			attachment.setName(contents[i]);
			
			// add the attachment
			email.attach(attachment);
		}

		// send the email
		email.send();
	}




	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,"Test case passed");

		try {
			test.addScreenCaptureFromPath(takeScreenshot());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL,"Test case failed");

		try {
			test.addScreenCaptureFromPath(takeScreenshot());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,"Test case skipped");

		try {
			test.addScreenCaptureFromPath(takeScreenshot());
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		try {
			reportSetUp();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onFinish(ITestContext context) {
		reportEnd();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		try { 
			sendEmailReport(); 
		} catch (EmailException e) { 
			e.printStackTrace(); }

	}


}
