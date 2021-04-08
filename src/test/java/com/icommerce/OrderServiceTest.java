package com.icommerce.model;

import static com.icommerce.services.error.OrderServiceErrorMessage.*;
import static org.hamcrest.Matchers.instanceOf;

import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.icommerce.repository.OrderRepository;
import com.icommerce.repository.ProductRepository;
import com.icommerce.repository.criteria.OrderInfor;
import com.icommerce.services.OrderService;


/**
 *
 * @author kaiser
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceTest {
    public static final String USER_EMAIL = "user_1@gmail.com";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void testGetOrderByEmail() {
        prepareDataForTest();

        List<Order> allOrdersForUser = orderService.getAllOrdersForUser(USER_EMAIL);
        Assert.assertEquals(2, allOrdersForUser.size());
        Assert.assertTrue(allOrdersForUser.stream()
                .anyMatch(order -> "Iphone12".equals(order.getProductName())));
        Assert.assertTrue(allOrdersForUser.stream()
                .anyMatch(order -> "Poco F3".equals(order.getProductName())));
    }

    @Test
    public void testAddOrderMissingFullName() {
        try {
            OrderInfor infor = DataTestFactory.createOrderInformation(null, "0123456789", "TPHCM", USER_EMAIL, "Iphone12", 1);
            orderService.addNewOrder(infor);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(NullPointerException.class));
            Assert.assertEquals(e.getMessage(), MISSING_FULL_NAME_OF_USER);
        }
    }

    @Test
    public void testAddOrderMissingPhone() {
        try {
            OrderInfor infor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", null, "TPHCM", USER_EMAIL, "Iphone12", 1);
            orderService.addNewOrder(infor);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(NullPointerException.class));
            Assert.assertEquals(e.getMessage(), MISSING_PHONE_OF_USER);
        }
    }

    @Test
    public void testAddOrderMissingAddress() {
        try {
            OrderInfor infor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", null, USER_EMAIL, "Iphone12", 1);
            orderService.addNewOrder(infor);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(NullPointerException.class));
            Assert.assertEquals(e.getMessage(), MISSING_ADDRESS_OF_USER);
        }
    }

    @Test
    public void testAddOrderMissingEmail() {
        try {
            OrderInfor infor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", "TPHCM", null, "Iphone12", 1);
            orderService.addNewOrder(infor);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(NullPointerException.class));
            Assert.assertEquals(e.getMessage(), MISSING_EMAIL_OF_USER);
        }
    }

    @Test
    public void testAddOrderMissingProductName() {
        try {
            OrderInfor infor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", "TPHCM", USER_EMAIL, null, 1);
            orderService.addNewOrder(infor);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(NullPointerException.class));
            Assert.assertEquals(e.getMessage(), MISSING_NAME_OF_PRODUCT_IN_ORDER);
        }
    }

    @Test
    public void testAddOrderMissingQuantity() {
        try {
            OrderInfor infor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", "TPHCM", USER_EMAIL, "Iphone12", null);
            orderService.addNewOrder(infor);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(NullPointerException.class));
            Assert.assertEquals(e.getMessage(), MISSING_QUANTITY_OF_PRODUCT_IN_ORDER);
        }
    }

    @Test
    public void testAddOrderWithQuantityEqualZero() {
        try {
            OrderInfor infor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", "TPHCM", USER_EMAIL, "Iphone12", 0);
            orderService.addNewOrder(infor);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(IllegalArgumentException.class));
            Assert.assertEquals(e.getMessage(), NUMBER_PRODUCT_IN_ORDER_MUST_GREATER_THAN_ZERO);
        }
    }

    @Test
    public void testAddOrderWithQuantityInvalid() {
        try {
            OrderInfor infor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", "TPHCM", USER_EMAIL, "Iphone12", 100);
            orderService.addNewOrder(infor);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(IllegalArgumentException.class));
            Assert.assertEquals(e.getMessage(), NUMBER_PRODUCT_IN_ORDER_NOT_VALID);
        }
    }

    @Test
    public void testAddOrderSuccessfully() {
        OrderInfor infor = DataTestFactory.createOrderInformation("Nguyen Quoc Duy", "0123456789", "TPHCM", USER_EMAIL, "Iphone12", 1);
        orderService.addNewOrder(infor);

        List<Order> allOrdersForUser = orderService.getAllOrdersForUser(USER_EMAIL);

        Assert.assertEquals(3, allOrdersForUser.size());

        Assert.assertEquals(2, allOrdersForUser.stream()
                .filter(order -> "Iphone12".equals(order.getProductName())).count());

        Assert.assertEquals(Double.valueOf(3600), allOrdersForUser.stream()
                .filter(order -> "Iphone12".equals(order.getProductName()))
                .map(Order::getSumMoney).filter(Objects::nonNull)
                .reduce(Double::sum).get());

        Assert.assertTrue(allOrdersForUser.stream()
                .anyMatch(order -> "Poco F3".equals(order.getProductName())));

        Product iphone12 = productRepository.findByName("Iphone12");
        Assert.assertEquals(Integer.valueOf(18), iphone12.getQuantity());
    }

    private void prepareDataForTest() {
        OrderInfor orderInfor1 = DataTestFactory.createOrderInformation("Nguyen Quoc Duy",
                "0123456789", "TPHCM", USER_EMAIL, "Iphone12", 1);
        OrderInfor orderInfor2 = DataTestFactory.createOrderInformation("Nguyen Quoc Duy",
                "0123456789", "TPHCM", USER_EMAIL, "Poco F3", 1);
        DataTestFactory.createOrder(orderInfor1, productRepository, orderRepository);
        DataTestFactory.createOrder(orderInfor2, productRepository, orderRepository);
    }
}
