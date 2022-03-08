package com.warehouse.application.manager;

import com.warehouse.application.repository.Article;
import com.warehouse.application.repository.Product;
import java.util.List;

/**
 * This is the base class for Warehouse it encapsulates the complete
 * state of the ware house . it has both products as well as articles state.
 * Is a single point of access for the other managers
 */

public abstract class WarehouseManager {

    private List<Product> products;

    private List<Article> articles;

    /**
     * @return articles
     */
    protected List<Article> getArticles() {
        return articles;
    }

    /**
     *
     * @param articles
     */
    protected void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     *
     * @return List<Product>
     */
    protected List<Product> getProducts() {
        return products;
    }

    /**
     *
     * @param products
     */
    protected void setProducts(List<Product> products) {
        this.products = products;
    }
}
