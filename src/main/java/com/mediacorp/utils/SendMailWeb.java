package com.mediacorp.utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendMailWeb {

	public void reportMail() {

		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication("mewatch@ifocussystec.com", "ifocus@12345");

					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("mewatch@ifocussystec.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mewatchqa@ifocussystec.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("mc.automation@ifocussystec.com"));

			// Add the subject link
			message.setSubject("MeWatch Automation_" + Web_Constants.ENV + "_" + Web_Constants.PROJECT + "_"
					+ Web_Constants.browser);

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			String text = "<html><b>" + "Hi Team,<br><br>" + "Please find the below Report details:</b>" + "<br><br>"
					+ "<b>ENVIRONMENT: </b>" + Web_Constants.ENV + "<br><br>" + "<b>BROWSER: </b>"
					+ Web_Constants.browser + "<br><br>" + "<b>PLATFORM: </b>" + Web_Constants.PLATFORM_NAME
					+ "<br><br>" + "<b>OS: </b>" + Web_Constants.OS

					+ "<br><br><b>Regards</b>,<br>Automation Team";

			// Set the body of email
			messageBodyPart1.setText(text);
			messageBodyPart1.setContent(text, "text/html");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = Web_Constants.ExtentReportPath;

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filename);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}
}