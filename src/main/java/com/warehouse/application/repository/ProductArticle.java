package com.warehouse.application.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Objects;

@Repository
public class ProductArticle implements Serializable {

    @JsonProperty("art_id")
    private int artId;

    @JsonProperty("amount_of")
    private int amountOf;

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public int getAmountOf() {
        return amountOf;
    }

    public void setAmountOf(int amountOf) {
        this.amountOf = amountOf;
    }

    @Override
    public String toString() {
        return "ProductArticle{" +
                "artId=" + artId +
                ", amountOf=" + amountOf +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductArticle)) return false;
        ProductArticle that = (ProductArticle) o;
        return getArtId() == that.getArtId() &&
                getAmountOf() == that.getAmountOf();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtId(), getAmountOf());
    }
}
