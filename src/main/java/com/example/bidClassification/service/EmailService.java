package com.example.bidClassification.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bidClassification.dto.EmailDTO;
import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.Email;
import com.example.bidClassification.repository.EmailRepository;
import com.example.bidClassification.repository.ProjectRepository;

@Service
public class EmailService {
	
	@Autowired
	BidService bidService;
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	BidClassificationService bidClassificationService;
	
	private final List<String> bidKeywords = Arrays.asList(
	        "invitation to bid", "bidding","proposal","auction","cr","lakhs","inr","offer","contract","request for proposal", "rfp","bid" ,"tender", "bid submission", "quotation", "contract offer"
	    );
	
	public boolean isBidRelated(EmailDTO email) {
        String content = (email.getSubject() + " " + email.getBody()).toLowerCase();
        return bidKeywords.stream().anyMatch(content::contains);
    }

	public Email processIncomingEmail(EmailDTO request) {
		Email email = new Email();
        email.setSubject(request.getSubject());
        email.setBody(request.getBody());
        email.setSender(request.getSender());
     
        email.setReceivedAt(LocalDateTime.now());

        boolean isBid = isBidRelated(request);
        email.setBidRelated(isBid);
        email.setProcessedAt(LocalDateTime.now());

        emailRepository.save(email);

        if (isBid) {
            Bid bid = new Bid();
            bid.setProjectName(bidService.extractProjectName(request.getSubject()));
            
            
            bid.setContractorName(request.getSender());
            bid.setBidType(bidService.classifyBidType(request.getBody()+request.getSubject()));
            bid.setEmail(email);
            bid.setValueRange(bidService.getValue(request.getBody()+request.getSubject()));
            bidService.createBid(bid);
            
            
        }

        return emailRepository.save(email);
		
		
	}

	public List<Email> searchEmails(String subject, String sender, String body) {
		// TODO Auto-generated method stub
		if ((subject == null || subject.isEmpty()) &&
		        (sender == null || sender.isEmpty()) &&
		        (body == null || body.isEmpty())) {
		        return emailRepository.findAll();
		    }

		    return emailRepository.searchEmails(subject, sender, body);
	}
	

}
