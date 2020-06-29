package info.unproj.dao;

import info.unproj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    User getFirstByLogin(String login);

    User getFirstByLoginAndPassword(String login, String password);

    User getFirstByIdAndLogin(Integer id, String login);
}
