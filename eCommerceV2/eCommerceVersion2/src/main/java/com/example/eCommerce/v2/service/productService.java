package com.example.eCommerce.v2.service;

import com.example.eCommerce.v2.exceptions.productNotFoundException;
import com.example.eCommerce.v2.model.Product;
import com.example.eCommerce.v2.repository.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productService {

@Autowired
    ProductsDao productsDao;

public List<Product> listProducts() {

    return productsDao.findAll();
}

public Optional<Product> findProduct(Long productid) throws productNotFoundException {
    Optional<Product> opProduct = productsDao.findById(productid);
    if (opProduct.isPresent()) {
        return opProduct;
    }
    else {
        throw new productNotFoundException();
    }
}

}
