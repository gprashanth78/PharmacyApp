package com.pharmacy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pharmacy.entity.Role;
import com.pharmacy.repository.RoleRepository;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    ApplicationRunner init() {
        return args -> {
            if (roleRepository.findByName("User") == null) {
                Role userRole = new Role();
                userRole.setName("User");
                roleRepository.save(userRole);
            }

            if (roleRepository.findByName("Admin") == null) {
                Role adminRole = new Role();
                adminRole.setName("Admin");
                roleRepository.save(adminRole);
            }
        };
    }
}
