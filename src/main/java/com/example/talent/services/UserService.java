package com.example.talent.services;

import java.util.*;

import com.example.talent.Mappers.UserMappers;
import com.example.talent.dtos.UserDto;
import com.example.talent.models.Role;
import com.example.talent.models.Users;
import com.example.talent.repository.RoleRepository;
import com.example.talent.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    UserMappers userMappers;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }


    public UserDto getUserByToken() {//te5ou el token traj3ou user without id forma dto
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();
        Jwt jwt = jwtAuthenticationToken.getToken();
        String username = jwt.getClaimAsString("sub");
        Optional<Users> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return userMappers.fromBasic(user.get());
        } else {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
    }


    public void removeMyaccount() {
        UserDto userDto = getUserByToken();
        Optional<Users> existingUserOptional = userRepository.findByUsername(userDto.getUsername());

        existingUserOptional.ifPresentOrElse(
                existingUser -> userRepository.delete(existingUser),
                () -> {
                    throw new EntityNotFoundException("User not found for username: " + userDto.getUsername());
                }
        );
    }

    public UserDto update(UserDto dto) {
        Optional<Users> userOptional = userRepository.findByUsername(dto.getUsername());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (dto.getEmail() != null) {
                user.setEmail(dto.getEmail());
            }

            if (dto.getPhoneNumber() != null) {
                user.setPhoneNumber(dto.getPhoneNumber());
            }

            if (dto.getCountry() != null) {
                user.setCountry(dto.getCountry());
            }

            if (dto.getObjectif() != null) {
                user.setObjectif(dto.getObjectif());
            }

            userRepository.save(user);
            return dto;
        } else {
            throw new EntityNotFoundException("User not found for username: " + dto.getUsername());
        }
    }

    public List<UserDto> getAllUsersByRoles(String s) {
        List<Users> usersWithUserRole = userRepository.findByAuthorities_Authority(s);

        List<UserDto> userDtos = new ArrayList<>();
        for (Users user : usersWithUserRole) {
            userDtos.add(userMappers.fromBasic(user));
        }

        return userDtos;
    }
    public void UserToManager(Integer id) throws UsernameNotFoundException {
        Optional<Users> userOptional = userRepository.findById(id);
        Optional<Role> managerRole = roleRepository.findById(3);

        if ((userOptional.isPresent())&&(managerRole.isPresent())) {
            Users user = userOptional.get();
            Role userRole = roleRepository.findByAuthority("MANAGER").get();
            Set<Role> authorities = new HashSet<>();

            authorities.add(userRole);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }


}




