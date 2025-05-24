package com.example.bidClassification.dto;

import com.example.bidClassification.model.Bid;

public class BidDTO {
	
	private String projectName;
    private String contractorName;
    private String bidType;
    private String valueRange;
    private String bidStatus;
  
    private String classificationCategory;
    
    public BidDTO() {
    }
    
    
    public BidDTO(Bid bid) {
        this.projectName = bid.getProjectName();
        this.contractorName = bid.getContractorName();
        this.bidType = bid.getBidType();
        this.valueRange = bid.getValueRange();
        this.bidStatus = bid.getBidStatus();
        
        this.classificationCategory = bid.getBidClassification() != null ? bid.getBidClassification().getCategory() : null;
    }


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getContractorName() {
		return contractorName;
	}


	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}


	public String getBidType() {
		return bidType;
	}


	public void setBidType(String bidType) {
		this.bidType = bidType;
	}


	public String getValueRange() {
		return valueRange;
	}


	public void setValueRange(String valueRange) {
		this.valueRange = valueRange;
	}


	public String getBidStatus() {
		return bidStatus;
	}


	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}





	public String getClassificationCategory() {
		return classificationCategory;
	}


	public void setClassificationCategory(String classificationCategory) {
		this.classificationCategory = classificationCategory;
	}
    
    

}
