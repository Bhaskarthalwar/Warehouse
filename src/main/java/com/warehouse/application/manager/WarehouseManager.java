package com.warehouse.application.manager;

import com.warehouse.application.model.Article;
import com.warehouse.application.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseManager {

    private List<Product> products;

    private List<Article> articles;

    protected List<Article> getArticles() {
        return articles;
    }

    protected void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    protected List<Product> getProducts() {
        return products;
    }

    protected void setProducts(List<Product> products) {
        this.products = products;
    }
}
