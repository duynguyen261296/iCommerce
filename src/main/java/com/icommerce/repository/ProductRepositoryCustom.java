package com.icommerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.icommerce.model.Product;

/**
 *
 * @author kaiser
 */
@Repository
public interface ProductRepositoryCustom {
    List<Product> filterProduct(ProductCriteria productCriteria);
}
