package com.poc.storeprocedure.entity;

import jakarta.persistence.*;

@Entity
public class BNYUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(initialValue = 1, name = "id")
    private int id;
    private String firstName;

    private String lastName;

    private String city;
}
