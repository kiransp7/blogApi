package com.app.helper;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.app.constants.AppConstants;

@Component
public class GmailHelperClass {

	@Async("async-executor")
	public void sendEmail(String userName, String email, String password){
		
		System.out.println(".................. Mail Method Start ......................");
		System.out.println();
		
		String message = "Dear "+ userName +",\r\n"
				+ "\nWelcome to Blog Application\n"
				+ "\r\n"
				+ "We are pleased to inform you that your account has been successfully created !\r\n"
				+ "\r\n"
				+ "Your login credentials are as follows:\r\n"
				+ "\r\n"
				+ "Username: "+ email +"\r\n"
				+ "Password: "+ password +"\r\n"
				+ "For security reasons, we recommend that you do not share sensitive information over unsecured channels.\r\n"
				+ "\r\n"
				+ "If you have any questions or need further assistance, feel free to contact our support team at bikkaditblog@gmail.com\r\n"
				+ "\r\n"
				+ "Thank you for choosing Blog App !\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ userName +"\r\n"
				+ "www.blogapp.com";
				
		String filePath = AppConstants.FILE_PATH;
		
		String subject = "BlogAPI : Account Created Sucessfully";
		String to = email.toLowerCase();
		String from = AppConstants.OFFICIAL_MAIL_ID;
		
		// Email Configurations 
		
		String host = "smtp.gmail.com";
		
		//get System properties
		
		Properties properties = System.getProperties();
		
		//setting important information to properties object
		
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//Step 1 : Get Session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bikkaditblog@gmail.com","wgxtdnhswvvvawus" );
			}
			
		});

		
		//Step 2 : Compose Message
		MimeMessage msg = new MimeMessage(session);
		
		try {
			//from Email
			msg.setFrom(from);
			
			//adding Recipient
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding Subject to Message
			msg.setSubject(subject);
			
			//adding text to Message
			msg.setText(message);
			
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			
			//text
			MimeBodyPart textMime = new MimeBodyPart();
			textMime.setText(message);
			
			try {
			//image
			MimeBodyPart fileMime = new MimeBodyPart();
			File file = new File(filePath);
			fileMime.attachFile(file);
			
			
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			msg.setContent(mimeMultipart);
			
			//Step 3 : Send Message With Attachment using Transport Class
			Transport.send(msg);
			
		} catch (MessagingException e ) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Mail Send Sucessfully..............");
		
	}
	
	
	
	
	
	
	
	
	
	

}
