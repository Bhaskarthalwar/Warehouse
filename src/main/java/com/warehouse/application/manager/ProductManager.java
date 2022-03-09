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

/**
 * it handles the lifecycle events for product catalog like
 * 1. accessing all the products in the catalog
 * 2. loading all the products to the catalog
 * 3. selling (removing) the product from both product catalog as well as inventory
 */
@Component
public class ProductManager extends WarehouseManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    InventoryManager inventoryManager;

    private Warehouse warehouse;

    @Value("${warehouse.products}")
    private String productsFile;

    /**
     * Load the products file to the catalog done as part of
     * spring bean initialization
     */
    @PostConstruct
    public void loadProducts() {
        try {
            warehouse = CommonUtil.loadFile(productsFile);
            setProducts(warehouse.getProducts());
        } catch (IOException e) {
            log.error("Error while loading products : {}", e.getMessage());
        }
    }

    /**
     * Sell the given product with the article id and
     * update the state of the warehouse store as well I.E Product catalog and inventory
     *
     * @param name
     * @param artId
     */
    public void sellProduct(String name, int artId) {
        Optional<Product> productToBeRemoved = getProducts().stream().filter(product -> product.getName().equalsIgnoreCase(name)).findFirst();
        if (productToBeRemoved.isPresent()) {
            inventoryManager.reduceArticleQty(productToBeRemoved.get(), artId);
            getProducts().stream().filter(product -> product.getName().equalsIgnoreCase(name)).findFirst().get().getProductArticles().removeIf(prodArticle -> prodArticle.getArtId() == artId);
        } else {
            throw new ProductNotFoundException("The product to be sold does not exist in the inventory");
        }
    }

    /**
     * Retrieve a particular product from the catalog.
     *
     * @param name
     * @return Product
     */
    public Product getAnProduct(String name) {
        Optional<Product> product = getProducts().stream().filter(prod -> prod.getName().equalsIgnoreCase(name)).findFirst();
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException("The product does not exist in the inventory");
        }
    }

    /**
     * State store of all products got from warehouse manager.
     *
     * @return List<Product>
     */
    public List<Product> getAllProducts() {
        return getProducts();
    }

}
