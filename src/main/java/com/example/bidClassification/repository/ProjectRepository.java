package com.example.bidClassification.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bidClassification.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query("SELECT p FROM Project p WHERE LOWER(p.projectName) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Project> findByProjectNameContaining(@Param("name") String name);
	
	Optional<Project> findByProjectName(String projectName);

	
}
