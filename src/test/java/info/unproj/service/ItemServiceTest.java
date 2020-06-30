package info.unproj.service;

import info.unproj.AppRunner;
import info.unproj.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppRunner.class)
@ActiveProfiles("test")
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    void save() {
        Item item = new Item();
        item.setName("test name");
        item.setPrice(500);
        item.setDiscount(50);
        item.setDiscountPrice(250);

        itemService.save(item);

        List<Item> items = itemService.getAll();

        assertNotNull(items);
        assertTrue(items.size() >= 1);
    }
}