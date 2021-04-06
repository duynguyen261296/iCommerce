package com.icommerce.services.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.icommerce.model.Product;
import com.icommerce.repository.ProductCriteria;
import com.icommerce.repository.ProductRepository;
import com.icommerce.services.ProductService;
import com.sun.tools.javac.code.Attribute;

/**
 *
 * @author kaiser
 */
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product getProductByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public Product getProductByBrand(String productBrand) {
        return productRepository.findByBrand(productBrand);
    }

    @Override
    public List<Product> sortProductByPrice(List<Product> products, boolean increase) {
        List<Product> sortedProducts = products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
        if (!increase) {
            Collections.reverse(sortedProducts);
        }
        return sortedProducts;
    }

    @Override
    public List<Product> filterProduct(ProductCriteria productCriteria) {
        return productRepository.filterProduct(productCriteria);
    }
}
