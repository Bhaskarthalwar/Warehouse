package com.warehouse.application.controllers;

import com.warehouse.application.manager.InventoryManager;
import com.warehouse.application.repository.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  Inventory Setup Endpoints
 */
@RestController
public class InventoryController {

    @Autowired
    InventoryManager inventoryManager;

    /**
     * @return Inventory data
     */
    @GetMapping(value = "/warehouse/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listInventory() {
        List<Article> allArticles = inventoryManager.getAllArticles();
        return new ResponseEntity<>(allArticles, HttpStatus.OK);
    }

    /**
     *
     * @param artId
     * @return An Article
     */
    @GetMapping(value = "/warehouse/inventory/{artId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity listAnArticle(@PathVariable int artId) {
        Article article = inventoryManager.getAnArticle(artId);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
}
