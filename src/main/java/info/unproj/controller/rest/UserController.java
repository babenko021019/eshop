package info.unproj.controller.rest;

import info.unproj.model.User;
import info.unproj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Profile("rest")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping
    public ResponseEntity save(@RequestBody User user) {
        User savedUser = userService.save(user);
        if (savedUser == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getUser(@PathVariable(required = false) Integer id) {
        if (id != null) {
            User user = userService.getById(id);
            if (user == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(userService.getAll(), HttpStatus.OK);
        }
    }
}
