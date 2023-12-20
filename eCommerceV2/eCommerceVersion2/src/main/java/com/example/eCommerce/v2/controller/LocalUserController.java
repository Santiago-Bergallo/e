package com.example.eCommerce.v2.controller;

import com.example.eCommerce.v2.Dto.RegistrationBody;
import com.example.eCommerce.v2.exceptions.UserAlreadyExistsException;
import com.example.eCommerce.v2.exceptions.UserNotFoundException;
import com.example.eCommerce.v2.model.LocalUser;
import com.example.eCommerce.v2.service.LocalUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocalUserController {

    @Autowired
    LocalUserService localUserService;


    @PostMapping("/save")
    ResponseEntity<LocalUser> saveUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            localUserService.registerUser(registrationBody);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (UserAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/users/{id}")
    ResponseEntity<LocalUser> checkUsers(@PathVariable Long id) throws UserNotFoundException {
        try{
            LocalUser foundUser = localUserService.findUser(id).get();
            return ResponseEntity.ok(foundUser);
        }
        catch (UserNotFoundException e) {
            System.out.println(e.message() + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/users")
    List<LocalUser> checkUsers() {
        return localUserService.listUsers();
    }
}
