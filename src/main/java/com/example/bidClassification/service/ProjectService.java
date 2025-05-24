package com.example.bidClassification.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.Project;
import com.example.bidClassification.repository.ProjectRepository;

import jakarta.persistence.OneToMany;

public class ProjectService {
	
	@Autowired
	BidService bidService;
	
	@Autowired
	ProjectRepository projectRepository;
	
	
	

}
