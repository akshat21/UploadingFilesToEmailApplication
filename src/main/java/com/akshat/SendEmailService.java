package com.akshat;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

	//passed as vm variable
	@Value("${username}")
	private String username;

	//passed as vm variable
	@Value("${password}")
	private String password;

	public void sendEmail(EmailMessage emailMessage) throws AddressException, MessagingException, IOException {
		Properties pro = new Properties();
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(pro, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username, false));

		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailMessage.getTo_address()));
		msg.setSubject(emailMessage.getSubject());
		msg.setContent(emailMessage.getBody(),"text/html");
		msg.setSentDate(new Date());
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("email Body Content","text/html");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();
		
		attachPart.attachFile("/Users/akshatsharma/Desktop/FileuploadThree.txt");
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		
		Transport.send(msg);

	}

}
