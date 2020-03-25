package com.akshat;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/send")
public class EmailController {
	
	@Autowired
	public SendEmailService emailService;
	
	@PostMapping("/email")
	public String sendEmail(@RequestParam("toAddress") String toAddress ,
			@RequestParam("body") String body,
			@RequestParam("subject") String subject
			,@RequestParam("manyfiles") MultipartFile[] files) throws AddressException, MessagingException ,IOException{
		
		
		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setBody(body);
		emailMessage.setSubject(subject);
		emailMessage.setTo_address(toAddress);
		
		emailService.sendEmail(emailMessage,files);
		return "email sent";
	}

}
