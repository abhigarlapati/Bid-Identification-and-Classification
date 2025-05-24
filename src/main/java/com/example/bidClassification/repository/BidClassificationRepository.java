package com.example.bidClassification.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.BidClassification;

@Repository
public interface BidClassificationRepository extends JpaRepository<BidClassification, Long>{


	Optional<BidClassification> findByCategory(String Category);

}
