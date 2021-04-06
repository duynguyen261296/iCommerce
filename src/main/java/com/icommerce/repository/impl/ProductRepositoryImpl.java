package com.icommerce.repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import com.icommerce.model.Product;
import com.icommerce.repository.ProductCriteria;
import com.icommerce.repository.ProductRepositoryCustom;

/**
 *
 * @author kaiser
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> filterProduct(ProductCriteria productCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        if (productCriteria.getName() != null) {
            Path<String> namePath = productRoot.get("name");
            predicates.add(cb.like(namePath, productCriteria.getName()));
        }
        if (productCriteria.getBrand() != null) {
            Path<String> brandPath = productRoot.get("brand");
            predicates.add(cb.like(brandPath, productCriteria.getBrand()));
        }
        if (productCriteria.getMinPrice() != null) {
            Path<Double> minPricePath = productRoot.get("price");
            predicates.add(cb.greaterThanOrEqualTo(minPricePath, productCriteria.getMinPrice()));
        }
        if (productCriteria.getName() != null) {
            Path<Double> maxPricePath = productRoot.get("price");
            predicates.add(cb.lessThanOrEqualTo(maxPricePath, productCriteria.getMaxPrice()));
        }
        query.select(productRoot)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query).getResultList();
    }
}
