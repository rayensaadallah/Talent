package com.example.talent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Integer id;
    private String title;
    private String Place;
    private Float Salary;
    private String Company;
    private String Description;
    private String Background;
    private String Requirement;


}
