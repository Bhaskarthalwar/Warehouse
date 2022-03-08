package com.warehouse.application.controllers;

import java.util.List;

import com.warehouse.application.manager.InventoryManager;
import com.warehouse.application.model.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    InventoryManager inventoryManager;

    @GetMapping(value = "/warehouse/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listProducts() {
        List<Article> allArticles = inventoryManager.getAllArticles();
        return new ResponseEntity<>(allArticles, HttpStatus.OK);
    }

}
