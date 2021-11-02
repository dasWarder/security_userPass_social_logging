package com.example.oauth2.controller;


import com.example.oauth2.model.User;
import com.example.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {


    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/base/reg")
    public ResponseEntity<User> create(@RequestBody User user) {

        User user1 = userService.storeUser(user);

        return ResponseEntity.ok(user1);
    }

    @GetMapping("/base")
    public ResponseEntity<User> auth(@RequestParam("login") String login, @RequestParam("pass") String password) {

        Authentication user = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password,
                Collections.singletonList(new SimpleGrantedAuthority("USER"))));
        User userByLogin = userService.getUserByLogin(login);
        SecurityContextHolder.getContext().setAuthentication(user);

        return ResponseEntity.ok(userByLogin);
    }

    @GetMapping("/oauth/reg")
    public ResponseEntity<User> createFromOAuth(@AuthenticationPrincipal OAuth2User logged) {

        User user = userService.createFromOAuthUser(logged);

        return ResponseEntity.ok(user);
    }
}
