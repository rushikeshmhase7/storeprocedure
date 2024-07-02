package com.poc.storeprocedure.controller;

import com.poc.storeprocedure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BNYUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/countByLastName")
    public ResponseEntity<Long> countUsersByLastName(@RequestParam String lastName) {
        Long count = userService.countUsersByLastName(lastName);
        return ResponseEntity.ok(count);
    }
}
