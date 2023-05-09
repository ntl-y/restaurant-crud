package com.example.demo.configs;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User userAdmin = userRepository.findByEmail("admin@admin.com");
        if (userAdmin == null) {
            User user = new User();
            user.setName("Test test");
            user.setEmail("admin@admin.com");
            user.setPassword(passwordEncoder.encode("test"));
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
        }

        alreadySetup = true;
    }
}