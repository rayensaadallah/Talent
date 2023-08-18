package com.example.talent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Learner implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private Boolean payment;
    private LocalDate Start_date;


    @ManyToOne
    @JsonIgnore
    Users users;

    @ManyToOne
    @JsonIgnore
    Formation formation;

}
