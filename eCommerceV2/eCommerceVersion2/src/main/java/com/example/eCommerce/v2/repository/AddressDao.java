package com.example.eCommerce.v2.repository;

import com.example.eCommerce.v2.model.Address;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends ListCrudRepository<Address, Long> {
}
