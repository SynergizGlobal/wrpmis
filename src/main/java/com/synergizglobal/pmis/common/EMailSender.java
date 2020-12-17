package com.synergizglobal.pmis.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class EMailSender {    
	
	private static Logger logger = Logger.getLogger(EMailSender.class);
	
	/*private static String mailId = "syntrack@synergizglobal.com";
	private static String pass = "Synergiz@2018";*/
	
	/************** ZIMBRA Mail Server Credentials**************************************/
	private static String mailId = "support_pmis@mrvc.gov.in";
	private static String pass = "Mrvc#pmis1";
	
	public static Session getSession() {
		Properties props = new Properties();
		
		/************** ZIMBRA Server Starts**************************************/
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", "mrvcmail.rcil.gov.in");
		props.put("mail.smtp.port", "587");
		/************** ZIMBRA Server ends*************************************/
		
		/************** GMAIL Server Starts**************************************/
		/*props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");*/
		/************** GMAIL Server ends*************************************/
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailId, pass);
			}
		  });
		return session;
	}
	
	public static boolean send(String toAddress, String subject, String body) {
		boolean isSend = false;		
		try {
			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(mailId));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			logger.info("Email sent successfully");
			isSend = true;
		} catch (MessagingException e) {
			e.printStackTrace();
			logger.error("Exception occured while sending an email: "+e.getMessage());			
		}
		return isSend;
	}
	public static File convert(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	
}
