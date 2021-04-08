package com.icommerce.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.icommerce.repository.OrderRepository;
import com.icommerce.repository.ProductRepository;
import com.icommerce.repository.criteria.OrderInfor;
import lombok.Getter;

/**
 *
 * @author dnq
 */
@Component
public class DataTestFactory {

    @Autowired
    @Getter
    private ProductRepository productRepository;

    @Autowired
    @Getter
    private OrderRepository orderRepository;

    public Product createDataProduct(String name, String brand, double price, int quantity) {
        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    public OrderInfor createOrderInformation(String fullname, String phone, String address, String email, String productName, Integer quantity) {
        return OrderInfor.builder()
                .fullName(fullname).phone(phone)
                .address(address).email(email)
                .productName(productName).quantity(quantity)
                .build();
    }

    public Order createOrder(OrderInfor orderInfor, Double sumMoney) {
        Order order = new Order(orderInfor.getFullName(), orderInfor.getPhone(), orderInfor.getAddress(), orderInfor.getEmail(),
                orderInfor.getProductName(), orderInfor.getQuantity());
        order.setSumMoney(sumMoney);
        return orderRepository.save(order);
    }
}
