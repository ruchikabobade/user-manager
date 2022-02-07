package com.user.controller;

import com.user.entity.Role;
import com.user.entity.User;
import com.user.exception.InvalidInputException;
import com.user.model.SignUp;
import com.user.model.UserRequest;
import com.user.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserManagerController {
    @Autowired
    private UserManagerService service;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        List<User>  users = service.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id){
        Optional<User> user = service.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody UserRequest user) throws MessagingException, UnsupportedEncodingException {
        User u = service.addUser(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        User u = service.updateUser(user, id);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public ResponseEntity<String> addRole(){
        service.addRoles();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/verify/{code}", method = RequestMethod.GET)
    public ResponseEntity<Void> verifyUser(@PathVariable String code){
        Long userId = service.verifyUser(code);
        String url = "http://localhost:3000/update-password/" + userId;
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<String> updatePassword(@RequestBody SignUp input){
        User u = service.updatePassword(input);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Role> Login(@RequestBody SignUp input) throws InvalidInputException {
        Role role = service.getRoleForUser(input);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

}
