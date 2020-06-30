package info.unproj.service;

import info.unproj.dao.CartDAO;
import info.unproj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartDAO cartDAO;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    public Cart getById(Integer id) {
        Optional<Cart> cart = cartDAO.findById(id);
        if (cart.isEmpty()) {
            return null;
        }
        return cart.get();
    }

    public Cart save(Cart cart) {
        if (cart.getId() == null) {
            return cartDAO.save(cart);
        }
        return null;
    }

    public List<Cart> getAll() {
        return cartDAO.findAll();
    }

    public List<Cart> getByUserAndOpenStatus(Integer userId) {
        return cartDAO.getByUserAndOpenStatus(userId);
    }

    public int updateStatus(Integer cartId, CartStatus cartStatus) {
        return cartDAO.updateStatus(cartId, cartStatus.ordinal());
    }

    public Cart addItem(Integer userId, Integer itemId) {
        UserAccount userAccount = userAccountService.getById(userId);
        if(userAccount == null) {
            return null;
        }
        Item item = itemService.getById(itemId);
        if(item == null) {
            return null;
        }
        List<Cart> carts = cartDAO.getByUserAndOpenStatus(userId);
        Cart openCart;
        if (carts.size() == 0) {
            Long currentTime = new Date().getTime();
            openCart = new Cart(userAccount, currentTime, CartStatus.OPEN);
            cartService.save(openCart);
            Order order = new Order(item, 1, openCart);
            orderService.save(order);
        } else {
            openCart = carts.get(0);
            Order targetOrder = orderService.getByCartWithItem(openCart.getId(), itemId);
            if (targetOrder == null) {
                Order order = new Order(item, 1, openCart);
                orderService.save(order);
            } else {
                orderService.updateAmount(targetOrder.getId(),
                        orderService.getById(targetOrder.getId()).getAmount() + 1);
            }
        }
        return openCart;
    }
}
