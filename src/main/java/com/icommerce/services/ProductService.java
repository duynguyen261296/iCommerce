package com.icommerce.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.icommerce.model.Product;
import com.icommerce.repository.ProductCriteria;

/**
 *
 * @author kaiser
 */
@Service
public interface ProductService {
    List<Product> getAllProducts();

    Product addNewProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Integer productId);

    Product getProductByName(String productName);

    Product getProductByBrand(String productBrand);

    List<Product> sortProductByPrice(List<Product> products, boolean increase);

    List<Product> filterProduct(ProductCriteria productCriteria);
}
