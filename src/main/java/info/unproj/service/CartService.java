package info.unproj.service;

import info.unproj.dao.CartDAO;
import info.unproj.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartDAO cartDAO;

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
}
