package com.example.talent.services;

import java.util.HashSet;
import java.util.Set;


import com.example.talent.dtos.LoginResponseDTO;
import com.example.talent.models.Users;
import com.example.talent.models.Role;
import com.example.talent.repository.RoleRepository;
import com.example.talent.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {


    private UserRepository userRepository;


    private RoleRepository roleRepository;


    private PasswordEncoder passwordEncoder;


    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    public ResponseEntity<String> registerUser(String username, String password){

        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exist ");
        }else {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        userRepository.save(new Users(0, username, encodedPassword, authorities));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Added ");
        }
    }

    public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }

}
