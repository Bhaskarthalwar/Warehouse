package com.warehouse.application.manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * This test class is used for testing the warehouse inventory manager
 */
public class WareHouseInventoryManagerTest {

    InventoryManager inventoryManager;


    /**
     * Initialize the instances required for testing this class
     */
    @Before
    public void init() {
        inventoryManager = new InventoryManager();
        String inventoryFile = "inventory.json";
        ReflectionTestUtils.setField(inventoryManager, "inventoriesFile", "inventory.json");
        inventoryManager.loadInventory();
    }

    /**
     * Test the loading of the inventory and presence of all the articles
     */
    @Test
    public void testLoadTheInventory() {
        Assert.assertNotNull(inventoryManager.getAllArticles());
    }

    /**
     * Get a loaded article from the inventory
     */
    @Test
    public void testGetAArticle() {
        int articleId = 1;
        Assert.assertEquals(inventoryManager.getAnArticle(1).getArtId(), 1);
    }


}
