package com.sergiossj.inventoryservice.entities;


import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Entity
@Table(name="inventory")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="product_code",nullable =false,unique = false)
    private String productCode;
    @Column(name="quantity")
    private Integer avaiableQuuantity=0;
}
