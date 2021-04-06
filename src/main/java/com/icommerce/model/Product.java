package com.icommerce.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kaiser
 */
@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    private Integer productId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "brand", nullable = false, length = 45)
    private String brand;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Product() {
    }

    public Product(String name, Double price, String brand, Integer quantity) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
    }
}
