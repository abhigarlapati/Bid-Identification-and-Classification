package com.example.bidClassification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.bidClassification.dto.BidDTO;
import com.example.bidClassification.service.BidService;

@RestController
@RequestMapping("/api/bids")
public class BidController {
	
	@Autowired
	BidService bidService;
	
	
	@GetMapping("/getallbids")
	public List<BidDTO> getAllBids(){
		return bidService.getAllBids();
	}

	@GetMapping("/getBidByBidId/{id}")
	public BidDTO getBid(@PathVariable Long id) {
		return bidService.getBidById(id).get();
	}
	
	@PostMapping("/updateBid/{id}")
	public void updateBid(@PathVariable Long id,@RequestBody BidDTO bid) {
		bidService.updateBid(id, bid);
	}
	
	@DeleteMapping("/deleteBid/{id}")
	public void deleteBid(@PathVariable Long id) {
		bidService.deleteBid(id);
	}
	
	
	

}
