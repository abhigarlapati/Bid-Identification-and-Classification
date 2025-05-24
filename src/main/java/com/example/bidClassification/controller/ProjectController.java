package com.example.bidClassification.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bidClassification.dto.BidDTO;
import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.Project;
import com.example.bidClassification.repository.ProjectRepository;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepository;
	
	
	@GetMapping("/search-bids")
	public ResponseEntity<List<BidDTO>> getBidsByPartialProjectName(@RequestParam String name) {
	    List<Project> projects = projectRepository.findByProjectNameContaining(name);

	    if (projects.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    List<BidDTO> bidDTOs = projects.stream()
	        .flatMap(p -> p.getBids().stream())
	        .map(BidDTO::new)
	        .toList();

	    return ResponseEntity.ok(bidDTOs);
	}
	

	
}
