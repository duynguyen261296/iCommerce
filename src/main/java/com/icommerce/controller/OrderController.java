package com.icommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.icommerce.model.Order;
import com.icommerce.repository.criteria.OrderInfor;
import com.icommerce.services.OrderService;

/**
 *
 * @author kaiser
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders/search")
    @ResponseBody
    public List<Order> getAllProductsForUser(@RequestParam String email) {
        return orderService.getAllOrdersForUser(email);
    }

    @PostMapping("/orders")
    @ResponseBody
    public Order addNewOrder(@RequestBody OrderInfor orderInfor) {
        return orderService.addNewOrder(orderInfor);
    }

}
