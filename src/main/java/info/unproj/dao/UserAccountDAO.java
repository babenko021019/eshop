package info.unproj.dao;

import info.unproj.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDAO extends JpaRepository<UserAccount, Integer> {
}
