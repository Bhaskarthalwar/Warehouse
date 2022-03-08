package com.warehouse.application.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article implements Serializable {
    private static final long serialVersionUID = -1768105479248068676L;

    @JsonProperty("art_id")
    private int artId;
    private String name;
    private int stock;


    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return getArtId() == article.getArtId() &&
                getStock() == article.getStock() &&
                getName().equals(article.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtId(), getName(), getStock());
    }
}
