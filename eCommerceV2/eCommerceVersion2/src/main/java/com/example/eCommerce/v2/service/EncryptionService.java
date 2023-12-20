package com.example.eCommerce.v2.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    @Value("${Encryption.salt.round}")
    private int saltRounds;

    @Value("${Encryption.salt}")
    private String salt;

    @PostConstruct
    public void postConstruct() {
        salt = BCrypt.gensalt(saltRounds);
    }

    public String EncryptPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    public Boolean checkPassword(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }


}
