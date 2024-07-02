package com.poc.storeprocedure.service;

import com.poc.storeprocedure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Long countUsersByLastName(String lastName) {
        return userRepository.countUsersByLastName(lastName);
    }
}
