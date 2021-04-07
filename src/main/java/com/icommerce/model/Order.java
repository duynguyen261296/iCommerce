package com.icommerce.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kaiser
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Integer orderId;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Column(name = "phone", nullable = false, length = 12)
    private String phone;

    @Column(name = "address", nullable = false, length = 60)
    private String address;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "sum_money", nullable = false)
    private Double sumMoney;

    public Order() {
    }

    public Order(String fullName, String phone, String address, String email, String productName, Integer quantity) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.productName = productName;
        this.quantity = quantity;
    }
}
