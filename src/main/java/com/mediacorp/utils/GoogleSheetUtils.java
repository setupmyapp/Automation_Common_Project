package com.mediacorp.utils;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheetUtils {

	public String getBuildLink() {
		
		String BuildLink = "";
		try {
			
			authorize();

			String[][] ar = getData("1quceQVJJQaSJRVyX93_f4GLxrY_H9PiWiQB3CIs-5qE", "Form Responses 1", "A:Z");
			
			
			
			int colomnIndex= 0;
			System.out.println( ar[0].length);
			for (int i = 0; i < ar[0].length; i++) {
				String expectedColomn = ar[0][i].toString();
				System.out.println(expectedColomn.trim().replaceAll(" ", ""));
				if (expectedColomn.equalsIgnoreCase("Build_Link (only for apps(except Prod))")) {
					colomnIndex=i;
					break;
				}
			}
			
			for (int i = 0; i < ar.length; i++) {
				String BuildLinkCell = ar[i][colomnIndex].toString();
				if (BuildLinkCell!="") {
					BuildLink=BuildLinkCell;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("App Build Link:-"+BuildLink);
		return BuildLink;
	}

	private Credential authorize() throws Exception {
		String credentialLocation = System.getProperty("user.dir") + "/client_secret.json";

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(),
				new FileReader(credentialLocation));

		List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

		GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
				GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
				scopes).setDataStoreFactory(new FileDataStoreFactory(new File(System.getProperty("user.dir"))))
						.setAccessType("offline").build();

		return new AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, new LocalServerReceiver())
				.authorize("user");
	}

	public String[][] getData(String spreadSheetId, String sheetName, String rangeDataToRead) throws Exception {
		Sheets sheet = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
				authorize());

		List<List<Object>> data = sheet.spreadsheets().values().get(spreadSheetId, sheetName + "!" + rangeDataToRead)
				.execute().getValues();

		return convertToArray(data);
	}

	private String[][] convertToArray(List<List<Object>> data) {
		String[][] array = new String[data.size()][];

		int i = 0;
		for (List<Object> row : data) {
			array[i++] = row.toArray(new String[row.size()]);
		}
		return array;
	}

	public void updateData(String spreadSheetId, String sheetName, String cellLocation, String newValue)
			throws Exception {

		if (cellLocation.contains(":")) {
			throw new Exception(String
					.format("Restricting update to single cell only. You are trying to update cells %s", cellLocation));
		}

		Sheets sheets = new Sheets(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
				authorize());

		ValueRange updateValue = new ValueRange().setValues(Arrays.asList(Arrays.asList(newValue)));

		sheets.spreadsheets().values().update(spreadSheetId, sheetName + "!" + cellLocation, updateValue)
				.setValueInputOption("RAW").execute();
	}

}
