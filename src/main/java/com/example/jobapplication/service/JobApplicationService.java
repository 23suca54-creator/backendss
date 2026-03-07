package com.example.jobapplication.service;

import com.example.jobapplication.model.JobApplication;

import java.util.List;

/**
 * Service interface that defines CRUD operations for job applications.
 */
public interface JobApplicationService {

    JobApplication createJob(JobApplication job);

    List<JobApplication> getAllJobs();

    JobApplication getJobById(Long id);

    JobApplication updateJob(Long id, JobApplication job);

    void deleteJob(Long id);

}
