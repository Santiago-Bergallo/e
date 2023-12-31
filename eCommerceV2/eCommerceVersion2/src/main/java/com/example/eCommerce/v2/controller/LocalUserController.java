package com.example.eCommerce.v2.controller;

import com.example.eCommerce.v2.Dto.LoginBody;
import com.example.eCommerce.v2.Dto.LoginResponse;
import com.example.eCommerce.v2.Dto.RegistrationBody;
import com.example.eCommerce.v2.exceptions.EmailFailureException;
import com.example.eCommerce.v2.exceptions.UserAlreadyExistsException;
import com.example.eCommerce.v2.exceptions.UserNotFoundException;
import com.example.eCommerce.v2.exceptions.UserNotVerifiedException;
import com.example.eCommerce.v2.model.LocalUser;
import com.example.eCommerce.v2.service.LocalUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocalUserController {

    @Autowired
    LocalUserService localUserService;


    @PostMapping("/register")
    ResponseEntity<LocalUser> saveUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            localUserService.registerUser(registrationBody);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (UserAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EmailFailureException e) {
            throw new RuntimeException(e);
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

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginBody loginBody) {
        try {
            if (localUserService.login(loginBody) != null) {
                String jwt = localUserService.login(loginBody);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setJwt(jwt);
                loginResponse.setSuccess(true);
                return ResponseEntity.ok(loginResponse);
            }
            else {return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
        } catch (EmailFailureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (UserNotVerifiedException e) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccess(false);
            String reason = "EMAIL_NOT_VERIFIED";
            if (e.isNewEmailSent()) {
                reason += "_EMAIL_RESENT";
            }
            loginResponse.setFailure(reason);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponse);
        }
    }

    @GetMapping("/me")
    public LocalUser loggedInUser(@AuthenticationPrincipal LocalUser user){
        return user;
    }

    @GetMapping("/users")
    List<LocalUser> checkUsers() {
        return localUserService.listUsers();
    }
}
