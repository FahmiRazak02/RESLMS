package com.afzrealty.springboot.slmsystem.service;

import com.afzrealty.springboot.slmsystem.entity.User;
import com.afzrealty.springboot.slmsystem.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {

    User findUserByUserName(String userName);

    User findUserByNric(String nric);

    User findUserByEmail(String email);

    void save(WebUser webUser);

    User findById(Long userId);

    List<User> findAll();

    void deleteById(Long userId);

    void update(WebUser webUser, Long userId);

    void approved(Long userId);
    void disapprove(Long userId);
}
