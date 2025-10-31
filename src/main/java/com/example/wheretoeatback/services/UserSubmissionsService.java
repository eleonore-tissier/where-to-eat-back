package com.example.wheretoeatback.services;

import com.example.wheretoeatback.repositories.UserSubmissionsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSubmissionsService {

    private UserSubmissionsRepository userSubmissionsRepository;

    public UserSubmissionsService (UserSubmissionsRepository userSubmissionsRepository) {
        this.userSubmissionsRepository = userSubmissionsRepository;
    }

    public void addUserSubmission(int userId, int submissionId) {
        this.userSubmissionsRepository.addSubmissionUser(userId, submissionId);
    }

    public boolean UserSubmissionAlreadyExist(int userId, int submissionId) {
        Object userSubmission = this.userSubmissionsRepository.getSubmissionUser(userId,submissionId);
        if (userSubmission != null) {
            return true;
        }
        return false;
    }
}
