package com.example.bidClassification.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Contract {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String contractNumber;
	    private String contractorName;
	    
	    
	    @Enumerated(EnumType.STRING)
	    private ContractStatus status; // Draft, Signed, Expired, etc.
	    private LocalDate startDate;
	    private LocalDate endDate;

	    @OneToOne
	    @JoinColumn(name = "bid_id", referencedColumnName = "id", unique = true)
	    private Bid bid;
	    
	    @OneToOne
	    @JoinColumn(name = "project_id")
	    private Project project;

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

		public Bid getBid() {
			return bid;
		}

		public void setBid(Bid bid) {
			this.bid = bid;
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		
	    

}
