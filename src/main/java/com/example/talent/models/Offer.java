package com.example.talent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String place;
    private Float salary;
    private String company;
    private String description;
    private String background;
    private String requirement;
    @ManyToMany(mappedBy="offers", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Carrier> carriers;


}
