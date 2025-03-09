package com.mironov.sessions_app.service;


import com.mironov.sessions_app.DTO.AuthDTO;
import com.mironov.sessions_app.DTO.UserDTO;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.exception.UserAlreadyExistsException;
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

    public void saveUser(AuthDTO user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User already exists");
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

    public void updateUsername(UserDTO userData){

        if(userRepository.findByUsername(userData.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }

        UserEntity user = userRepository.findByEmail(userData.getEmail()).
                orElseThrow(() -> new UsernameNotFoundException("User with email" + userData.getEmail() + "not found"));
        user.setUsername(userData.getUsername());

        userRepository.save(user);
    }
}
