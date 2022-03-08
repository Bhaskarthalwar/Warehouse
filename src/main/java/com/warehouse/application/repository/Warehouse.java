package com.warehouse.application.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is a document which represents both the product and inventory and is the source of truth
 * embedding the overall state of the warehouse
 */

@Repository
public class Warehouse {

    @JsonProperty("products")
    List<Product> products;

    @JsonProperty("inventory")
    List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
