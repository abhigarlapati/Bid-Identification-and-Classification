package com.example.bidClassification.dto;

import jakarta.persistence.Column;

public class EmailDTO {
	
	private String subject;

    private String body;

    private String sender;
    
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
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
    
    

}
