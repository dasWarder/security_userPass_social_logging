package com.example.oauth2.controller;

import com.example.oauth2.model.User;
import com.example.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HelloController {


    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> main() {
        return ResponseEntity.ok("This is a main page");
    }


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        String s = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        return ResponseEntity.ok("OK! User: " + s);
    }

}
