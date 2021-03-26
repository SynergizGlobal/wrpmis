package com.synergizglobal.pmis.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.Issue;

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
	
	
	public void sendEmailWithAlerts(Mail mail, List<Alerts> alerts) throws Exception {
		try {
			  MimeMessage message = new MimeMessage( getSession() );
			  Multipart multipart = new MimeMultipart( "alternative" );

			  VelocityEngine velocityEngine = new VelocityEngine();
			  Properties p = new Properties();
			  //p.setProperty("resource.loader", "class");
			  //p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			  
			  p.setProperty("resource.loader", "class");
			  p.setProperty("class.resource.loader.description", "Velocity Classpath Resource Loader");
			  p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			  
			  p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
			  
			  //p.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS,    NullLogChute.class.getName());
			  try {
				  velocityEngine.init( p );    
			  }catch (Exception e) {
				  throw new Exception(e);
			  }
			     
			 			  
			  Template template = velocityEngine.getTemplate("templates/"+ mail.getTemplateName());
				
			  VelocityContext velocityContext = new VelocityContext();
			  velocityContext.put("alerts", alerts);
			  
			  StringWriter stringWriter = new StringWriter();
			  
			  template.merge(velocityContext, stringWriter);


			  MimeBodyPart htmlPart = new MimeBodyPart();
			  htmlPart.setContent( stringWriter.toString(), "text/html; charset=utf-8" );

			  //multipart.addBodyPart( htmlPart );

			  
			  //Multipart multiPart = new MimeMultipart();
			  multipart.addBodyPart(htmlPart);
			  
			  
			  message.setContent( multipart );
		    
			  message.setFrom(new InternetAddress(mailId));
			  
			  
			  ArrayList<String> recipientsArray = new ArrayList<String>();
			  StringTokenizer stringTokenizer = new StringTokenizer(mail.getMailTo(), ",");
			 
			  while (stringTokenizer.hasMoreTokens()) {
				 recipientsArray.add(stringTokenizer.nextToken());
			  }
			  int sizeTo = recipientsArray.size();
			  InternetAddress[] addressTo = new InternetAddress[sizeTo];
			  for (int i = 0; i < sizeTo; i++) {
				 addressTo[i] = new InternetAddress(recipientsArray.get(i).toString());
			  }	 
			  message.setRecipients(Message.RecipientType.TO, addressTo);
				 
			  //message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getMailTo()));
			  message.setSubject(mail.getMailSubject());
			  
			  Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	public void sendEmailWithIssueAlert(Mail mail, Issue iObj) throws Exception {
		try {
			  MimeMessage message = new MimeMessage( getSession() );
			  Multipart multipart = new MimeMultipart( "alternative" );

			  VelocityEngine velocityEngine = new VelocityEngine();
			  Properties p = new Properties();
			  //p.setProperty("resource.loader", "class");
			  //p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			  
			  p.setProperty("resource.loader", "class");
			  p.setProperty("class.resource.loader.description", "Velocity Classpath Resource Loader");
			  p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			  
			  p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
			  
			  //p.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS,    NullLogChute.class.getName());
			  try {
				  velocityEngine.init( p );    
			  }catch (Exception e) {
				  throw new Exception(e);
			  }
			     
			 			  
			  Template template = velocityEngine.getTemplate("templates/"+ mail.getTemplateName());
				
			  VelocityContext velocityContext = new VelocityContext();
			  velocityContext.put("alert", iObj);
			  
			  StringWriter stringWriter = new StringWriter();
			  
			  template.merge(velocityContext, stringWriter);


			  MimeBodyPart htmlPart = new MimeBodyPart();
			  htmlPart.setContent( stringWriter.toString(), "text/html; charset=utf-8" );

			  //multipart.addBodyPart( htmlPart );

			  
			  //Multipart multiPart = new MimeMultipart();
			  multipart.addBodyPart(htmlPart);
			  
			  
			  message.setContent( multipart );
		    
			  message.setFrom(new InternetAddress(mailId));
			  
			  
			  ArrayList<String> recipientsArray = new ArrayList<String>();
			  StringTokenizer stringTokenizer = new StringTokenizer(mail.getMailTo(), ",");
			 
			  while (stringTokenizer.hasMoreTokens()) {
				 recipientsArray.add(stringTokenizer.nextToken());
			  }
			  int sizeTo = recipientsArray.size();
			  InternetAddress[] addressTo = new InternetAddress[sizeTo];
			  for (int i = 0; i < sizeTo; i++) {
				 addressTo[i] = new InternetAddress(recipientsArray.get(i).toString());
			  }	 
			  message.setRecipients(Message.RecipientType.TO, addressTo);
			  
			  /*********************************************************************/
			  
			  ArrayList<String> ccArray = new ArrayList<String>();
			  StringTokenizer stringTokenizerCc = new StringTokenizer(mail.getMailCc(), ",");
			 
			  while (stringTokenizerCc.hasMoreTokens()) {
				  ccArray.add(stringTokenizerCc.nextToken());
			  }
			  int sizeCc = ccArray.size();
			  InternetAddress[] addressCc = new InternetAddress[sizeCc];
			  for (int i = 0; i < sizeCc; i++) {
				 addressCc[i] = new InternetAddress(ccArray.get(i).toString());
			  }	 
			  message.setRecipients(Message.RecipientType.CC, addressCc);
			  
				 
			  //message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getMailTo()));
			  message.setSubject(mail.getMailSubject());
			  
			  Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
}
