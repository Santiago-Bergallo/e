package com.example.eCommerce.v2.repository;

import com.example.eCommerce.v2.model.LocalUser;
import com.example.eCommerce.v2.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface VerificationTokenDao extends ListCrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    long deleteByLocalUser(LocalUser localUser);






}
