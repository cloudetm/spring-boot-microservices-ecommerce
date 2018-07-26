package com.microservicesecommerce.catalogservice.services;

import com.microservicesecommerce.catalogservice.entities.Product;
import com.microservicesecommerce.catalogservice.models.ProductsInventoryResponse;
import com.microservicesecommerce.catalogservice.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;



@Service
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public ProductService(ProductRepository productRepository,RestTemplate restTemplate){
        this.productRepository=productRepository;
        this.restTemplate=restTemplate;
    }

    public List<Product> findAllProducts(){
        //log("find all products in service");
        return productRepository.findAll();
    }
    public Optional<Product> findProductByCode(String code){
        //log("findprodcutobycode");
        Optional<Product> productOptional=productRepository.findByCode(code);

        if(productOptional.isPresent()){
            log.info("Fetching inventory level product code"+code);
            ResponseEntity<ProductsInventoryResponse> itemResponseEntity=restTemplate.getForEntity("http://inventory-service/api/inventory/{code}",
                    ProductsInventoryResponse.class,code);
            log.info("after inventory-service");
            if(itemResponseEntity.getStatusCode()== HttpStatus.OK){
                Integer quantity=itemResponseEntity.getBody().getAvailableQuantity();
                log.info("AvaiableQuantity"+quantity);
                productOptional.get().setInStock(quantity>0);
            }else{
                log.error("Unable to get inventory level for product_code: " + code + ", status code: " + itemResponseEntity.getStatusCode());
            }
        }


        return productOptional;
    }




}
