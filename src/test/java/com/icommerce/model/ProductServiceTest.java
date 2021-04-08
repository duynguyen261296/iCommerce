package com.icommerce.model;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.icommerce.repository.criteria.ProductCriteria;
import com.icommerce.services.ProductService;


/**
 *
 * @author kaiser
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceTest {

    @Autowired
    private DataTestFactory dataTestFactory;

    @Autowired
    private ProductService productService;

    @Test
    public void testFindAllProduct() {
        List<Product> allProducts = productService.getAllProducts();
        Assert.assertFalse(allProducts.isEmpty());
    }

    @Test
    public void testAddNewProduct() {
        Product product = productService.addNewProduct(new Product("POCO X3 Pro", 300.00, "Xiaomi", 15));
        Assert.assertNotNull(dataTestFactory.getProductRepository().findByName(product.getName()));
    }

    @Test
    public void testUpdateProduct() {
        Product product = dataTestFactory.getProductRepository().findByName("Samsung Galaxy Note 20");
        product.setPrice(1500.00);
        product.setQuantity(20);
        productService.updateProduct(product);

        Product productAfterUpdate = dataTestFactory.getProductRepository().findByName("Samsung Galaxy Note 20");
        Assert.assertEquals(Double.valueOf(1500), productAfterUpdate.getPrice());
        Assert.assertEquals(Integer.valueOf(20), productAfterUpdate.getQuantity());
    }

    @Test
    public void testDeleteProduct() {
        Product product = dataTestFactory.getProductRepository().findByName("Xiaomi Mi 11");
        productService.deleteProduct(product.getProductId());
        Assert.assertNull(dataTestFactory.getProductRepository().findByName("Xiaomi Mi 11"));
    }

    @Test
    public void testSortProductByPrice() {
        List<Product> products = dataTestFactory.getProductRepository().findAll();

        List<Product> sortedProductsIncrease = productService.sortProductByPrice(products, true);
        Assert.assertEquals(Double.valueOf(200), sortedProductsIncrease.get(0).getPrice());

        List<Product> sortedProductsDecrease = productService.sortProductByPrice(products, false);
        Assert.assertEquals("Iphone12", sortedProductsDecrease.get(0).getName());
    }

    @Test
    public void testSearchProductByCriteria() {
        ProductCriteria criteria_1 = ProductCriteria.builder()
                .name("VSmart Star 4").build();
        List<Product> vsmartProducts = productService.searchProduct(criteria_1);
        Assert.assertEquals(1, vsmartProducts.size());

        ProductCriteria criteria_2 = ProductCriteria.builder()
                .brand("Samsung").minPrice(1000.00).maxPrice(1400.00).build();
        List<Product> samSungProducts = productService.searchProduct(criteria_2);
        Assert.assertEquals(2, samSungProducts.size());
    }
}
