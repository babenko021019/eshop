package info.unproj.service;

import info.unproj.dao.ItemDAO;
import info.unproj.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemDAO itemDAO;

    public Item getById(Integer id) {
        Optional<Item> item = itemDAO.findById(id);
        if (item.isEmpty()) {
            return null;
        }
        return item.get();
    }

    public Item save(Item item) {
        return itemDAO.save(item);
    }

    public List<Item> getAll() {
        return itemDAO.findAll();
    }

    public Item update(Item item) {
        if (item.getId() != null && itemDAO.getOne(item.getId()) != null) {
            return itemDAO.save(item);
        }
        return null;
    }
}
