package info.unproj.service;

import info.unproj.dao.OrderDAO;
import info.unproj.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    public Order getById(Integer id) {
        Optional<Order> order = orderDAO.findById(id);
        if (order.isEmpty()) {
            return null;
        }
        return order.get();
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            return orderDAO.save(order);
        }
        return null;
    }

    public List<Order> getAllByCart(Integer cartId) {
        return orderDAO.getAllByCart(cartId);
    }

    public int updateAmount(Integer orderId, Integer amount) {
        return orderDAO.updateAmount(orderId, amount);
    }
}
