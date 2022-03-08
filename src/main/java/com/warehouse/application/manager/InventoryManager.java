package com.warehouse.application.manager;

import com.warehouse.application.model.Article;
import com.warehouse.application.model.Product;
import com.warehouse.application.model.ProductArticle;
import com.warehouse.application.model.Warehouse;
import com.warehouse.application.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class InventoryManager extends WarehouseManager {

    @Value("${warehouse.inventory}")
    private String inventoriesFile;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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

    public List<Article> getAllArticles() {
        return getArticles();
    }

    private void removeAnArticle(int artId) {
        getArticles().removeIf(x -> x.getArtId() == artId);
    }

    public void reduceArticleQty(Product product, int artiId) {
        ProductArticle productArticle = product.getProductArticles().stream().filter(x -> x.getArtId() == artiId).findFirst().get();
        Article articleToBeUpdated = getArticles().stream().filter(x -> x.getArtId() == artiId).findFirst().get();
        int quantityToBeUpdated = articleToBeUpdated.getStock() - productArticle.getAmountOf();
        if (Integer.compare(quantityToBeUpdated, 0) != -1)
            getArticles().stream().filter(x -> x.getArtId() == artiId).findFirst().get().setStock(quantityToBeUpdated);
        if (Integer.compare(quantityToBeUpdated, 0) == 0)
            removeAnArticle(artiId);
    }


}
