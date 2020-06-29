package info.unproj.controller.rest;

import info.unproj.model.UserAccount;
import info.unproj.service.UserAccountService;
import info.unproj.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Profile("rest")
@RestController
@RequestMapping("user_account")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    MapperUtil mapperUtil;

    @PutMapping
    public ResponseEntity save(@RequestBody String request) {
        userAccountService.save(mapperUtil.toUserAccount(mapperUtil.toUserAccountDTO(request)));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody UserAccount userAccount) {
        UserAccount savedUserAccount = userAccountService.update(userAccount);
        if (savedUserAccount == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userAccount, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getUser(@PathVariable(required = false) Integer id) {
        if (id != null) {
            UserAccount userAccount = userAccountService.getById(id);
            if (userAccount == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(userAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity(userAccountService.getAll(), HttpStatus.OK);
        }
    }


}
