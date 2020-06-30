package info.unproj.service;

import info.unproj.AppRunner;
import info.unproj.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppRunner.class)
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Test
    void save() {

        User user = new User("testLogin", "testPassword", "testName", "testSurname");
        userService.save(user);

        UserAccount userAccount = new UserAccount(user,1000);
        userAccountService.save(userAccount);

        Item item = new Item();
        item.setName("test name");
        item.setPrice(500);
        item.setDiscount(50);
        item.setDiscountPrice(250);
        itemService.save(item);

        Cart cart = new Cart( userAccount , 1213234L, CartStatus.OPEN );
        cartService.save(cart);

        Order order = new Order(item, 123, cart);
        orderService.save(order);

        List<Order> orders = orderService.getAll();

        assertNotNull(orders);
        assertTrue(orders.size() >= 1);
    }
}