package info.unproj.controller.rest;

import info.unproj.model.Item;
import info.unproj.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Profile("rest")
@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PutMapping
    public ResponseEntity save(@RequestBody Item item) {
        Item savedItem = itemService.save(item);
        if (savedItem == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody Item item) {
        Item savedItem = itemService.update(item);
        if (savedItem == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(item, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getItem(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Item item = itemService.getById(id);
            if (item == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(item, HttpStatus.OK);
        } else {
            return new ResponseEntity(itemService.getAll(), HttpStatus.OK);
        }
    }
}
