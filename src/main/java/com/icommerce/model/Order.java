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

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullname;

    @Column(name = "phone", nullable = false, length = 12)
    private String phone;

    @Column(name = "address", nullable = false, length = 60)
    private String address;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    public Order() {
    }

    public Order(String fullname, String phone, String address, String email) {
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
}
