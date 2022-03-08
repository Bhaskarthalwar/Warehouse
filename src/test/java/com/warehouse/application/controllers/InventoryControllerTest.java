package com.warehouse.application.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class InventoryControllerTest {
    private static final String WAREHOUSE_INVENTORY = "/warehouse/inventory/";
    private static final String WAREHOUSE_INVENTORY_ARTICLE_ID = "/warehouse/inventory/1";

    @Autowired
    private InventoryController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void listInventory() throws Exception {
        mockMvc.perform(get(WAREHOUSE_INVENTORY)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getAnArticle() throws Exception {
        mockMvc.perform(get(WAREHOUSE_INVENTORY_ARTICLE_ID)).andDo(print()).andExpect(status().isOk());
    }



}
