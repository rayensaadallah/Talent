package com.example.talent.dtos;


import com.example.talent.models.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrierDto {
    private Integer id;
    private String title;
    private String Level;
    private int needed_days;
    private Date date_Start;
    private Type type;
    private List<UserDto> users;

}
