package com.warehouse.application.manager;

import com.warehouse.application.expections.ProductNotFoundException;
import com.warehouse.application.repository.Product;
import com.warehouse.application.repository.Warehouse;
import com.warehouse.application.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class ProductManager extends WarehouseManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    InventoryManager inventoryManager;

    private Warehouse warehouse;

    @Value("${warehouse.products}")
    private String productsFile;

    @PostConstruct
    public void loadProducts() {
        try {
            warehouse = CommonUtil.loadFile(productsFile);
            setProducts(warehouse.getProducts());
        } catch (IOException e) {
            log.error("Error while loading products : {}", e.getMessage());
        }
    }

    public void sellProduct(String name, int artId) {
        Optional<Product> productToBeRemoved = getProducts().stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
        if (productToBeRemoved.isPresent()) {
            inventoryManager.reduceArticleQty(productToBeRemoved.get(), artId);
            getProducts().stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().get().getProductArticles().removeIf(x -> x.getArtId() == artId);
        } else {
            throw new ProductNotFoundException("The product to be sold does not exist in the inventory");
        }
    }

    public Optional<Product> getAProduct(String name) {
        return getProducts().stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<Product> getAllProducts() {
        return getProducts();
    }

}
