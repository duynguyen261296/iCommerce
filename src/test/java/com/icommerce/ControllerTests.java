package com.icommerce;

import static com.icommerce.SecurityTest.ADMIN_1;
import static com.icommerce.SecurityTest.ROLE_ADMIN;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icommerce.model.Product;
import com.icommerce.repository.OrderRepository;
import com.icommerce.repository.ProductRepository;
import com.icommerce.repository.criteria.OrderInfor;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ControllerTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

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
    public void testGetAllProductsAPI() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        JSONArray array = new JSONArray(mvcResult.getResponse().getContentAsString());
        JSONAssert.assertEquals("{\"quantity\":10,\"productId\":1,\"price\":1000,\"name\":\"IphoneX\",\"brand\":\"Apple\"}", array.get(0).toString(), false);
    }

    @Test
    @WithMockUser(username = ADMIN_1, roles = {ROLE_ADMIN})
    public void testAddNewProduct() throws Exception {
        Product product = DataTestFactory.createDataProduct("Xiaomi Mi 10T Pro",
                "Xiaomi", 500.00, 12, productRepository);
        MvcResult mvcResult = mvc.perform(post("/products/add")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        JSONAssert.assertEquals("{\"name\":\"Xiaomi Mi 10T Pro\",\"price\":500.0,\"brand\":\"Xiaomi\",\"quantity\":12}", mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser(username = ADMIN_1, roles = {ROLE_ADMIN})
    public void testUpdateProduct() throws Exception {
        Product product = DataTestFactory.createDataProduct("Xiaomi Mi 10T Pro",
                "Xiaomi", 800.00, 50, productRepository);
        MvcResult mvcResult = mvc.perform(put("/products/update")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        JSONAssert.assertEquals("{\"quantity\":50,\"price\":800,\"name\":\"Xiaomi Mi 10T Pro\",\"brand\":\"Xiaomi\"}", mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser(username = ADMIN_1, roles = {ROLE_ADMIN})
    public void testDeleteProduct() throws Exception {
        Product product = DataTestFactory.createDataProduct("Xiaomi Mi 9T Pro",
                "Xiaomi", 500.00, 12, productRepository);
        mvc.perform(delete("/products/delete")
                .param("id", product.getProductId().toString()))
                .andExpect(status().isOk());
        Assert.assertNull(productRepository.findByName("Xiaomi Mi 9T Pro"));
    }

    @Test
    @WithMockUser
    public void testAddNewOrder() throws Exception {
        OrderInfor orderInfor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", "TPHCM"
                , "user_1@gmail.com", "Iphone12", 1);
        MvcResult mvcResult = mvc.perform(post("/orders")
                .content(asJsonString(orderInfor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        JSONAssert.assertEquals("{\"fullName\":\"Nguyen Quoc Duy\",\"phone\":\"0123456789\",\"address\":\"TPHCM\",\"email\":\"user_1@gmail.com\",\"productName\":\"Iphone12\",\"quantity\":1,\"sumMoney\":1800.0}", mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    public void testGetOrderByEmail() throws Exception {
        OrderInfor orderInfor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", "TPHCM"
                , "user_1@gmail.com", "Iphone12", 1);
        DataTestFactory.createOrder(orderInfor, productRepository, orderRepository);
        MvcResult mvcResult = mvc.perform(get("/orders/search")
                .param("email", orderInfor.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        JSONArray array = new JSONArray(mvcResult.getResponse().getContentAsString());
        JSONAssert.assertEquals("{\"orderId\":1,\"fullName\":\"Nguyen Quoc Duy\",\"phone\":\"0123456789\",\"address\":\"TPHCM\",\"email\":\"user_1@gmail.com\",\"productName\":\"Iphone12\",\"quantity\":1,\"sumMoney\":1800.0}", array.get(0).toString(), false);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
