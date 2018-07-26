package com.sergiossj.inventoryservice.controlllers;

import com.sergiossj.inventoryservice.entities.InventoryItem;
import com.sergiossj.inventoryservice.repositories.InventoryItemRepository;


import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class InventoryController {
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryController(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @GetMapping("/api/inventory/{productCode}")
    public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable("productCode") String productCode) {
        log.info("Finding inventory fro product code" + productCode);
        Optional<InventoryItem> inventoryItem = inventoryItemRepository.findByProductCode(productCode);
        if (inventoryItem.isPresent()) {
            return new ResponseEntity<InventoryItem>(inventoryItem.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/api/inventory")
    public List<InventoryItem> getInventory(){
        log.info("Finding inventory for all prodcuts");
        return inventoryItemRepository.findAll();
    }


}
