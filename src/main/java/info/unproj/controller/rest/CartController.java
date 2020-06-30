package info.unproj.controller.rest;

import info.unproj.model.Cart;
import info.unproj.model.CartStatus;
import info.unproj.service.CartService;
import info.unproj.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Profile("rest")
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    MapperUtil mapperUtil;

    @PutMapping
    public ResponseEntity save(@RequestBody String request) {
        Cart savedCart = cartService.save(mapperUtil.toCart(mapperUtil.toCartDTO(request)));
        if (savedCart == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(mapperUtil.toCartDTOFromCart(savedCart),HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getCart(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Cart cart = cartService.getById(id);
            if (cart == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(mapperUtil.toCartDTOFromCart(cart), HttpStatus.OK);
        } else {
            return new ResponseEntity(mapperUtil.toCartDTOListFromCartList(cartService.getAll()), HttpStatus.OK);
        }
    }

    @PostMapping("update-status")
    public ResponseEntity updateStatus(@RequestBody String body) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(body);
        cartService.updateStatus((Integer) map.get("cartId"), CartStatus.valueOf((String) map.get("cartStatus")));
        int updatedRows =  cartService.updateStatus((Integer) map.get("cartId"), CartStatus.valueOf((String) map.get("cartStatus")));
        if (updatedRows < 1) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
