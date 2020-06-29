package info.unproj.service;

import info.unproj.dao.ItemCategoryDAO;

import info.unproj.model.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryService {

    @Autowired
    ItemCategoryDAO itemCategoryDAO;

    public ItemCategory save(ItemCategory itemCategory) {
        return itemCategoryDAO.save(itemCategory);
    }

    public List<ItemCategory> getAll() {
        return itemCategoryDAO.findAll();
    }
}