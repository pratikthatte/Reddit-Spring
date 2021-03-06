package com.example.reddit.service;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


import com.example.reddit.model.NotificationEmail;

@Service
public class MailService {

	private static final Logger log = Logger.getLogger(MailService.class);
	
	private final JavaMailSender javaMailSender;
	private final MailContext mailContext;
	
	
	
	public MailService(JavaMailSender javaMailSender, MailContext mailContext) {
		this.javaMailSender = javaMailSender;
		this.mailContext = mailContext;
	}



	public void sendEmail(NotificationEmail notificationEmail) {
		
		log.info("Entering sendEmail method for user :  " + notificationEmail.getRecepient());
		MimeMessagePreparator messagePreparator = mimeMessage -> {
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom("springreddit@email.com");
		mimeMessageHelper.setTo(notificationEmail.getRecepient());
		mimeMessageHelper.setSubject(notificationEmail.getSubject());
		mimeMessageHelper.setText(mailContext.build(notificationEmail.getBody()));
		};
		
		
		try
		{
			javaMailSender.send(messagePreparator);
			log.info("Notification Email sent");
		}
		catch(Exception e)
		{
			log.info("Exception occured on sending notification email - ",e);
		}
	}

}
