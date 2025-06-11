package com.example.wheretoeatback.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "saisons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_debut")
    private LocalDate startDate;

    @Column(name = "date_fin")
    private LocalDate endDate;

    @Column(name = "winner_id")
    private Integer winnerId;
}
