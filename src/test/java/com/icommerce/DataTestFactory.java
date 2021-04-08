package com.icommerce;

import org.springframework.stereotype.Component;
import com.icommerce.model.Order;
import com.icommerce.model.Product;
import com.icommerce.repository.OrderRepository;
import com.icommerce.repository.ProductRepository;
import com.icommerce.repository.criteria.OrderInfor;

/**
 *
 * @author dnq
 */
@Component
public class DataTestFactory {

    public static Product createDataProduct(String name, String brand, double price, int quantity,
                                            ProductRepository productRepository) {
        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    public static OrderInfor createOrderInformation(String fullname, String phone, String address, String email, String productName, Integer quantity) {
        return OrderInfor.builder()
                .fullName(fullname).phone(phone)
                .address(address).email(email)
                .productName(productName).quantity(quantity)
                .build();
    }

    public static Order createOrder(OrderInfor orderInfor, ProductRepository productRepository, OrderRepository orderRepository) {
        Order order = new Order(orderInfor.getFullName(), orderInfor.getPhone(), orderInfor.getAddress(), orderInfor.getEmail(),
                orderInfor.getProductName(), orderInfor.getQuantity());

        Product product = productRepository.findByName(orderInfor.getProductName());
        order.setSumMoney(product.getPrice() * orderInfor.getQuantity());
        product.setQuantity(product.getQuantity() - orderInfor.getQuantity());
        productRepository.save(product);
        return orderRepository.save(order);
    }
}
