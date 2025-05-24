package com.example.bidClassification.dto;

public class BidStatusCountDTO {
	
	private String status;
    private Long count;
    
    
	public BidStatusCountDTO(String status, Long count) {
		super();
		this.status = status;
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
    
    

}
