package info.unproj.controller.rest;

import info.unproj.model.ItemCategory;
import info.unproj.service.ItemCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Profile("rest")
@RestController
@RequestMapping("item_category")
public class ItemCategoryController {

    @Autowired
    ItemCategoryService itemCategoryService;

    @PutMapping
    public ResponseEntity save(@RequestBody ItemCategory itemCategory) {
        ItemCategory savedItemCategory = itemCategoryService.save(itemCategory);
        if (savedItemCategory == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(itemCategory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody ItemCategory itemCategory) {
        ItemCategory savedItemCategory = itemCategoryService.update(itemCategory);
        if (savedItemCategory == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(itemCategory, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getItemCategory(@PathVariable(required = false) Integer id) {
        if (id != null) {
            ItemCategory itemCategory = itemCategoryService.getById(id);
            if (itemCategory == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(itemCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity(itemCategoryService.getAll(), HttpStatus.OK);
        }
    }
}
