package info.unproj.service;

import info.unproj.dao.UserAccountDAO;
import info.unproj.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    UserAccountDAO userAccountDAO;

    public UserAccount getById(Integer id) {
        Optional<UserAccount> userAccount = userAccountDAO.findById(id);
        if (userAccount.isEmpty()) {
            return null;
        }
        return userAccount.get();
    }

    public UserAccount save(UserAccount userAccount) {
        if (userAccount.getId() == null) {
            return userAccountDAO.save(userAccount);
        }
        return null;
    }

    public UserAccount update(UserAccount userAccount) {
        if (userAccount.getId() != null && userAccountDAO.getOne(userAccount.getId()) != null) {
            return userAccountDAO.save(userAccount);
        }
        return null;
    }

    public List<UserAccount> getAll() {
        return userAccountDAO.findAll();
    }
}
