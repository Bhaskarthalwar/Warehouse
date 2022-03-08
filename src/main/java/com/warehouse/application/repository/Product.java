package com.warehouse.application.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

/**
 * Document to store the Product to the repo
 */
@Repository
public class Product implements Serializable {

    @JsonProperty("contain_articles")
    private List<ProductArticle> productArticles;

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductArticle> getProductArticles() {
        return productArticles;
    }

    public void setProductArticles(List<ProductArticle> productArticles) {
        this.productArticles = productArticles;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productArticles=" + productArticles +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProductArticles().equals(product.getProductArticles()) &&
                getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductArticles(), getName());
    }
}
