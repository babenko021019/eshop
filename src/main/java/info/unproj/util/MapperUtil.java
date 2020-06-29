package info.unproj.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.unproj.dao.UserDAO;
import info.unproj.model.UserAccount;
import info.unproj.model.dto.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MapperUtil {

    @Autowired
    UserDAO userDAO;

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
}
