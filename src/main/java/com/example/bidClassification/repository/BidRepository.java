package com.example.bidClassification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.Project;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

	List<Bid> findByProject(Project project);
	
	

}
