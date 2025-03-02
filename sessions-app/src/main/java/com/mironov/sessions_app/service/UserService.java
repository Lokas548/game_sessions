package com.mironov.sessions_app.service;


import com.mironov.sessions_app.DTO.UserDTO;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("User already exists");
        }
        UserEntity newUser = new UserEntity();
        newUser.setEmail(user.getEmail());
        newUser.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now().toString());

        userRepository.save(newUser);
    }

    public UserEntity loadUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email" + email + "not found"));
    }
}
