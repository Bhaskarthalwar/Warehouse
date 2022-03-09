package com.warehouse.application.manager;

import com.warehouse.application.repository.Warehouse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Test Class To Test the Functionality of
 * Product Manager
 */
public class ProductManagerTest {

    ProductManager productManager;
    InventoryManager inventoryManager;
    Warehouse wareHouse;

    /**
     * Initialize all the required instance classes to test out the features of this product manager class
     */
    @Before
    public void init() {
        productManager = new ProductManager();
        inventoryManager = new InventoryManager();
        wareHouse = new Warehouse();
        String productsFile = "products.json";
        String inventoryFile = "inventory.json";
        ReflectionTestUtils.setField(productManager, "productsFile", "products.json");
        ReflectionTestUtils.setField(productManager, "warehouse", wareHouse);
        productManager.loadProducts();
        ReflectionTestUtils.setField(inventoryManager, "inventoriesFile", "inventory.json");
        inventoryManager.loadInventory();
    }

    /**
     * Test if all the products loaded from the products json
     */
    @Test
    public void testLoadTheProducts() {
        Assert.assertNotNull(productManager.getAllProducts());
    }

    /**
     * Test if the product specified is available in the product catalog
     */
    @Test
    public void testGetAProduct() {
        String productName = "Dining Table";
        Assert.assertNotNull(productManager.getAnProduct(productName));
    }

    /**
     * Sell the product then accordingly update the product as well as inventory catalog in memory
     */
    @Test
    public void testSellAProduct() {

        ReflectionTestUtils.setField(productManager, "inventoryManager", inventoryManager);

        String productName = "Dining Table";
        int articleId = 1;

        Assert.assertEquals(productManager.getAnProduct(productName).getProductArticles().size(), 3);

        Assert.assertEquals(inventoryManager.getAllArticles().stream().filter(x -> x.getArtId() == articleId).findFirst().get().getStock(), 12);

        productManager.sellProduct(productName, 1);

        Assert.assertEquals(productManager.getAnProduct(productName).getProductArticles().size(), 2);

        Assert.assertEquals(inventoryManager.getAllArticles().stream().filter(x -> x.getArtId() == articleId).findFirst().get().getStock(), 8);

    }


}
