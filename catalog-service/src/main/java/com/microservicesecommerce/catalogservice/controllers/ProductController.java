package com.microservicesecommerce.catalogservice.controllers;


import com.microservicesecommerce.catalogservice.entities.Product;
import com.microservicesecommerce.catalogservice.exceptions.ProductNotFoundException;
import com.microservicesecommerce.catalogservice.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import static com.sun.activation.registries.LogSupport.log;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController (ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/")
    public List<Product> allProducts(){
        //log("allProducts");
        return productService.findAllProducts();
    }

    @GetMapping("/{code}")
    public Product productByCode(@PathVariable String code){
        System.out.println("product by code");
        return productService.findProductByCode(code).orElseThrow(()->new ProductNotFoundException("Product with code ["+code+"] doesnt exist"));
    }



}
