package com.example.wheretoeatback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String name;

    @Column(name = "categorie")
    private String category;

    @Column(name = "adresse2")
    private String address;

    @Column(name = "code_postal")
    private String postalCode;

    @Column(name = "commune")
    private String city;

    @Column(name = "mobile")
    private String mobilePhone;

    @Column(name = "fixe")
    private String landlinePhone;

    @Column(name = "mail")
    private String email;

    @Column(name = "site_web")
    private String website;

    @Column(name = "type_plateforme")
    private String social;

    @Column(name = "already_done")
    @Setter
    private boolean alreadyDone;
}
