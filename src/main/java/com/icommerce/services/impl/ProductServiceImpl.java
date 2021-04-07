package com.icommerce.services.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icommerce.model.Product;
import com.icommerce.repository.ProductRepository;
import com.icommerce.repository.criteria.ProductCriteria;
import com.icommerce.services.ProductService;

/**
 *
 * @author kaiser
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());
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
    public List<Product> searchProduct(ProductCriteria productCriteria) {
        return productRepository.filterProduct(productCriteria);
    }
}
