package com.flipkart.runner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class Runner {

	public static void main(String[] args) {
		XmlSuite suite = new XmlSuite();
		suite.setName("Flipkart Automation Suite");
		suite.setParallel("classes");
		suite.setThreadCount(2);
		
		XmlTest test = new XmlTest(suite);
		test.setName("Flipkart Demo Automation");
		
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.flipkart.test.HomePageTest"));
		classes.add(new XmlClass("com.flipkart.test.LogInPageTest"));
		test.setXmlClasses(classes);
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		
		TestNG ng = new TestNG();
		ng.setXmlSuites(suites);
		
		ng.run();

		
	}

}
