// File: src/main/java/com/shop/inventory/controller/InventoryController.java
// Place this file under the 'controller' package
package com.shop.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.inventory.model.InventoryItem;
import com.shop.inventory.repository.InventoryRepository;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository repository;

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return repository.findAll();
    }

    @PostMapping
    public InventoryItem addItem(@RequestBody InventoryItem item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public InventoryItem updateItem(@PathVariable String id, @RequestBody InventoryItem newItem) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setQuantity(newItem.getQuantity());
                    item.setPrice(newItem.getPrice());
                    item.setSupplier(newItem.getSupplier());
                    return repository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return repository.save(newItem);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        repository.deleteById(id);
    }
}