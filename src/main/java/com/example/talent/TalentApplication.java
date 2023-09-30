package com.example.talent;

import com.example.talent.models.Users;
import com.example.talent.models.Role;
import com.example.talent.repository.RoleRepository;
import com.example.talent.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TalentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentApplication.class, args);
		System.out.println("Starting ...");
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) {
				return;
			}
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			Role userRole = roleRepository.save(new Role("USER"));
			Role managerRole = roleRepository.save(new Role("MANAGER"));

			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(adminRole);
			Users adminUser = new Users(1, "admin", passwordEncode.encode("admin"), adminRoles);
			userRepository.save(adminUser);

			Set<Role> userRoles = new HashSet<>();
			userRoles.add(userRole);
			Users regularUser = new Users(2, "user", passwordEncode.encode("user"), userRoles);
			userRepository.save(regularUser);

			Set<Role> managerRoles = new HashSet<>();
			managerRoles.add(managerRole);
			Users managerUser = new Users(3, "manager", passwordEncode.encode("manager"), managerRoles);
			userRepository.save(managerUser);

		};

	}

}
