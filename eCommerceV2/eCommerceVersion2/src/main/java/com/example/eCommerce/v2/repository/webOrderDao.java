package com.example.eCommerce.v2.repository;

import com.example.eCommerce.v2.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface webOrderDao extends ListCrudRepository<WebOrder, Long> {
    List<WebOrder> findByUser_Id(Long id);


}
