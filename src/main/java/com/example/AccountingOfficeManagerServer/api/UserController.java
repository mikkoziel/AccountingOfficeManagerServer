package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.User;
import com.example.AccountingOfficeManagerServer.entity.modelpack.ChangeRole;
import com.example.AccountingOfficeManagerServer.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public List<User> list() {
        return userService.listAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<User> add(@RequestBody User user) {
        try{
            User saved_user = userService.saveUser(user);
            return new ResponseEntity<>(saved_user, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
        try {
            User existUser = userService.getUser(id);
            user.setUser_id(id);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<User> changeUserPassword(@RequestBody ObjectNode objectNode) {
        try{
            User user = userService.loadUserByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName());

            String password = objectNode.get("new_password").asText();
            String oldPassword = objectNode.get("old_password").asText();;

            userService.checkIfValidPassword(user, oldPassword);
            userService.changeUserPassword(user, password);

            return ResponseEntity.ok()
                    .body(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/updateRole/{id}")
    public void changeUserRole(@PathVariable Integer id, @RequestBody ChangeRole role) {
        this.userService.changeUserRole(id, role);
    }

    @GetMapping("/part/{id}")
    public ResponseEntity<List<User>> getParticipants(@PathVariable Integer id) {
        List<User> participants = this.userService.getParticipants(id);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
}
