package com.example.wheretoeatback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private int id;

        @Column(name = "name")
        private String firstName;

        @Column(name = "family_name")
        private String lastName;

        @Column(name = "points")
        @Setter
        private int points;

        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        private ERoles role;

        @ManyToMany
        @JoinTable(
                name = "user_submission_vote",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "submission_id")
        )
        private List<Submission> votedSubmissions;

}
