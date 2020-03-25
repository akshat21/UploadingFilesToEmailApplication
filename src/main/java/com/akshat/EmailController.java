package com.akshat;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class EmailController {
	
	@Autowired
	public SendEmailService emailService;
	
	@PostMapping("/email")
	public String sendEmail(@RequestBody EmailMessage emailMessage) throws AddressException, MessagingException ,IOException{
		emailService.sendEmail(emailMessage);
		return "email sent";
	}

}
