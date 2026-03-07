package com.example.jobapplication.repository;

import com.example.jobapplication.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for JobApplication entities.
 */
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

}
