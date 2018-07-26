package com.microservicesecommerce.catalogservice.models;

import lombok.Data;

@Data
public class ProductsInventoryResponse {
    private String productCode;
    private int availableQuantity;
}
