package com.example.talent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String name_company;
    private String technologie;
    private String Skills;
    private Integer nbr_hours;
    private String place;
    private String description;
    private String Language;
    private Integer Rate;

    @ManyToMany(mappedBy="Formations", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Users> usersSet ;

}
