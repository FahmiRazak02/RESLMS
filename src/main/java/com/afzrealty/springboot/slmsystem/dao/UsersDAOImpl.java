package com.afzrealty.springboot.slmsystem.dao;

import com.afzrealty.springboot.slmsystem.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersDAOImpl implements UsersDAO {

    private EntityManager entityManager;

    @Autowired
    public UsersDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public User findUserByUserName(String userName) {

        // Retrieve/read from database using username
        TypedQuery<User> query = entityManager.createQuery("from User where userName=:uName and enabled=true", User.class);
        query.setParameter("uName", userName);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    public User findByNric(String nric) {
        // Retrieve/read from database using NRIC
        TypedQuery<User> query = entityManager.createQuery("from User where nric=:nric and enabled=true", User.class);
        query.setParameter("nric", nric);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        // Retrieve/read from database using NRIC
        TypedQuery<User> query = entityManager.createQuery("from User where email=:email and enabled=true", User.class);
        query.setParameter("email", email);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    @Transactional
    public void save(User user) {
        // Create the user
        entityManager.merge(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = entityManager.find(User.class, id);

        entityManager.remove(user);

    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }
}
