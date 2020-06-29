package info.unproj.dao;

import info.unproj.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryDAO extends JpaRepository<ItemCategory, Integer> {
}
