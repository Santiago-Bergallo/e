package com.example.eCommerce.v2.repository;

import com.example.eCommerce.v2.model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserDao extends ListCrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByEmailIgnoreCase(String email);

    Optional<LocalUser> findByUsernameIgnoreCase(String username);




}
