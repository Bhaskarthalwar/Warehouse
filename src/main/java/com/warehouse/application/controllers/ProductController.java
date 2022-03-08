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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductManager prodManager;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping(value = "/warehouse/product/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listProducts() {
        return new ResponseEntity<>(prodManager.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/warehouse/product/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getProduct(@PathVariable String name) {
        Optional<Product> product = prodManager.getAProduct(name);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/warehouse/product/{name}/{qty}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> sellProduct(@PathVariable String name, @PathVariable int qty) {
        prodManager.sellProduct(name, qty);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
