package com.example.oauth2.service;

import com.example.oauth2.dao.UserRepository;
import com.example.oauth2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private static final String DEFAULT_PASS = "1234567";

    @Override
    public User storeUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User createFromOAuthUser(OAuth2User logged) {

        String login = logged.getAttribute("login").toString();

        User userByLogin = userRepository.getUserByLogin(login);

        if (Objects.isNull(userByLogin)) {
            User user  = User.builder()
                    .login(login)
                    .password(passwordEncoder.encode(DEFAULT_PASS))
                    .build();

            return this.storeUser(user);
        }

        return userByLogin;
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return this.getUserByLogin(s);
    }
}
