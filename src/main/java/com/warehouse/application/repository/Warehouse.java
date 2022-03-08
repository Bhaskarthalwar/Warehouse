package com.warehouse.application.repository;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

@JsonIgnoreProperties(ignoreUnknown = true)
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
