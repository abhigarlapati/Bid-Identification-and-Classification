package com.example.bidClassification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.example.bidClassification.dto.ContractDTO;
import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.Contract;
import com.example.bidClassification.model.ContractStatus;
import com.example.bidClassification.model.Project;
import com.example.bidClassification.repository.BidRepository;
import com.example.bidClassification.repository.ContractRepository;
import com.example.bidClassification.repository.ProjectRepository;

@Service
public class ContractService {
	
	@Autowired
    private ContractRepository contractRepository;
	
	@Autowired
	private BidRepository bidRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	public ContractDTO createContract(ContractDTO request) {
        Bid bid = bidRepository.findById(request.getBidId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bid not found"));

        Project project = bid.getProject();
        if (project == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bid is not associated with any project");
        }

        List<Bid> projectBids = bidRepository.findByProject(project);
        for (Bid b : projectBids) {
            if (contractRepository.existsByBid(b)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "A contract already exists for this project");
            }
        }

        Contract contract = new Contract();
        contract.setContractNumber(request.getContractNumber());
        contract.setContractorName(request.getContractorName());
        contract.setStatus(request.getStatus());
        contract.setStartDate(request.getStartDate());
        contract.setEndDate(request.getEndDate());
        contract.setBid(bid);
        contract.setProject(project); 

        if(bid.getContractorName().equals(contract.getContractorName())) {
        contractRepository.save(contract);
        
        bid.setContract(contract);
        bid.setBidStatus(contract.getStatus().toString());
        bidRepository.save(bid);

        project.setContract(contract);
        projectRepository.save(project);

        return new ContractDTO(contract);
        }
        else {
        	throw new ResponseStatusException(
        	        HttpStatus.BAD_REQUEST, 
        	        "Bid contractor and given contractor do not match. Unable to assign bid."
        	    );        
        	}
        
        
        
    }

    public Contract updateStatus(Long contractId, ContractStatus newStatus) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        
        contract.setStatus(newStatus);
        contract.getBid().setBidStatus(newStatus.toString());
        return contractRepository.save(contract);
    }

	public List<ContractDTO> getAllContracts() {
		List<Contract> contracts = contractRepository.findAll();
		List<ContractDTO> contractDTOs = new ArrayList<>();

	    for (Contract contract : contracts) {
	        contractDTOs.add(new ContractDTO(contract));
	    }

	    return contractDTOs;
	}

}
