package com.example.jobapplication.controller;

import com.example.jobapplication.model.JobApplication;
import com.example.jobapplication.service.JobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing CRUD endpoints for job applications.
 */
@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    // Create
    @PostMapping
    public ResponseEntity<JobApplication> createJob(@RequestBody JobApplication job) {
        JobApplication created = service.createJob(job);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllJobs() {
        List<JobApplication> list = service.getAllJobs();
        return ResponseEntity.ok(list);
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getJobById(@PathVariable Long id) {
        JobApplication job = service.getJobById(id);
        return ResponseEntity.ok(job);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateJob(@PathVariable Long id, @RequestBody JobApplication job) {
        JobApplication updated = service.updateJob(id, job);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        service.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

}
