package com.example.bidClassification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
	
	boolean existsByBid(Bid bid);

}
