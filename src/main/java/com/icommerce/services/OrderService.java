package com.icommerce.services;

import java.util.List;
import com.icommerce.model.Order;
import com.icommerce.repository.criteria.OrderInfor;

/**
 *
 * @author kaiser
 */
public interface OrderService {
    List<Order> getAllOrdersForUser(String email);

    Order addNewOrder(OrderInfor orderInfor);
}
