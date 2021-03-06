package com.icommerce.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.icommerce.model.Product;
import com.icommerce.repository.criteria.ProductCriteria;

/**
 *
 * @author kaiser
 */
@Repository
public interface ProductRepositoryCustom {
    List<Product> filterProduct(ProductCriteria productCriteria);
}
