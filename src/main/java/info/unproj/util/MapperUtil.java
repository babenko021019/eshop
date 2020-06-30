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
import java.util.ArrayList;
import java.util.List;

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
        cart.setCartStatus(CartStatus.valueOf(cartDTO.getStatus().toUpperCase()));
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

    public CartDTO toCartDTOFromCart(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserAccount().getId());
        cartDTO.setCreationTime(cart.getCreationTime());
        cartDTO.setStatus(cart.getCartStatus().name());
        return cartDTO;
    }

    public List<CartDTO> toCartDTOListFromCartList(List<Cart> carts) {
        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart each : carts) {
            CartDTO cartDTO = toCartDTOFromCart(each);
            cartDTOs.add(cartDTO);
        }
        return cartDTOs;
    }

    public Order toOrder(OrderDTO orderDTO) {
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

    public OrderDTO toOrderDTOFromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setItemId(order.getItem().getId());
        orderDTO.setCartId(order.getCart().getId());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setItemName(order.getItem().getName());
        orderDTO.setItemPrice(order.getItem().getPrice());
        return orderDTO;
    }

    public String orderDTOFormat(Order order) {
        OrderDTO orderDTO = toOrderDTOFromOrder(order);
        return "{" + System.lineSeparator() +
                "  \"" + "id" + "\" : " + orderDTO.getId() + "," + System.lineSeparator() +
                "  \"" + "itemId" + "\" : " + orderDTO.getItemId() + "," + System.lineSeparator() +
                "  \"" + "cartId" + "\" : " + orderDTO.getCartId() + "," + System.lineSeparator() +
                "  \"" + "amount" + "\" : " + orderDTO.getAmount() + System.lineSeparator() +
                "}";
    }

    public String orderDTOFormatList(List<Order> orders) {
        List<String> list = new ArrayList<>();
        for (Order each : orders) {
            String string = System.lineSeparator() + orderDTOFormat(each);
            list.add(string);
        }
        return list.toString();
    }
}
