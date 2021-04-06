package com.icommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.icommerce.model.Product;
import com.icommerce.repository.ProductCriteria;
import com.icommerce.services.ProductService;

/**
 *
 * @author kaiser
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/sort")
    @ResponseBody
    public List<Product> sortProductByPrice(@RequestParam(required = false) boolean increase) {
        List<Product> allProducts = productService.getAllProducts();
        return productService.sortProductByPrice(allProducts, increase);
    }

    @GetMapping("/products/search")
    @ResponseBody
    public List<Product> filterProductByCriteria(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String brand,
                                                 @RequestParam(required = false) Double minPrice,
                                                 @RequestParam(required = false) Double maxPrice) {
        ProductCriteria productCriteria = ProductCriteria.builder()
                .name(name).brand(brand)
                .minPrice(minPrice).maxPrice(maxPrice).build();
        return productService.searchProduct(productCriteria);
    }

    @PostMapping("/products/add")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PutMapping(value = "/products/update")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping(value = "/products/delete")
    public void deleteProduct(@RequestParam Integer id) {
        productService.deleteProduct(id);
    }
}
