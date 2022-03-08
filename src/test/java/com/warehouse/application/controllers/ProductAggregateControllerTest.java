package com.warehouse.application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Used for mocking the spring beans of Product management
 */

@AutoConfigureMockMvc
@SpringBootTest
public class ProductAggregateControllerTest {

    private static final String WAREHOUSE_PRODUCT = "/warehouse/product/";
    private static final String WAREHOUSE_PRODUCT_ARTICLE_NAME = "/warehouse/product/Dining Table";
    private static final String WAREHOUSE_PRODUCT_ARTICLE_SELL = "/warehouse/product/Dining Table/1";

    @Autowired
    private ProductController controller;

    @Autowired
    private MockMvc mockMvc;

    /**
     * List all the products mock
     *
     * @throws Exception
     */
    @Test
    public void listProduct() throws Exception {
        mockMvc.perform(get(WAREHOUSE_PRODUCT)).andDo(print()).andExpect(status().isOk());
    }

    /**
     * Get a product mock
     *
     * @throws Exception
     */
    @Test
    public void getProduct() throws Exception {
        mockMvc.perform(get(WAREHOUSE_PRODUCT_ARTICLE_NAME)).andDo(print()).andExpect(status().isOk());
    }

    /**
     * Sell a product mock
     *
     * @throws Exception
     */
    @Test
    public void getSellProduct() throws Exception {
        mockMvc.perform(put(WAREHOUSE_PRODUCT_ARTICLE_SELL)).andDo(print()).andExpect(status().isCreated());

    }

}
