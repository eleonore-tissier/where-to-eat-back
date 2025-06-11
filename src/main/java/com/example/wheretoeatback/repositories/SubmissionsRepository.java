package com.example.wheretoeatback.repositories;

import com.example.wheretoeatback.entities.Submission;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Qualifier("submissions")
@Repository
public interface SubmissionsRepository extends CrudRepository<Submission, Long> {

    @Nonnull
    List<Submission> findAll();

    @Modifying
    @Query(value = "insert into submissions (nom_restaurant, user_id, date) VALUES (:restaurantName, :userId, :date)", nativeQuery = true)
    @Transactional
    void submitRestaurant(@Param("restaurantName") String restaurantName, @Param("userId") int userId, @Param("date") LocalDate date);
}
