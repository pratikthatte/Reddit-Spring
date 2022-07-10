package com.example.reddit.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContext {

	private final TemplateEngine templateEngine;

	public MailContext(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
	public String build(String message) {
		
		Context context = new Context();
		context.setVariable("message", message);
		
		return templateEngine.process("MailTemplate", context);
	}
	
}
