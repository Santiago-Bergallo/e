package com.example.eCommerce.v2.controller;

import com.example.eCommerce.v2.exceptions.productNotFoundException;
import com.example.eCommerce.v2.model.Product;
import com.example.eCommerce.v2.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class productController {

    @Autowired
    productService productService;


    @GetMapping("/products")
    public List<Product> listProducts() {
        return  productService.listProducts();
    }

    @GetMapping("/findproduct/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id){
        try {
            Product product =   productService.findProduct(id).get();
            return ResponseEntity.ok(product);
        }
        catch (productNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
