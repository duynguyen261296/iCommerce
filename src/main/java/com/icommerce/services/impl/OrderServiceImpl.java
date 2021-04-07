package com.icommerce.services.impl;

import java.util.List;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icommerce.model.Order;
import com.icommerce.model.Product;
import com.icommerce.repository.OrderRepository;
import com.icommerce.repository.ProductRepository;
import com.icommerce.repository.criteria.OrderInfor;
import com.icommerce.services.OrderService;

/**
 *
 * @author kaiser
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Order> getAllOrdersForUser(String email) {
        return orderRepository.findByEmail(email);
    }

    @Override
    public Order addNewOrder(OrderInfor orderInfor) {
        validateOrderInfor(orderInfor);
        Order order = new Order(orderInfor.getFullName(), orderInfor.getPhone(), orderInfor.getAddress(), orderInfor.getEmail(),
                orderInfor.getProductName(), orderInfor.getQuantity());

        Product product = productRepository.findByName(orderInfor.getProductName());
        // validate quantity of order and quantity of exist product
        Validate.isTrue(orderInfor.getQuantity() <= product.getQuantity(), "The number of product in order must not exceed the number of exist product.");
        // calculate money have to pay
        order.setSumMoney(product.getPrice() * orderInfor.getQuantity());
        // update quantity of product
        product.setQuantity(product.getQuantity() - orderInfor.getQuantity());
        productRepository.save(product);

        return orderRepository.save(order);
    }

    private void validateOrderInfor(OrderInfor orderInfor) {
        // validate information of user and product to be order
        Validate.notNull(orderInfor.getFullName());
        Validate.notNull(orderInfor.getAddress());
        Validate.notNull(orderInfor.getPhone());
        Validate.notNull(orderInfor.getEmail());
        Validate.notNull(orderInfor.getProductName());
        Validate.notNull(orderInfor.getQuantity());
        Validate.isTrue(orderInfor.getQuantity() > 0, "The number of product in order must be greater than 0");
    }
}
