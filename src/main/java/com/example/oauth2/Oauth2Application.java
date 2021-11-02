package com.example.oauth2;

import com.example.oauth2.model.User;
import com.example.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class Oauth2Application implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        userService.storeUser(User.builder().login("user").password("12345").build());
    }
}
