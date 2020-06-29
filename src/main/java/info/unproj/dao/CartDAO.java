package info.unproj.dao;

import info.unproj.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDAO extends JpaRepository<Cart, Integer> {
}
