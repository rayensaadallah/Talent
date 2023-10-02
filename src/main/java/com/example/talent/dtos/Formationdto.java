package com.example.talent.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formationdto {
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
}
