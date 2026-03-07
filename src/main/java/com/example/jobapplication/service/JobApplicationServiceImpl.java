package com.example.jobapplication.service;

import com.example.jobapplication.exception.ResourceNotFoundException;
import com.example.jobapplication.model.JobApplication;
import com.example.jobapplication.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for JobApplicationService.
 */
@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationServiceImpl(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public JobApplication createJob(JobApplication job) {
        return repository.save(job);
    }

    @Override
    public List<JobApplication> getAllJobs() {
        return repository.findAll();
    }

    @Override
    public JobApplication getJobById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobApplication not found with id " + id));
    }

    @Override
    public JobApplication updateJob(Long id, JobApplication job) {
        JobApplication existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobApplication not found with id " + id));

        existing.setCompanyName(job.getCompanyName());
        existing.setJobTitle(job.getJobTitle());
        existing.setLocation(job.getLocation());
        existing.setSalary(job.getSalary());
        existing.setApplicationDate(job.getApplicationDate());
        existing.setStatus(job.getStatus());
        existing.setNotes(job.getNotes());

        return repository.save(existing);
    }

    @Override
    public void deleteJob(Long id) {
        JobApplication existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobApplication not found with id " + id));
        repository.delete(existing);
    }
}
