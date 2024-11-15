package com.afzrealty.springboot.slmsystem.dao;

import com.afzrealty.springboot.slmsystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UsersDAO {

    User findUserByUserName(String userName);

    User findByNric(String nric);

    User findByEmail(String email);

    void save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    void update(User user);
}
