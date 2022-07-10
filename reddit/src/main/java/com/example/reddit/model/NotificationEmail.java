package com.example.reddit.model;

import lombok.Data;


@Data

public class NotificationEmail {
	private String subject;
	private String body;
	private String recepient;
	
	
	public NotificationEmail(String subject, String body, String recepient) {
		this.subject = subject;
		this.body = body;
		this.recepient = recepient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getRecepient() {
		return recepient;
	}
	public void setRecepient(String recepient) {
		this.recepient = recepient;
	}
	
	
}
