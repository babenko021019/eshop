package info.unproj.controller.rest;

import info.unproj.model.Order;
import info.unproj.service.OrderService;
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
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    MapperUtil mapperUtil;

    @PutMapping
    public ResponseEntity save(@RequestBody String request) {
        Order savedOrder = orderService.save(mapperUtil.toOrder(mapperUtil.toOrderDTO(request)));
        if (savedOrder == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(mapperUtil.orderDTOFormat(savedOrder), HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getOrder(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Order order = orderService.getById(id);
            if (order == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(mapperUtil.orderDTOFormat(order), HttpStatus.OK);
        } else {
            return new ResponseEntity(mapperUtil.orderDTOFormatList(orderService.getAll()), HttpStatus.OK);
        }
    }

    @PostMapping("update-amount")
    public ResponseEntity updateAmount(@RequestBody String body) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(body);
        int updatedRows = orderService.updateAmount((Integer) map.get("orderId"), (Integer) map.get("amount"));
        if (updatedRows < 1) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
