package com.warehouse.application.controllers;

import java.util.Optional;

import com.warehouse.application.manager.ProductManager;
import com.warehouse.application.repository.Product;
import com.warehouse.application.expections.ProductNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API endpoints for Product setup
 */
@RestController
public class ProductController {

    @Autowired
    private ProductManager prodManager;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Gives the list of available products in store
     *
     * @return List<Product></Product>
     */
    @GetMapping(value = "/warehouse/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listProducts() {
        return new ResponseEntity<>(prodManager.getAllProducts(), HttpStatus.OK);
    }

    /**
     * Gives the product available in store with name
     *
     * @param name
     * @return Product
     */
    @GetMapping(value = "/warehouse/product/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getProduct(@PathVariable String name) {
        Product product = prodManager.getAnProduct(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * This method sells a product with name and artId and removes the
     * sold product from inventory as well as product catalog
     *
     * @param name
     * @param artId
     * @return Updated Product and Inventory or Warehouse as a whole
     */
    @PatchMapping(value = "/warehouse/product/{name}/{artId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> sellProduct(@PathVariable String name, @PathVariable int artId) {
        prodManager.sellProduct(name, artId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
