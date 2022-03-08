package com.warehouse.application.manager;

import com.warehouse.application.expections.ArticleNotFoundException;
import com.warehouse.application.repository.Article;
import com.warehouse.application.repository.Product;
import com.warehouse.application.repository.ProductArticle;
import com.warehouse.application.repository.Warehouse;
import com.warehouse.application.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * This handles all the life cycle events related to Inventory
 * 1. Load the inventory from external file
 * 2. Retrieve the articles from Inventory
 * 3. Remove/Update the quantity of an article when a product of that article is sold.
 */
@Component
public class InventoryManager extends WarehouseManager {

    @Value("${warehouse.inventory}")
    private String inventoriesFile;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Load the inventory from external file
     */
    @PostConstruct
    public void loadInventory() {
        File file;
        Warehouse wareHouse;
        try {
            wareHouse = CommonUtil.loadFile(inventoriesFile);
            setArticles(wareHouse.getArticles());
        } catch (IOException e) {
            log.error("Error while loading inventory : {}", e.getMessage());
        }
    }

    /**
     * Retrieve all the articles from the inventory
     *
     * @return List<Article>
     */
    public List<Article> getAllArticles() {
        return getArticles();
    }

    /**
     * when a product is sold with an article , update the status of the change in inventory
     * if the quantity updated reaches to zero then remove that article from inventory
     * if the quantity to be sold is higher than whats in store then dont update the store at all
     * if the quantity is available then updated the sold quantity to the inventory
     *
     * @param product
     * @param artiId
     */
    public void reduceArticleQty(Product product, int artiId) {
        Optional<ProductArticle> productArticle = product.getProductArticles().stream().filter(x -> x.getArtId() == artiId).findFirst();
        if (productArticle.isPresent()) {
            Optional<Article> articleToBeUpdated = getArticles().stream().filter(x -> x.getArtId() == artiId).findFirst();
            if (articleToBeUpdated.isPresent()) {
                int quantityToBeUpdated = articleToBeUpdated.get().getStock() - productArticle.get().getAmountOf();
                if (Integer.compare(quantityToBeUpdated, 0) != -1)
                    getArticles().stream().filter(x -> x.getArtId() == artiId).findFirst().get().setStock(quantityToBeUpdated);
                if (Integer.compare(quantityToBeUpdated, 0) == 0)
                    removeAnArticle(artiId);
            } else {
                throw new ArticleNotFoundException("The article that needs to be updated is not found in the inventory");
            }
        } else {
            throw new ArticleNotFoundException("The product article does not exist for the given product in the catalog");
        }

    }

    /**
     * Retrieve an article
     *
     * @param articleId
     * @return Article
     */
    public Article getAnArticle(int articleId) {
        Optional<Article> article = getArticles().stream().filter(x -> x.getArtId() == articleId).findFirst();
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new ArticleNotFoundException("The article does not exist in the Inventory");
        }
    }

    /**
     * Remove an article from with articleId on a specific condition
     *
     * @param artId
     */
    private void removeAnArticle(int artId) {
        getArticles().removeIf(x -> x.getArtId() == artId);
    }


}
