package com.icommerce.model;

import static com.icommerce.services.error.OrderServiceErrorMessage.NUMBER_PRODUCT_IN_ORDER_NOT_VALID;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.Validate;
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
