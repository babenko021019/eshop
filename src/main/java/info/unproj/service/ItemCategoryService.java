package info.unproj.service;

import info.unproj.dao.ItemCategoryDAO;
import info.unproj.model.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCategoryService {

    @Autowired
    ItemCategoryDAO itemCategoryDAO;

    public ItemCategory getById(Integer id) {
        Optional<ItemCategory> itemCategory = itemCategoryDAO.findById(id);
        if (itemCategory.isEmpty()) {
            return null;
        }
        return itemCategory.get();
    }

    public ItemCategory save(ItemCategory itemCategory) {
        return itemCategoryDAO.save(itemCategory);
    }

    public List<ItemCategory> getAll() {
        return itemCategoryDAO.findAll();
    }

    public ItemCategory update(ItemCategory itemCategory) {
        if (itemCategory.getId() != null && itemCategoryDAO.getOne(itemCategory.getId()) != null) {
            return itemCategoryDAO.save(itemCategory);
        }
        return null;
    }
}