package com.example.wheretoeatback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @ManyToMany(mappedBy = "votedSubmissions")
    private List<User> users;

    public Submission(
            String restaurantName,
            User user,
            LocalDate date
    ) {
        this.restaurantName = restaurantName;
        this.user = user;
        this.date = date;
    }
}
