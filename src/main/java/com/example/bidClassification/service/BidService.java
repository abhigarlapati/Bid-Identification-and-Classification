package com.example.bidClassification.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.bidClassification.dto.BidDTO;
import com.example.bidClassification.dto.BidStatusCountDTO;
import com.example.bidClassification.dto.EmailDTO;
import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.BidClassification;
import com.example.bidClassification.model.Email;
import com.example.bidClassification.model.Project;
import com.example.bidClassification.repository.BidClassificationRepository;
import com.example.bidClassification.repository.BidRepository;
import com.example.bidClassification.repository.EmailRepository;
import com.example.bidClassification.repository.ProjectRepository;

@Service
public class BidService {
	

	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	BidRepository bidRepository;
	
	@Autowired
	BidClassificationService bidClassificationService;
	
	@Autowired
	BidClassificationRepository bidClassificationRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	
	
	
	
	public List<BidDTO> getAllBids() {
	    List<Bid> bids = bidRepository.findAll();
	    return bids.stream()
	               .map(BidDTO::new) 
	               .collect(Collectors.toList());
	}
    public Optional<BidDTO> getBidById(Long id) {
        Bid bid=bidRepository.findById(id).orElseThrow(() -> new RuntimeException("Bid not found"));
        BidDTO biddto=new BidDTO(bid);
        return Optional.of(biddto);
    }

   

    public Bid updateBid(Long id, BidDTO biddto) {
        Bid bid = bidRepository.findById(id).orElseThrow(() -> new RuntimeException("Bid not found"));
        bid.setProjectName(biddto.getProjectName());
        bid.setContractorName(biddto.getContractorName());
        bid.setBidType(biddto.getBidType());
        bid.setValueRange(biddto.getValueRange());
        bid.setBidStatus(biddto.getBidStatus());
        return bidRepository.save(bid);
    }

    public void deleteBid(Long id) {
        bidRepository.deleteById(id);
    }
    

	
	
	public String classifyBidType(String text) {
	    String lowerText = text.toLowerCase();
	    if (lowerText.contains("technical")) return "Technical";
	    if (lowerText.contains("financial") || lowerText.contains("quotation")) return "Financial";
	    if (lowerText.contains("pre-qualification") || lowerText.contains("prequalification")) return "Pre-qualification";
	    if (lowerText.contains("negotiation")) return "Negotiation";
	    if (lowerText.contains("proposal")) return "Combined"; // assume combined if just "proposal"
	    return "Other";
	}
	public String getValue(String text)
    {
    	Pattern pattern = Pattern.compile("INR ([\\d\\.]+\\s?Lakshs|Cr|cr|lakhs\rupees|Rs)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
	
	private String extractContractorName(Email email) {
        return email.getSender();
    }
	
	String extractProjectName(String subject) {
        
        return subject.length()>30 ? subject.substring(0,30):subject;
    }

	public void createBid(Bid bid) {
		String text=(bid.getEmail().getBody()+bid.getEmail().getSubject());
		
		
		 String categoryname=bidClassificationService.classifyText(text);
		 BidClassification bc = bidClassificationRepository.findByCategory(categoryname)
			        .orElseGet(() -> {
			            BidClassification c = new BidClassification();
			            c.setCategory(categoryname);
			            return bidClassificationRepository.save(c);
			        });
		 
		 bid.setBidClassification(bc);
		 
		 String projectName = bid.getProjectName();
		    Project project = projectRepository.findByProjectName(projectName)
		        .orElseGet(() -> {
		            Project p = new Project();
		            p.setProjectName(projectName);
		            return projectRepository.save(p);
		        });
		    
		    project.addBid(bid);
		 
		 
		    bidRepository.save(bid);
		    
		    projectRepository.save(project);
		 
		
	}

	public List<BidStatusCountDTO> countBidsByStatus() {
		 List<Bid> allBids = bidRepository.findAll();
		    return allBids.stream()
		            .collect(Collectors.groupingBy(
		                bid -> bid.getBidStatus() == null ? "UNKNOWN" : bid.getBidStatus(),
		                Collectors.counting()))
		            .entrySet()
		            .stream()
		            .map(entry -> new BidStatusCountDTO(entry.getKey(), entry.getValue()))
		            .collect(Collectors.toList());
	}
	
}
