package com.example.wheretoeatback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom_restaurant")
    private String restaurantName;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "date")
    private LocalDate date;

    public Submission(
            String restaurantName,
            int userId,
            LocalDate date
    ) {
        this.restaurantName = restaurantName;
        this.userId = userId;
        this.date = date;
    }
}
