package com.example.wheretoeatback.services;

import com.example.wheretoeatback.entities.Submission;
import com.example.wheretoeatback.repositories.SubmissionsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubmissionsService {

    private SubmissionsRepository submissionsRepository;

    public SubmissionsService(SubmissionsRepository submissionsRepository) {
        this.submissionsRepository = submissionsRepository;
    }

    public List<Submission> getSubmissions() {
        return this.submissionsRepository.findAll();
    }

    public void submitRestaurant(String restaurantName, int userId, LocalDate date) {
        this.submissionsRepository.submitRestaurant(restaurantName, userId, date);
    }
}
