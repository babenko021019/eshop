package info.unproj.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.unproj.dao.UserAccountDAO;
import info.unproj.dao.UserDAO;
import info.unproj.model.Cart;
import info.unproj.model.UserAccount;
import info.unproj.model.dto.CartDTO;
import info.unproj.model.dto.UserAccountDTO;
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
}
