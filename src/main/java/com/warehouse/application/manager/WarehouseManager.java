package com.warehouse.application.manager;

import com.warehouse.application.repository.Article;
import com.warehouse.application.repository.Product;

import java.util.List;

public abstract class WarehouseManager {

    private List<Product> products;

    private List<Article> articles;

    List<Article> getArticles() {
        return articles;
    }

    void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    List<Product> getProducts() {
        return products;
    }

    void setProducts(List<Product> products) {
        this.products = products;
    }
}
