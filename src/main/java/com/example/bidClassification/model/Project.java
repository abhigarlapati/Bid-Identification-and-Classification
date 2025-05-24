package com.example.bidClassification.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators	.PropertyGenerator.class,
		  property = "id")
@Entity
public class Project {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String projectName;
    private String status;

    
    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Bid> bids;
    
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private Contract contract;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	 public void addBid(Bid bid) {
	        if (bids == null) {
	            bids = new ArrayList<>();
	        }
	        bids.add(bid);
	        bid.setProject(this);
	    }

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	
    
    

}
