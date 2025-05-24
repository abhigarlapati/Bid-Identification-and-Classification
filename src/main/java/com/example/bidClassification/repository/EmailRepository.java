package com.example.bidClassification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bidClassification.model.Email;

import io.micrometer.common.lang.Nullable;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
	@Query("SELECT e FROM Email e " +
		       "WHERE (:subject IS NULL OR LOWER(e.subject) LIKE LOWER(CONCAT('%', :subject, '%'))) " +
		       "AND (:sender IS NULL OR LOWER(e.sender) LIKE LOWER(CONCAT('%', :sender, '%'))) " +
		       "AND (:body IS NULL OR LOWER(e.body) LIKE LOWER(CONCAT('%', :body, '%')))")
				List<Email> searchEmails(
					    @Param("subject") @Nullable String subject,
					    @Param("sender") @Nullable String sender,
					    @Param("body") @Nullable String body
					);


}
