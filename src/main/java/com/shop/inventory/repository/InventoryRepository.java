// File: src/main/java/com/shop/inventory/repository/InventoryRepository.java
// Place this file under the 'repository' package
package com.shop.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shop.inventory.model.InventoryItem;

public interface InventoryRepository extends MongoRepository<InventoryItem, String> {
    
}