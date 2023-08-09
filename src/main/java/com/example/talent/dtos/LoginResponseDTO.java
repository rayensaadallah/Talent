package com.example.talent.dtos;

import com.example.talent.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private Users user;
    private String jwt;







}
