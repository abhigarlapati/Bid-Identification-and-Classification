package com.example.bidClassification.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bidClassification.dto.BidStatusCountDTO;
import com.example.bidClassification.service.BidService;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	BidService bidService;
	
	@GetMapping("/bid-status")
	public List<BidStatusCountDTO> getBidsCountByStatus() {
	    return bidService.countBidsByStatus();
	}
	

}
