package com.poc.storeprocedure.entity;

import jakarta.persistence.*;

//@NamedStoredProcedureQuery(
//        name = "countUsersByLastName",
//        procedureName = "countByLast",
//        parameters = {
//                @StoredProcedureParameter(mode = ParameterMode.IN, name = "lastName", type = String.class),
//                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "count", type = Long.class)
//        })
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
