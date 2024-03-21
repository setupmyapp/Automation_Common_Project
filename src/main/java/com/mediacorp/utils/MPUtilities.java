package com.mediacorp.utils;

import java.io.BufferedWriter;
/***
 * Name of Author :- Harish  B R
 * Developed By :- Ifocus Automation Team
 * Organization Name :- Ifocus Systec
 * Date :- 15-02-2024
 * Function Description :-  mixpanel Utilities
 */
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class MPUtilities {

	public static Element cElement;
	public static String strResponse = "";
	public static String decodedString = "";
	public static String EventName = "Download";
	public static int count = 0;
	public static boolean actionFlag = false;
	private ExtentTest test;

	public MPUtilities(ExtentTest test) {
		this.test = test;
	}

	Utilities Utilities = new Utilities();

	// Open Charles Method
	public static void openCharles() throws IOException, InterruptedException {
		try {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				Runtime.getRuntime().exec("open /Applications/Charles.app");
			} else {
				Runtime.getRuntime().exec("Charles.exe");
			}
			System.out.println("Charles was Opened Sucessfully");
			Thread.sleep(20000);
			Web_Constants.localhost = InetAddress.getLocalHost();
			Web_Constants.ipAdress = Web_Constants.localhost.getHostAddress().trim();
			System.out.println("System IP Address : " + (Web_Constants.localhost.getHostAddress()).trim());
			Thread.sleep(20000);
			Runtime.getRuntime()
					.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/session/clear");
			Thread.sleep(2000);
			Runtime.getRuntime().exec(
					"curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/recording/start");
			System.out.println("Charles Recording was started Sucessfully");

			Thread.sleep(80000);
		} catch (Exception e) {
			System.out.println("Exception Occured while Opeing Charles: " + e.getMessage());
			System.err.println("[EXCEPTION] Failed Due to: " + e.getMessage());
		}

	}

	// To save and close charles sessoin saveandcloseCharles yet to be renamed to
	// saveAndCloseCharles
	public static void saveAndCloseCharles(String fileName) {
		Utilities Utilities = new Utilities();

		try {

			Utilities.createFolder(Web_Constants.REPORT_PATH_MAC + "Charleslogs/");

			DateFormat date = new SimpleDateFormat("ddmmyyHHMMss");
			Date date2 = new Date();
			String date1 = date.format(date2);
			Web_Constants.charlesName = fileName + date1 + ".xml";
			Web_Constants.charleslogsName = fileName + date1 + ".chls";

			Web_Constants.filePathxml = Web_Constants.REPORT_PATH_MAC + "Charleslogs/" + Web_Constants.charlesName;
			Web_Constants.filePathlogs = Web_Constants.REPORT_PATH_MAC + "Charleslogs/" + Web_Constants.charleslogsName;

			Web_Constants.ipAdress = Web_Constants.localhost.getHostAddress().trim();
			Thread.sleep(150000);
			Runtime.getRuntime().exec("curl -o " + Web_Constants.filePathxml + " -x http://" + Web_Constants.ipAdress
					+ ":8888 http://control.charles/session/export-xml");
			Thread.sleep(15000);
			Runtime.getRuntime().exec("curl -o " + Web_Constants.filePathlogs + " -x http://" + Web_Constants.ipAdress
					+ ":8888 http://control.charles/session/download");
			Thread.sleep(150000);
			Runtime.getRuntime()
					.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/quit");
			Thread.sleep(2000);

		} catch (Exception e) {
		}
	}

	public static void closeCharles() {
		try {
			Web_Constants.ipAdress = Web_Constants.localhost.getHostAddress().trim();
			Runtime.getRuntime()
					.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/quit");
		} catch (Exception e) {
		}
	}

	// Forming a list and returning a arraylist of all Mixpannel Events
	public List<Map<String, String>> getActualArraylistofMPEvents()
			throws ParserConfigurationException, SAXException, IOException, ParseException {
		ArrayList<String> eventsList = new ArrayList<String>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String ety = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(ety.getBytes());
				System.out.println("Charles Xml documet session:" + systemId);
				return new InputSource(bais);
			}
		};

		dBuilder.setEntityResolver(resolver);
		Document doc = null;
		try {
			doc = dBuilder.parse(Web_Constants.filePathxml);
		} catch (Exception e) {

			try {
				doc = dBuilder.parse(Web_Constants.filePathxml);
			} catch (SAXException e1) {

			} catch (IOException e1) {

			}

		}
		System.out.println("File Printing is: " + Web_Constants.filePathxml);
		doc.getDocumentElement().normalize();

		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");

		List<Map<String, String>> actualEventList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				if (eElement.getAttribute("path").contains("/track")
						&& eElement.getAttribute("host").equals("api-js.mixpanel.com")) {

					cElement = (Element) eElement.getElementsByTagName("body").item(0);

					try {
						strResponse = cElement.getTextContent();
						String split[] = strResponse.split("data=");
						strResponse = split[1].toString().trim();

						String decodedJsonString = java.net.URLDecoder.decode(strResponse, "UTF-8");
						eventsList.add(decodedJsonString);

					} catch (Exception e) {
						System.out.println("Not able to decode String");
					}

				} else {

				}

			}

		}

		for (String eventString : eventsList) {
			try {
				decodedString = eventString;
				if (decodedString.startsWith("[")) {
					decodedString = decodedString.substring(1, decodedString.length() - 1);
				}
				String[] decodedStringArray = decodedString.split("},");
				for (String decodedStringEvent : decodedStringArray) {

					if (!decodedStringEvent.endsWith("}}")) {
						decodedStringEvent = decodedStringEvent + "}";
					}
					JSONObject obj = new JSONObject(decodedStringEvent);
					String eventName = obj.getString("event");
					System.out.println("event:: " + eventName);

					Map<String, String> map = new LinkedHashMap<String, String>();

					map.put("event", eventName);
//					System.out.println("expectedMap.put(\"" + "event" + "\", \"" + eventName + "\");");
//					String osName = obj.getJSONObject("properties").getString("$os");
//					System.out.println("osName:: " + osName);
//					String browserName = obj.getJSONObject("properties").getString("$browser");
//					System.out.println("browserName:: " + browserName);

					JSONObject newobj = obj.getJSONObject("properties");
					Iterator iter = newobj.keys();
					while (iter.hasNext()) {
						String key = (String) iter.next();
						String value = newobj.get(key).toString();
						map.put(key, value);
//						System.out.println("expectedMap.put(\"" + key + "\", \"" + value + "\");");

					}
					actualEventList.add(map);

				}

			} catch (JSONException e) {
//					//System.out.println("Failed due to Exception: "+e.getMessage());
//					logScreenShot(driver, test, "");
			}
		}

		return actualEventList;

	}

	// To compare the Arraylist of all Event Maps
	public void compareArrayListOfEventMaps(List<Map<String, String>> actualEventList,
			List<Map<String, String>> expectedEventList) {
		String FirstPrimaryKey = "";
		// Looping through all the Expected Event List Maps
		for (int i = 0; i < actualEventList.size(); i++) {
			Set<Entry<String, String>> actualentrySet = actualEventList.get(i).entrySet();
			for (Entry<String, String> actualentry : actualentrySet) {
				{
					//System.out.println("ACTUALKEY= " + actualentry.getKey() + "--------" + "ACTUALVALUE= "+ actualentry.getValue());
					//Checking if the key of Actual Map is event or not

					if (actualentry.getKey().equals("event")) {

						FirstPrimaryKey = MPUtilities.getFirstPrimaryKey(actualentry.getValue());

						// Looping through all the Expected Event List Maps
						J: for (int j = 0; j < expectedEventList.size(); j++) {

							Set<Entry<String, String>> expectedentrySet = expectedEventList.get(j).entrySet();
							for (Entry<String, String> expectedentry : expectedentrySet) {
								//System.out.println("EXPECTEDKEY= " + expectedentry.getKey() + "--------"+ "EXPECTEDVALUE= " + expectedentry.getValue());
								//Checking if the key of Expected Map is event or not
								if (expectedentry.getKey().equals("event")) {

									for (Entry<String, String> firstprimaryexpectedentry : expectedentrySet) {

										if (firstprimaryexpectedentry.getKey().equals(FirstPrimaryKey)) {
											for (Entry<String, String> firstprimaryactualentry : actualentrySet) {
                                                //Here we are checking if the First Primary Key is same in Expected Map and Actual Map 
												if (expectedentry.getValue().equals(actualentry.getValue())
														&& firstprimaryexpectedentry.getValue()
																.equals(firstprimaryactualentry.getValue())) {
                                                    // Here we are Comparing Map at Position i with Map at Position j
													//if (actualEventList.get(i).equals(expectedEventList.get(j))) {

													if (areKeysAndValuesinMapsEqual(actualEventList.get(i),
															expectedEventList.get(j))) {
														//System.out.println("Actual Map: \t" + actualEventList.get(i));
														//System.out.println("Expected Map: \t" + expectedEventList.get(j));

														logStatusMP("PASS",
																"PASSED: Event Name: \t" + expectedentry.getValue());
														System.out.println("PASS");

														break J;
													} else {
														//System.out.println("Actual Map: \t" + actualEventList.get(i));
														//System.out.println("Expected Map: \t" + expectedEventList.get(j));
														logStatusMP("FAIL",
																"FAILED: Event Name: \t" + expectedentry.getValue());

														break J;
													}

												} else {
													continue;
												}

											}
										} else {
											continue;
										}
									}
								} else {
									continue;
								}
							}
						}
					} else {
						continue;
					}
				}
			}
		}
	}

	// This method is to compare two maps key and values and tells if the entire
	// event map is passed or not
	private boolean areKeysAndValuesinMapsEqual(Map<String, String> actMap, Map<String, String> expectMap) {
		boolean completeMapflag = true;
		try {

			for (String k : expectMap.keySet()) {
				if (k.equalsIgnoreCase("timeBefore") || k.equalsIgnoreCase("timeAfter"))
					continue; // skip these two keys
				String str = actMap.get(k);

				if (str != null) {

					if (str.equalsIgnoreCase(expectMap.get(k)) || expectMap.get(k).equalsIgnoreCase("random")) {
//						System.out.println("Expected Key and Value is:" + k + "=" + expectMap.get(k)
//								+ "\t :: \t Actual Key and Value is:" + k + "=" + actMap.get(k));
//					logStatusMP( "INFO", "Expected Key and Value is:" + k + "=" + expectMap.get(k)
//							+ "\t :: \t Actual Key and Value is:\t" + k + "=" + actMap.get(k));

						System.out.println(" MATCHING");
					} else {
						System.out.println("Expected Key and Value is:\t" + k + "=" + expectMap.get(k)
								+ "\t :: \t Actual Key and Value is:" + k + "=" + actMap.get(k));
						logStatusMP("ERROR/WOSS", "Expected Key and Value is:" + k + "=" + expectMap.get(k)
								+ "\t :: \t Actual Key and Value is:" + k + "=" + actMap.get(k));
						System.out.println(" NOT MATCHING");
						completeMapflag = false;
					}

				} else {
					logStatusMP("WARNING/WOSS", "Expected Key and Value is:" + k + "=" + expectMap.get(k)
							+ " The Key is not found in the actual Map ");
					completeMapflag = false;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured in the try block");
			logStatusMP("ERROR/WOSS", "Exception Occured: " + e);

			completeMapflag = false;
		}
		return completeMapflag;
	}

	// Utility that returns the First Primary key of any events. More events yet to
	// be added.
	public static String getFirstPrimaryKey(String eventName) {
		Map<String, String> firstPrimaryKeyMap = new HashMap<String, String>();

		firstPrimaryKeyMap.put("mewatch_app_launch", "mediacorp_product");
		firstPrimaryKeyMap.put("page_loads", "page_type");
		firstPrimaryKeyMap.put("login_successful", "login_type");
		firstPrimaryKeyMap.put("search_request", "search_keyword");
		firstPrimaryKeyMap.put("search_card_click", "search_result_category");
		firstPrimaryKeyMap.put("program_detail_page", "entry_point");
		firstPrimaryKeyMap.put("watch_program_trailer", "media_id");
		firstPrimaryKeyMap.put("program_tag_click", "tag_type");
		firstPrimaryKeyMap.put("program_episode_synopsis_click", "media_id");
		firstPrimaryKeyMap.put("click_to_watch", "entry_point");
		firstPrimaryKeyMap.put("video_start", "entry_point");
		firstPrimaryKeyMap.put("video_pause", "media_id");
		firstPrimaryKeyMap.put("video_resume", "media_id");
		firstPrimaryKeyMap.put("video_forward", "media_id");
		firstPrimaryKeyMap.put("video_backward", "media_id");
		firstPrimaryKeyMap.put("video_select_episode", "selected_season_id");
		firstPrimaryKeyMap.put("video_select_quality", "selected_video_quality");
		firstPrimaryKeyMap.put("video_seek", "media_id");
		firstPrimaryKeyMap.put("video_watch_completed", "media_id");
		firstPrimaryKeyMap.put("video exit", "media_id");
		firstPrimaryKeyMap.put("menu_click", "menu_option");

		return firstPrimaryKeyMap.get(eventName);
	}

	// Dummy Map Comparision from expected to actual set
	public void compareArrayListOfEventMapsReverse(List<Map<String, String>> expectedEventList,
			List<Map<String, String>> actualEventList) {
		String FirstPrimaryKey = "";
		// Looping through all the Expected Event List Maps
		for (int i = 0; i < expectedEventList.size(); i++) {
			Set<Entry<String, String>> expectedentrySet = expectedEventList.get(i).entrySet();
			for (Entry<String, String> expectedentry : expectedentrySet) {
				{
//					System.out.println("ACTUALKEY= " + actualentry.getKey() + "--------" + "ACTUALVALUE= "+ actualentry.getValue());
//					Checking if the key of Actual Map is event or not

					if (expectedentry.getKey().equals("event")) {

						FirstPrimaryKey = MPUtilities.getFirstPrimaryKey(expectedentry.getValue());

						// Looping through all the Expected Event List Maps
						J: for (int j = 0; j < actualEventList.size(); j++) {

							Set<Entry<String, String>> actualentrySet = actualEventList.get(j).entrySet();
							for (Entry<String, String> actualentry : actualentrySet) {
//								Checking if the key of Expected Map is event or not
								if (actualentry.getKey().equals("event")) {

									for (Entry<String, String> firstprimaryactentry : actualentrySet) {

										if (firstprimaryactentry.getKey().equals(FirstPrimaryKey)) {

											for (Entry<String, String> firstprimaryexpectentry : expectedentrySet) {
												//Here we are checking if the First Primary Key is same in Expected Map and Actual Map 
												if (actualentry.getValue().equals(expectedentry.getValue())
														&& firstprimaryactentry.getValue()
																.equals(firstprimaryexpectentry.getValue())) {
													// Here we are Comparing Map at Position i with Map at Position j

													if (areKeysAndValuesinMapsEqual(actualEventList.get(j),
															expectedEventList.get(i))) {
														logStatusMP("INFO",
																"Expected Map: " + expectedEventList.get(i));
														logStatusMP("INFO", "Actual Map: " + actualEventList.get(j));
														logStatusMP("PASS",
																"PASSED: Event Name: \t" + actualentry.getValue());
														System.out.println("PASS");

														break J;
													} else {
														logStatusMP("INFO",
																"Expected Map: " + expectedEventList.get(i));
														logStatusMP("INFO", "Actual Map: " + actualEventList.get(j));
														logStatusMP("FAIL",
																"FAILED: Event Name: \t" + actualentry.getValue());
														System.out.println("FAIL");

														break J;
													}

												} else {
													continue;
												}

											}
										} else {
											continue;
										}
									}
								} else {
									continue;
								}
							}
						}
					} else {
						continue;
					}
				}
			}
		}
	}

	public static List<Map<String, String>> usersList = new ArrayList<Map<String, String>>();
	public static String GotValue = "";

	public void compareArrayListOfEvents(List<Map<String, String>> expectedEventList,
			List<Map<String, String>> actualEventList) {
		String FirstPrimaryKey = "";
		long eventTimeBefore = 0;
		// Looping through all the Expected Event List Maps
		int counter = 0;
		for (int i = 0; i < expectedEventList.size(); i++) {

			Map<String, String> expectedentrySet = expectedEventList.get(i);

			String eventName = expectedentrySet.get("event");
			String primaryKey = MPUtilities.getFirstPrimaryKey(eventName);
			String primaryKeyValue = expectedentrySet.get(primaryKey);

			try {
				eventTimeBefore = Long.parseLong(expectedentrySet.get("timeBefore"));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				logStatusMP("EXCEPTION", "Failed Due to: " + e1.getMessage());
			}
			long eventTimeAfter = Long.parseLong(expectedentrySet.get("timeAfter"));
			boolean validTime = false;
			boolean evetnNameMatches = false;
			boolean eventPrimaryKeyMatches = false;
			boolean eventAbsent = false;
			Map<String, String> actualentrySet = new HashMap<String, String>();
			for (int j = 0; j < actualEventList.size() && !eventAbsent; j++) {

				actualentrySet = actualEventList.get(j);
				long actualTime = Double.valueOf(actualentrySet.get("time")).longValue();

				if (actualTime > eventTimeBefore && actualTime < eventTimeAfter) {
					validTime = true;
				} else {
					validTime = false; // to skip the loop, event not triggered

				}

				String actualEventName;
				if (validTime) {
					try {
						actualEventName = actualentrySet.get("event");
						if (actualEventName.equalsIgnoreCase(eventName)) {
							evetnNameMatches = true;
						}
					} catch (Exception e) {
						// to continue for next list
					}
					if (evetnNameMatches) {
						String actualprimaryKeyValue = new String();
						try {
							actualprimaryKeyValue = actualentrySet.get(primaryKey).trim();
						} catch (Exception e) {
							logStatusMP("INFO", "primary key not there " + primaryKey);
							eventPrimaryKeyMatches = false;

						}
						if (actualprimaryKeyValue != null) {
							if ((actualprimaryKeyValue.equalsIgnoreCase(primaryKeyValue))) {
								eventPrimaryKeyMatches = true;
								break;
								// counter = j+1; // beforetime will take care of skipping
							}
						}
					}
				}
			}
			if (!evetnNameMatches || !eventPrimaryKeyMatches) {
				logStatusMP("INFO", "Expected Map: " + expectedentrySet.toString());
				logStatusMP("INFO", "Actual Map: Not present ");
				logStatusMP("FAIL", "FAILED: Event Name: " + eventName);

			} else if (eventPrimaryKeyMatches && evetnNameMatches) {

				if (areKeysAndValuesinMapsEqual(actualentrySet, expectedentrySet)) {
					logStatusMP("INFO", "Expected Map: " + expectedentrySet.toString());
					logStatusMP("INFO", "Actual Map: " + actualentrySet.toString());
					logStatusMP("PASS", "PASSED: Event Name: \t" + eventName);

					System.out.println("PASS");
				} else {
					logStatusMP("INFO", "Expected Map: " + expectedentrySet.toString());
					logStatusMP("INFO", "Actual Map: " + actualentrySet.toString());
					logStatusMP("FAIL", "FAILED: Event Name: \t" + eventName);

					System.out.println("FAIL");
				}
			}
		}
	}

	public static void saveMap(List<Map<String, String>> list, String type) {

		Utilities Utilities = new Utilities();
		File file;

		if (Web_Constants.OS.equalsIgnoreCase("mac")) {
			file = new File(System.getProperty("user.dir") + "/Charleslogs/" + type + "_" + Utilities.getTimeStamp_sec()
					+ ".lst");
		} else {
//			file = new File("C:\\Charleslogs\\" + type + "_" + Utilities.getTimeStamp_sec() + ".lst");
			file = new File(System.getProperty("user.dir") + "/Reports/Charleslogs/" + type + "_"
					+ Utilities.getTimeStamp_sec() + ".lst");
		}
		BufferedWriter bf = null;

		try {

			// create new BufferedWriter for the output file
			bf = new BufferedWriter(new FileWriter(file));

			// iterate map entries
			for (int i = 0; i < list.size(); i++) {

				// put key and value separated by a colon
				bf.write(list.get(i).toString());

				// new line
				bf.newLine();
			}

			bf.flush();

		} catch (Exception e) {
			System.err.println("[EXCEPTION] Failed Due to: " + e.getMessage());
		} finally {

			try {
				// always close the writer
				bf.close();
			} catch (Exception e) {
				System.err.println("[EXCEPTION] " + ": " + e.getMessage());
			}
		}
	}

	public static String GetAppVersionfromCharles() throws SAXException, IOException, ParserConfigurationException {
		String app_version = "";
		ArrayList<String> base64encodedString = new ArrayList<String>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Utilities Utilities = new Utilities();
		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String ety = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(ety.getBytes());
				return new InputSource(bais);
			}
		};

		dBuilder.setEntityResolver(resolver);
		Document doc = dBuilder.parse(Web_Constants.filePathxml);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("transaction");

		List<Map<String, String>> actualEventList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) (nodeList.item(i));
				if (eElement.getAttribute("path").contains("/track")
						&& eElement.getAttribute("host").equals("api-js.mixpanel.com")) {
					cElement = (Element) eElement.getElementsByTagName("body").item(0);
					try {
						strResponse = cElement.getTextContent();
						String split[] = strResponse.split("data=");
						strResponse = split[1].toString().trim();
						strResponse = Utilities.replaceHexadeciValuetoUnichar(strResponse);
						base64encodedString.add(strResponse);
					} catch (Exception e) {
						System.out.println("Not able to decode String");
					}
				} else {
				}
			}
		}

		L: for (String base64String : base64encodedString) {
			byte[] byteArray = Base64.decodeBase64(base64String.getBytes());
			decodedString = new String(byteArray).toString().trim();
			JSONObject obj = new JSONObject(decodedString);
			String eventName = obj.getString("event");
			JSONObject newobj = obj.getJSONObject("properties");
			Iterator iter = newobj.keys();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = newobj.get(key).toString();
				if (key.equals("appversion")) {
					app_version = value;
					break L;
				}
			}
		}
		return app_version;
	}

	public void logStatusMP(String status, String Message) {

		switch (status.toLowerCase()) {
		case "info":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.info(MarkupHelper.createLabel(Message, ExtentColor.WHITE));
			break;
		case "warning":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.warning(MarkupHelper.createLabel(Message, ExtentColor.RED));
			break;
		case "error":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.warning(MarkupHelper.createLabel(Message, ExtentColor.RED));

			break;
		case "pass":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.pass(MarkupHelper.createLabel(Message, ExtentColor.GREEN));
			break;
		case "fail":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.fail(MarkupHelper.createLabel(Message, ExtentColor.RED));
			break;
		case "skip":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.skip(MarkupHelper.createLabel(Message, ExtentColor.PINK));
			break;
		default:
			test.warning(Message);
			break;
		}

	}

	public static void clearCharles() {
		try {
			Runtime.getRuntime()
					.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/session/clear");
			Thread.sleep(5000);

		} catch (Exception e) {
		}
	}

}
