package info.unproj.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.unproj.dao.UserAccountDAO;
import info.unproj.dao.UserDAO;
import info.unproj.model.Cart;
import info.unproj.model.CartStatus;
import info.unproj.model.Order;
import info.unproj.model.UserAccount;
import info.unproj.model.dto.CartDTO;
import info.unproj.model.dto.OrderDTO;
import info.unproj.model.dto.UserAccountDTO;
import info.unproj.service.CartService;
import info.unproj.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MapperUtil {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserAccountDAO userAccountDAO;

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @Autowired
    ObjectMapper objectMapper;



    public UserAccount toUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userAccountDTO.getId());
        userAccount.setUser(userDAO.getOne(userAccountDTO.getUserId()));
        userAccount.setUser(userDAO.getOne(userAccountDTO.getUserLogin()));
        userAccount.setUserBalance(userAccountDTO.getUserBalance());
        return userAccount;
    }

    public UserAccountDTO toUserAccountDTO(String request) {
        try {
            return objectMapper.readValue(request, UserAccountDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUserAccount(userAccountDAO.getOne(cartDTO.getUserId()));
        cart.setUserAccount(userAccountDAO.getOne(cartDTO.getUserLogin()));
        cart.setUserAccount(userAccountDAO.getOne(cartDTO.getUserBalance()));
        cart.setCreationTime(cartDTO.getCreationTime());
        cart.setStatus(CartStatus.valueOf(cartDTO.getStatus().toUpperCase()));
        return cart;
    }

    public CartDTO toCartDTO(String request) {
        try {
            return objectMapper.readValue(request, CartDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order  toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setItem(itemService.getById(orderDTO.getItemId()));
        order.setCart(cartService.getById(orderDTO.getCartId()));
        order.setAmount(orderDTO.getAmount());
        return order;
    }

    public OrderDTO toOrderDTO(String request) {
        try {
            return objectMapper.readValue(request, OrderDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
