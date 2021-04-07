package com.icommerce.model;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void getAllProductsAPI() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(10)))
                .andReturn();
        Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
        JSONArray array = new JSONArray(mvcResult.getResponse().getContentAsString());
        Assert.assertEquals("{\"quantity\":10,\"productId\":1,\"price\":1000,\"name\":\"IphoneX\",\"brand\":\"Apple\"}", array.get(0).toString());
    }

    @Test
    @WithMockUser(username = "admin_1", roles = {"ADMIN"})
    public void givenGreetURIWithPost_whenMockMVC_thenVerifyResponse() throws Exception {
        Product product = new Product();
        product.setName("Xiaomi Mi 10T Pro");
        product.setBrand("Xiaomi");
        product.setPrice(500.00);
        product.setQuantity(12);
        mvc.perform(post("/products/add")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
