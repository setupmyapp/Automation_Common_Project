package com.mediacorp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RepositoryUtils {

	Document repFile;

	String page;
	String repositoryPath;
	public RepositoryUtils(String page){
		this.page = page;

		if ((Web_Constants.ENV.equalsIgnoreCase("preprod"))&&(Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp"))){
			repositoryPath = System.getProperty("user.dir") + "/preprod_ObjectRepository/" + page + ".xml";
		}else {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				repositoryPath = System.getProperty("user.dir") + "/ObjectRepository/" + page + ".xml";
			}else {
				repositoryPath = System.getProperty("user.dir") + "\\ObjectRepository\\" + page + ".xml";
			}
		}
	}

	private String[] getObjectInfo(String elementName) {
		
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(repositoryPath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			repFile = db.parse(fis);
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		
		String[] objectInfo = new String[2];
		
		NodeList platformElementCol = repFile.getElementsByTagName(Web_Constants.PROJECT);
		int platformNodeLength = platformElementCol.getLength();
		
		if (platformNodeLength>0) {
			for (int i = 0; i < platformNodeLength; i++) {

				Element platformElement = (Element) platformElementCol.item(i);
				String tagName = platformElement.getTagName();
				if (tagName.equalsIgnoreCase(Web_Constants.PROJECT)) {
					NodeList elemCol = platformElement.getElementsByTagName(elementName);
					int nodeLength = elemCol.getLength();
					if (nodeLength == 1) {

						Element element = (Element) elemCol.item(0);
						boolean isLocatorAttributeFound = element.hasAttribute("locator");
						boolean isValueAttributeFound = element.hasAttribute("value");
						if (isLocatorAttributeFound && isValueAttributeFound) {
							objectInfo[0] = element.getAttribute("locator");
							objectInfo[1] = element.getAttribute("value");
						} else {
//							Assert.assertTrue(false, "element : " + elementName
//									+ " does not have either locator attribute or value attribute in object repository.");
						}

					} else {
//						Assert.assertFalse(true, "either '" + elementName
//								+ "'either not found in the repository or more than one entry is found.");
					}
					break;
				}
			} 
		}else {
//			Assert.assertFalse(true, "Expected Platform tag '" + Web_Constants.PROJECT
//					+ "'not found in the repository");
		}
		return objectInfo;
	}

	public By getBy(String elementName) {
		By by = null;
		String[] objInfo = getObjectInfo(elementName);

		try {
			switch (objInfo[0].toString()) {
			case "id":
				by = By.id(objInfo[1]);
				break;
			case "name":
				by = By.name(objInfo[1]);
				break;

			case "linktext":
				by = By.linkText(objInfo[1]);
				break;

			case "partiallinktext":
				by = By.partialLinkText(objInfo[1]);
				break;

			case "xpath":
				by = By.xpath(objInfo[1]);
				break;

			case "cssselector":
				by = By.cssSelector(objInfo[1]);
				break;

			case "classname":
				by = By.className(objInfo[1]);
				break;

			case "tagname":
				by = By.tagName(objInfo[1]);
				break;

			default:
				Assert.assertTrue(false,
						"Locator : " + objInfo[0] + " is invalid for the element : " + elementName + " in the repository");
				break;
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}

		return by;
	}

}
