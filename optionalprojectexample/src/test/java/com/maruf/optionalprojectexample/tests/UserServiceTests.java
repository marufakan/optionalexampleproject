package com.maruf.optionalprojectexample.tests;

import com.maruf.optionalprojectexample.entities.User;
import com.maruf.optionalprojectexample.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void testGetUserByEmail() {
        Optional<User> userOptional = userService.getUserByEmail("test@example.com");
        assertTrue(userOptional.isPresent());
        assertEquals("test@gmail.com", userOptional.get().getEmail());
    }

    @Test
    void testGetUserNameByEmail() {
        String userName = userService.getUserNameByEmail("testUnit@gmail.com");
        assertEquals("Unknown User", userName);
    }
}

