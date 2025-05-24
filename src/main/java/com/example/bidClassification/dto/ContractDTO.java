package com.example.bidClassification.dto;

import java.time.LocalDate;

import com.example.bidClassification.model.Contract;
import com.example.bidClassification.model.ContractStatus;

public class ContractDTO {
	
	private String contractNumber;
    private String contractorName;
    private ContractStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long bidId;
    
    
    
    
    
	public ContractDTO() {
	}
	public ContractDTO(Contract contract) {
		super();
		this.contractNumber = contract.getContractNumber();
		this.contractorName = contract.getContractorName();
		this.status = contract.getStatus();
		this.startDate = contract.getStartDate();
		this.endDate = contract.getEndDate();
		this.bidId = contract.getBid().getId();
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	public ContractStatus getStatus() {
		return status;
	}
	public void setStatus(ContractStatus status) {
		this.status = status;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Long getBidId() {
		return bidId;
	}
	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}
    
    

}
