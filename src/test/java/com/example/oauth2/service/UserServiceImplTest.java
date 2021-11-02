package com.example.oauth2.service;

import com.example.oauth2.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void getAll() {

        User user = userService.getUserByLogin("user");
        System.out.println(user.toString());
    }
}