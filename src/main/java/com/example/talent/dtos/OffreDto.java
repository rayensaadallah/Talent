package com.example.talent.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffreDto {
    private String title;
    private String Place;
    private Float Salary;
    private String Company;
    private String Description;
    private String Background;
    private String Requirement;
}
