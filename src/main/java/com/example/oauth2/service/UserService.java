package com.example.oauth2.service;

import com.example.oauth2.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User storeUser(User user);

    User createFromOAuthUser(OAuth2User logged);

    User getUserByLogin(String login);

    List<User> findAll();
}
