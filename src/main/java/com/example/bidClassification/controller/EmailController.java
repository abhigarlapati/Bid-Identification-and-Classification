package com.example.bidClassification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bidClassification.dto.EmailDTO;
import com.example.bidClassification.model.Email;
import com.example.bidClassification.service.EmailService;

@RestController
@RequestMapping("/processemail")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	
	@PostMapping
    public ResponseEntity<Email> createEmail(@RequestBody EmailDTO email) {
        Email savedEmail = emailService.processIncomingEmail(email);
        return ResponseEntity.ok(savedEmail);
    }
	
	@GetMapping("/search/emails")
	public List<Email> searchEmails(
	    @RequestParam(required = false) String subject,
	    @RequestParam(required = false) String sender,
	    @RequestParam(required = false) String body
	) {
	    return emailService.searchEmails(subject, sender, body);
	}

}
