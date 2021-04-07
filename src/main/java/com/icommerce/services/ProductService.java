package com.icommerce.services;

import java.sql.SQLException;
import java.util.List;
import com.icommerce.model.Product;
import com.icommerce.repository.criteria.ProductCriteria;

/**
 *
 * @author kaiser
 */
public interface ProductService {
    List<Product> getAllProducts();

    Product addNewProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Integer productId);

    List<Product> sortProductByPrice(List<Product> products, boolean increase);

    List<Product> searchProduct(ProductCriteria productCriteria);
}
