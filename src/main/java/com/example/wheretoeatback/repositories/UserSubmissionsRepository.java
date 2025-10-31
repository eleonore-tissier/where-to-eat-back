package com.example.wheretoeatback.repositories;

import com.example.wheretoeatback.entities.Submission;
import com.example.wheretoeatback.entities.User;
import jakarta.jws.soap.SOAPBinding;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Qualifier("user_submission_vote")
@Repository
public interface UserSubmissionsRepository extends CrudRepository<Submission, User> {

    @Modifying
    @Query(value = "insert into user_submission_vote (user_id, submission_id) VALUES (:userId, :submissionId)", nativeQuery = true)
    @Transactional
    void addSubmissionUser(@Param("userId") int userId, @Param("submissionId") int submissionId);

    @Modifying
    @Query(value = "select * from user_submission_vote where user_id=:userId and submission_id=:submissionId", nativeQuery = true)
    @Transactional
    Object getSubmissionUser(@Param("userId") int userId, @Param("submissionId") int submissionId);
}
