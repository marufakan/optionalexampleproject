package com.maruf.optionalprojectexample.controllers;

import com.maruf.optionalprojectexample.entities.User;
import com.maruf.optionalprojectexample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<String> getUserNameByEmail(@PathVariable String email) {
        String userName = userService.getUserNameByEmail(email);
        return ResponseEntity.ok(userName);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<User> getUserByEmailOrCreate(@RequestParam String email) {
        User user = userService.getUserByEmailOrCreate(email);
        return ResponseEntity.ok(user);
    }
}
